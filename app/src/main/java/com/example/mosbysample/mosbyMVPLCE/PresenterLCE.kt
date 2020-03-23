package com.example.mosbysample.mosbyMVPLCE

import android.os.Handler
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class PresenterLCE : MvpBasePresenter<ViewLCE>(), IpresenterLCE {
    override fun loadData() {
        view.showLoading(true)
        Handler().postDelayed({
            view.showContent()
            view.setData(createData())
        }, 3000)
    }

    override fun showError() {
        view.showLoading(true)
        Handler().postDelayed({
            view.showError(null, false)
        }, 3000)
    }

    private fun createData(): List<DataModel>? {
        val data = ArrayList<DataModel>()
        for (i in 1..15) {
            data.add(DataModel("Name $i", "Surname $i"))
        }
        return data
    }
}