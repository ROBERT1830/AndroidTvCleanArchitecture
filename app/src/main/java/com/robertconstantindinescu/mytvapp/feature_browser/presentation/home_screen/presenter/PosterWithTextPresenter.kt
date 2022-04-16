package com.robertconstantindinescu.mytvapp.feature_browser.presentation.home_screen.presenter

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.robertconstantindinescu.mytvapp.R
import com.robertconstantindinescu.mytvapp.feature_browser.presentation.util.Constants.ITEM_HEIGHT
import com.robertconstantindinescu.mytvapp.feature_browser.presentation.util.Constants.ITEM_WIDTH
import org.w3c.dom.Text

class PosterWithTextPresenter: Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val textView = TextView(parent.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            layoutParams = ViewGroup.LayoutParams(ITEM_WIDTH, ITEM_HEIGHT)
            setBackgroundColor(ContextCompat.getColor(parent.context, R.color.default_background))
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {

        (viewHolder.view as TextView).text = item as String
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        //nothing
    }
}