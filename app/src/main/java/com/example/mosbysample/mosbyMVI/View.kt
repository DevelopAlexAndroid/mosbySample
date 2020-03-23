package com.example.mosbysample.mosbyMVI

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface View : MvpView {

    fun clickButton(): Observable<Unit>
    fun loadFirstIntent(): Observable<Boolean>

    fun render(mviState: MviState)

}