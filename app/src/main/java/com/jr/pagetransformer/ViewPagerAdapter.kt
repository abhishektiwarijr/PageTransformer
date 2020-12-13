package com.jr.pagetransformer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jr.pagetransformer.FragmentA.Companion.newInstance

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return newInstance(position)
    }

    override fun getCount(): Int {
        return Content.values().size
    }
}