package com.example.mosbysample.mosbyMVI

import android.util.Log
import com.example.mosbysample.mosbyMVI.model.PersonModel
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class Presenter : MviBasePresenter<View, MviState>() {

    //имитация ошибки загрузки
    private val getDataFromInternet = Observable.just("fail")

    override fun bindIntents() {

        val firstLoading: Observable<ViewStateActivity> =
            intent(View::loadFirstIntent)
                .doOnNext { Log.d("Presenter", "firstLoading") }
                .delay(2000, TimeUnit.MILLISECONDS)
                .map { (ViewStateActivity.DataState(createMviState()) as ViewStateActivity) }
                .startWith(ViewStateActivity.LoadingState())
                .onErrorReturn { ex -> ViewStateActivity.ErrorState(ex) }
                .subscribeOn(Schedulers.io())

        val click: Observable<ViewStateActivity> =
            intent(View::clickButton)
                .switchMap { getDataFromInternet
                .doOnNext { data ->  Log.d("Presenter", "click = $data") }
                .delay(2000, TimeUnit.MILLISECONDS)
                .map { (createMviStateError() as ViewStateActivity) }
                .startWith(ViewStateActivity.LoadingState())
                .onErrorReturn { ex -> ViewStateActivity.ErrorState(ex) }
                .subscribeOn(Schedulers.io())}


        val allIntentsObservable = Observable
            .merge(firstLoading, click)
            .observeOn(AndroidSchedulers.mainThread())

        val initialState = MviState(error = false, loading = false, data = ArrayList())

        subscribeViewState(
            allIntentsObservable.scan(initialState, this::viewStateReducer).distinctUntilChanged(),
            View::render)
    }

    /**
     * @param previousMviState -  используется когда есть данные,
     * которые требуется сохранять при добавление новой информации н/р пагинация
     * @param viewStateActivity - новое состояние
    **/
    private fun viewStateReducer(
        previousMviState: MviState,
        viewStateActivity: ViewStateActivity
    ): MviState {

        if (viewStateActivity is ViewStateActivity.LoadingState) {
            Log.d("Presenter", "viewStateReducer.LoadingState")
            return MviState(error = false, loading = true, data = ArrayList())
        }

        if (viewStateActivity is ViewStateActivity.ErrorState) {
            Log.d("Presenter", "viewStateReducer.ErrorState")
            return MviState(error = true, loading = false, data = ArrayList())
        }

        if (viewStateActivity is ViewStateActivity.DataState) {
            Log.d("Presenter", "viewStateReducer.DataState")
            return createMviState()
        }

        throw IllegalStateException("Don't know how to reduce the partial state ")
    }

    private fun createMviState(): MviState {
        val mviState = MviState(error = false, loading = false, data = ArrayList())
        for (i in 1..5) {
            (mviState.data as ArrayList).add(
                PersonModel(
                    "Alex№$i",
                    android.R.color.holo_green_light
                )
            )
        }
        return mviState
    }

    private fun createMviStateError(): ViewStateActivity.ErrorState {
        return ViewStateActivity.ErrorState(Throwable())
    }
}