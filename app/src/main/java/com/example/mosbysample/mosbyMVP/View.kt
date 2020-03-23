package com.example.mosbysample.mosbyMVP

import com.hannesdorfmann.mosby3.mvp.MvpView

interface View : MvpView {
    fun reactionActivity(text: String)
}