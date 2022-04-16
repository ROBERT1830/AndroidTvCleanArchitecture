package com.robertconstantindinescu.mytvapp.feature_browser.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.robertconstantindinescu.mytvapp.R
import dagger.hilt.android.AndroidEntryPoint
import io.github.inflationx.viewpump.ViewPumpContextWrapper

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
//    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}