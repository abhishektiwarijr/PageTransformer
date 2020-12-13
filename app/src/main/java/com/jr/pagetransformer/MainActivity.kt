package com.jr.pagetransformer

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.jr.pagetransformer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val numLevels = 28
    private val vpAdapter by lazy { ViewPagerAdapter(supportFragmentManager) }
    private val colors by lazy { Content.values() }
    private val argbEvaluator = ArgbEvaluator()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cookViewpager()
    }

    private fun cookViewpager() {
        binding.vpDayNight.apply {
            adapter = vpAdapter
//            setPageTransformer(false, ColorTransformer())
            setPageTransformer(false, IntroPageTransformer())
            addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    if (position < vpAdapter.count - 1 && position < colors.size - 1) {
                        setBackgroundColor(
                            argbEvaluator.evaluate(
                                positionOffset,
                                colors[position].color,
                                colors[position + 1].color
                            ) as Int
                        )

                        val progress = (numLevels * positionOffset).toInt()
                        binding.todaySunMoon.setImageLevel(progress)

                    } else {
                        val color = colors[colors.size - 1].color
                        // the last page color
                        setBackgroundColor(color)
                    }
                }
            })
        }
    }
}