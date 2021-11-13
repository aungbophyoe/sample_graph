package com.aungbophyoe.space.samplechart.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SmallPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
    FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->OneSmallFragment()
            else -> TwoSmallFragment()
        }
    }
}