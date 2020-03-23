package com.example.mosbysample.mosbyMVP

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class Presenter : MvpBasePresenter<View>() {
    fun presenterReaction() {
        if (view != null)
        view.reactionActivity("TEST")
    }
}