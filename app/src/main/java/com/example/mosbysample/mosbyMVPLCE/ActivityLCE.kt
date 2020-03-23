package com.example.mosbysample.mosbyMVPLCE

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mosbysample.R
import com.example.mosbysample.adapter.ListAdapter
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity
import kotlinx.android.synthetic.main.activity_l_c_e.*

class ActivityLCE : MvpLceActivity<SwipeRefreshLayout, List<DataModel>, ViewLCE, IpresenterLCE>(),
    ViewLCE, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var adapter: ListAdapter

    override fun createPresenter(): IpresenterLCE = PresenterLCE()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l_c_e)
        // Setup contentView == SwipeRefreshView
        contentView.setOnRefreshListener(this)
        loadData(false)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))

        butMessage.setOnClickListener {
            highPriorityMessage(getString(R.string.message))
        }
        butError.setOnClickListener {
            presenter.showError()
        }
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
}
