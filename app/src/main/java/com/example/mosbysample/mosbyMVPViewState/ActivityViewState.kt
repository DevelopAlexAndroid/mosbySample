package com.example.mosbysample.mosbyMVPViewState

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mosbysample.R
import com.example.mosbysample.adapter.ListAdapter
import com.example.mosbysample.mosbyMVPLCE.DataModel
import com.example.mosbysample.mosbyMVPLCE.IpresenterLCE
import com.example.mosbysample.mosbyMVPLCE.PresenterLCE
import com.example.mosbysample.mosbyMVPLCE.ViewLCE
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateActivity
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState
import kotlinx.android.synthetic.main.activity_view_state.recyclerView

class ActivityViewState :
    MvpLceViewStateActivity<SwipeRefreshLayout, List<DataModel>, ViewLCE, IpresenterLCE>(),
    ViewLCE, SwipeRefreshLayout.OnRefreshListener {

    private var adapter = ListAdapter(ArrayList())

    override fun createPresenter(): IpresenterLCE = PresenterLCE()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_state)
        // Setup contentView == SwipeRefreshView
        contentView.setOnRefreshListener(this)
        retainInstance = true

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
    }

    override fun setData(data: List<DataModel>?) {
        adapter = ListAdapter(data as ArrayList<DataModel>)
        recyclerView.adapter = adapter
    }

    override fun loadData(pullToRefresh: Boolean) {
        Toast.makeText(this, "loadData", Toast.LENGTH_SHORT).show()
        presenter.loadData()
    }

    override fun onRefresh() {
        Toast.makeText(this, "onRefresh", Toast.LENGTH_SHORT).show()
        loadData(true)
        Handler().postDelayed({ contentView.isRefreshing = false},1000)
    }

    override fun highPriorityMessage(message: String) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        return "ErrorMessage"
    }

    override fun createViewState(): LceViewState<List<DataModel>, ViewLCE> {
       return RetainingLceViewState()
    }

    override fun getData(): List<DataModel> {
        return adapter.listData
    }

}
