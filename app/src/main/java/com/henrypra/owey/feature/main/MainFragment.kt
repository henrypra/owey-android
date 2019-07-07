package com.henrypra.owey.feature.main

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.henrypra.owey.R
import com.henrypra.owey.architecture.BaseContractFragment
import com.henrypra.owey.model.Debt
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainFragment : BaseContractFragment<MainContract.Presenter>(), MainContract.View, View.OnClickListener {
    private val adapter: MainAdapter by lazy { MainAdapter(getCurrentContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initOnCLickListeners()
        presenter?.retrieveDebtFromDatabase()
    }

    private fun initOnCLickListeners() {
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.gist_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.mm_refresh -> {
                adapter.notifyDataSetChanged()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun displayDebtList(debtList: List<Debt>) {
        GlobalScope.launch(Dispatchers.Main) {
            adapter.debtList = debtList.toMutableList()
            adapter.notifyDataSetChanged()
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

}

