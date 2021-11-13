package com.aungbophyoe.space.samplechart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.aungbophyoe.space.samplechart.R
import com.google.android.material.tabs.TabLayout

private const val CLASSROOM_ID = "classId"
class OneFragment : Fragment(R.layout.fragment_one) {
    private var classroomId : String? = null
    private var smallPagerAdapter : SmallPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            classroomId = it.getString(CLASSROOM_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val smallTab = view.findViewById<TabLayout>(R.id.smallTab)
        val pager = view.findViewById<ViewPager>(R.id.pager)
        smallTab.addTab(smallTab.newTab().setText("News"))
        smallTab.addTab(smallTab.newTab().setText("History"))
        smallPagerAdapter = SmallPagerAdapter(requireActivity().supportFragmentManager,smallTab!!.tabCount)
        pager.adapter = smallPagerAdapter
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(smallTab))
        smallTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }
}