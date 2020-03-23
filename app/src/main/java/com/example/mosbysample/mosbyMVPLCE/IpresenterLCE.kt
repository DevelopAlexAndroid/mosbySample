package com.example.mosbysample.mosbyMVPLCE

import com.hannesdorfmann.mosby3.mvp.MvpPresenter

interface IpresenterLCE : MvpPresenter<ViewLCE> {
    fun loadData()
    fun showError()
}