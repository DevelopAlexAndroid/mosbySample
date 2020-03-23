package com.example.mosbysample.mosbyMVPLCE

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView

interface ViewLCE : MvpLceView<List<DataModel>> {
    fun highPriorityMessage(message: String)
}