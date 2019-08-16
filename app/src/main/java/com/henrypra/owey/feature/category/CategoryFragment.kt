package com.henrypra.owey.feature.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.henrypra.owey.R
import com.henrypra.owey.architecture.BaseContractFragment
import com.henrypra.owey.model.Debt
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryFragment : BaseContractFragment<CategoryContract.Presenter>(), CategoryContract.View, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, CategoryAdapter.DebtClickListener {
    private val adapter: CategoryAdapter by lazy { CategoryAdapter(getCurrentContext(), this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initOnCLickListeners()
        refresh_container?.setOnRefreshListener(this)
        presenter?.retrieveDebtFromDatabase()
    }

    private fun initOnCLickListeners() {
    }

    override fun onResume() {
        super.onResume()
        presenter?.retrieveDebtFromDatabase()
    }

    override fun displayDebtList(debtList: List<Debt>) {
        GlobalScope.launch(Dispatchers.Main) {
            adapter.debtList = debtList.toMutableList()
            adapter.notifyDataSetChanged()
            refresh_container?.isRefreshing = false
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

