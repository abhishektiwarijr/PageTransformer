package com.jr.pagetransformer

import android.view.View
import androidx.viewpager.widget.ViewPager

abstract class BasePageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val pageIndex = page.tag as Int
        transformPage(page, pageIndex, position)
    }

    protected abstract fun transformPage(page: View?, pageIndex: Int, position: Float)
    override fun toString(): String {
        return javaClass.simpleName
    }

    companion object {
        @JvmStatic
        fun inRange(position: Float): Boolean {
            return position <= 1.0 && position >= -1.0
        }

        @JvmStatic
        fun isLeftPage(position: Float): Boolean {
            return position < 0
        }

        @JvmStatic
        fun isRightPage(position: Float): Boolean {
            return position > 0
        }
    }
}