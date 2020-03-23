package com.example.mosbysample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mosbysample.mosbyMVP.Activity
import com.example.mosbysample.mosbyMVPLCE.ActivityLCE
import com.example.mosbysample.mosbyMVPViewState.ActivityViewState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//http://hannesdorfmann.com/android/mosby3-mvi-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mosby_mvp_but.setOnClickListener {
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
        }
        mosby_mvp_lce_but.setOnClickListener {
            val intent = Intent(this, ActivityLCE::class.java)
            startActivity(intent)
        }
        mosby_mvp_viewstate_but.setOnClickListener {
            val intent = Intent(this, ActivityViewState::class.java)
            startActivity(intent)
        }
        mosby_mvi_but.setOnClickListener {
            val intent = Intent(this, com.example.mosbysample.mosbyMVI.Activity::class.java)
            startActivity(intent)
        }
    }
}
