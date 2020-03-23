package com.example.mosbysample.mosbyMVI

import android.os.Bundle
import android.util.Log
import com.example.mosbysample.R
import com.example.mosbysample.mosbyMVI.model.PersonModel
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_m_v_i.*

class Activity : MviActivity<View, Presenter>(), View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_i)
    }

    override fun createPresenter(): Presenter = Presenter()

    override fun clickButton(): Observable<Unit> = button.clicks()

    override fun loadFirstIntent(): Observable<Boolean> {
        return Observable.just(true)
    }

    override fun render(mviState: MviState) {
        if (mviState.loading)
            loadState()

        if (mviState.error)
            errorState()

        if (!mviState.data.isNullOrEmpty())
            viewDataState(mviState.data)
    }

    private fun loadState() {
        progressBar.visibility = android.view.View.VISIBLE
        errorTextView.visibility = android.view.View.GONE
    }

    private fun errorState() {
        progressBar.visibility = android.view.View.GONE
        errorTextView.visibility = android.view.View.VISIBLE
        button.text = resources.getString(R.string.button)
    }

    private fun viewDataState(dataModel: List<PersonModel>) {
        progressBar.visibility = android.view.View.GONE
        errorTextView.visibility = android.view.View.GONE

        button.text = resources.getString(R.string.app_name)
        (dataModel as ArrayList).forEach{
            Log.d("Activity", it.name)
        }
    }

}
