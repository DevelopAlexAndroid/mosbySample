package com.example.mosbysample.mosbyMVP

import android.os.Bundle
import android.widget.Toast
import com.example.mosbysample.R
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_mosby_m_v_p.*

class Activity : MvpActivity<View, Presenter>(), View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mosby_m_v_p)
        container.setOnClickListener {
            presenter.presenterReaction()
        }
    }

    override fun createPresenter(): Presenter = Presenter()

    override fun reactionActivity(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}
