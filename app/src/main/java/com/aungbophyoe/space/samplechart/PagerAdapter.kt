package com.aungbophyoe.space.samplechart

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aungbophyoe.space.samplechart.view.OneFragment
import com.aungbophyoe.space.samplechart.view.ThreeFragment
import com.aungbophyoe.space.samplechart.view.TwoFragment

class PagerAdapter(fm: FragmentManager, private var tabCount: Int) :
    FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0-> OneFragment()
            else -> ThreeFragment()
        }
    }
}