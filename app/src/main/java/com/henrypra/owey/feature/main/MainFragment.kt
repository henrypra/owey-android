package com.henrypra.owey.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.henrypra.owey.R
import com.henrypra.owey.architecture.BaseContractFragment
import com.henrypra.owey.model.Debt
import com.henrypra.owey.scichart.basics.BalanceSciChart
import com.scichart.charting.visuals.SciPieChartSurface
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainFragment : BaseContractFragment<MainContract.Presenter>(), MainContract.View, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, MainAdapter.DebtClickListener {
    private val SURFACE_TAG = "SURFACE_TAG"
    private val adapter: MainAdapter by lazy { MainAdapter(getCurrentContext(), this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        refresh_container?.setOnRefreshListener(this)
        presenter?.retrieveDebtFromDatabase()
    }

    override fun onResume() {
        super.onResume()
        presenter?.retrieveDebtFromDatabase()
    }

    override fun displayDebtList(debtList: List<Debt>) {
        GlobalScope.launch(Dispatchers.Main) {
            initChartValues(debtList)
            val sciChartSurface = SciPieChartSurface(activity)
            sciChartSurface.tag = SURFACE_TAG
            activity?.let {
                BalanceSciChart.initChart(it,
                        chart_container,
                        sciChartSurface,
                        debtList)
            }

            adapter.debtList = debtList.toMutableList()
            adapter.notifyDataSetChanged()
            refresh_container?.isRefreshing = false
        }
    }

    private fun initChartValues(debtList: List<Debt>) {
        if (debtList.isNullOrEmpty()) {
            data_container?.visibility = View.GONE
        } else {
            data_container?.visibility = View.VISIBLE
        }

        val allLoans: Double = debtList.filter { it.isDebt == false }.sumByDouble { it.amount!! }
        val allDebts: Double = debtList.filter { it.isDebt == true }.sumByDouble { it.amount!! }
        val total = allLoans - allDebts

        if (total > 0) {
            tv_total?.text = "Balance: ${total.toString()}€"
        } else {
            tv_total?.text = "Balance: ${total.toString()}€"
        }
    }

    private fun initRecyclerView() {
        rv_debt_main?.layoutManager = LinearLayoutManager(getCurrentContext(), LinearLayoutManager.VERTICAL, false)
        rv_debt_main?.setHasFixedSize(true)
        rv_debt_main?.adapter = adapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
        }
    }

    override fun onRefresh() {
        presenter?.retrieveDebtFromDatabase()
    }

    override fun onDebtClicked(id: Int) {
        presenter?.onDebtClicked(id)
    }
}

