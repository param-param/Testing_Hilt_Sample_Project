package com.learning.hiltSample.utils

import android.content.Context
import android.content.res.Resources
import android.widget.RelativeLayout

class CommonUtils {

    companion object {
        fun getPosterLayoutParams(context: Context): RelativeLayout.LayoutParams? {
            val screenWidth: Int = getScreenWidth(context)
            val params: RelativeLayout.LayoutParams
            val calculatedWidth = (screenWidth / 2 - dpToPx(20))
            val calculatedHeight = (calculatedWidth*3)/2
            params = RelativeLayout.LayoutParams(calculatedWidth, calculatedHeight)
            return params
        }

        fun getScreenWidth(context: Context): Int {
            val displayMetrics = context.resources.displayMetrics
            return displayMetrics.widthPixels
        }

        fun dpToPx(dp: Int): Int {
            return (dp * Resources.getSystem().displayMetrics.density).toInt()
        }

    }
}