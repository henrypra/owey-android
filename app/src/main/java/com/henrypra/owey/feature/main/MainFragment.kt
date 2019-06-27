package com.henrypra.owey.feature.main

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.henrypra.owey.R
import com.henrypra.owey.architecture.BaseContractFragment
import com.henrypra.owey.model.Debt
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseContractFragment<MainContract.Presenter>(), MainContract.View {
    private val adapter: MainAdapter by lazy { MainAdapter(getCurrentContext()) }
    var debtList = mutableListOf<Debt>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        debtList.add(0, Debt("Mittagsessen", "01-01-2019", "Du schuldest Barbara Geld!", 7.50, "430569335"))
        debtList.add(1, Debt("Schulgeld", "01-01-2019", "Du 23 Barbara Geld!", 71.50, "430569335"))
        debtList.add(2, Debt("Fahrrad", "01-01-2019", "Du 42 Barbara Geld!", 75.50, "430569335"))
        debtList.add(3, Debt("Bäcker", "01-01-2019", "Du 11 Barbara Geld!", 57.50, "430569335"))
        debtList.add(4, Debt("Leihen", "01-01-2019", "Du 42 42 Geld!", 217.50, "430569335"))
        debtList.add(5, Debt("Ausflug", "01-01-2019", "Du 242442 Barbara Geld!", 75.50, "430569335"))
        debtList.add(6, Debt("Ausflug", "01-01-2019", "Du 242442 Barbara Geld!", 75.50, "430569335"))
        debtList.add(7, Debt("Ausflug", "01-01-2019", "Du 242442 Barbara Geld!", 75.50, "430569335"))
        debtList.add(8, Debt("Ausflug", "01-01-2019", "Du 242442 Barbara Geld!", 75.50, "430569335"))
        debtList.add(9, Debt("Ausflug", "01-01-2019", "Du 242442 Barbara Geld!", 75.50, "430569335"))
        debtList.add(10, Debt("Ausflug", "01-01-2019", "Du 242442 Barbara Geld!", 75.50, "430569335"))
        debtList.add(11, Debt("Ausflug", "01-01-2019", "Du 242442 Barbara Geld!", 75.50, "430569335"))
        adapter.debtList = debtList
        adapter.notifyDataSetChanged()
        initRecyclerView()
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

    private fun initRecyclerView() {
        rv_debt_main?.layoutManager = LinearLayoutManager(getCurrentContext(), LinearLayoutManager.VERTICAL, false)
        rv_debt_main?.setHasFixedSize(true)
        rv_debt_main?.adapter = adapter
    }

}

