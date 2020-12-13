package com.jr.pagetransformer

import android.graphics.Color
import android.view.View

class ColorTransformer : BasePageTransformer() {
    public override fun transformPage(page: View?, pageIndex: Int, position: Float) {
        if (inRange(position)) {
            if (isRightPage(position)) {
                val leftIndex = pageIndex - 1
                val leftColor = Content.values()[leftIndex].color
                val rightColor = Content.values()[pageIndex].color
                val composedColor = blendColors(leftColor, rightColor, position)
                page?.setBackgroundColor(composedColor)
            } else if (isLeftPage(position)) {
                val rightIndex = pageIndex + 1
                val leftColor = Content.values()[pageIndex].color
                val rightColor = Content.values()[rightIndex].color
                val composedColor = blendColors(leftColor, rightColor, 1 - Math.abs(position))
                page?.setBackgroundColor(composedColor)
            } else {
                page?.setBackgroundColor(Content.values()[pageIndex].color)
            }
        } else {
            page?.setBackgroundColor(Content.values()[pageIndex].color)
        }
    }

    companion object {
        private fun blendColors(color1: Int, color2: Int, ratio: Float): Int {
            val inverseRation = 1f - ratio
            val r = Color.red(color1) * ratio + Color.red(color2) * inverseRation
            val g = Color.green(color1) * ratio + Color.green(color2) * inverseRation
            val b = Color.blue(color1) * ratio + Color.blue(color2) * inverseRation
            return Color.rgb(r.toInt(), g.toInt(), b.toInt())
        }
    }
}