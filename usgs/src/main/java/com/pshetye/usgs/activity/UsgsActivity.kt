package com.pshetye.usgs.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.play.core.splitcompat.SplitCompat
import com.pshetye.usgs.R

class UsgsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usgs)

        val someString = getString(R.string.something_for_testing)
    }
}