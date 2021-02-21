package com.andariadar.util

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

class CubeOutScalingTransformer : ViewPager2.PageTransformer {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun transformPage(page: View, position: Float) {
        page.apply {
            pivotY = (height / 2).toFloat()
            when {
                // Signifies page offset to left side or
                // this page is way off-screen to the right
                position < -1 || position > 1 -> {
                    alpha = 0f
                }

                // Signifies current page
                position <= 0 -> {
                    alpha = 1f
                    pivotX = width.toFloat()
                    rotationY = -90 * abs(position)
                }

                // Signifies page offset to right side
                position <= 1 -> {
                    alpha = 1f
                    pivotX = 0f
                    rotationY = 90 * abs(position)
                }

                abs(position) <= 0.5 -> {
                    scaleY = max(0.4f, 1 - abs(position))
                }

                abs(position) <= 1 -> {
                    scaleY = max(0.4f, abs(position))
                }
            }
        }
    }
}