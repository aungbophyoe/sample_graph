package com.aungbophyoe.space.samplechart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.view.setPadding
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.viewpager.widget.ViewPager
import com.aungbophyoe.space.samplechart.view.OneFragment
import com.aungbophyoe.space.samplechart.view.TwoFragment
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity(),View.OnClickListener {
    val mainChart by lazy {
        findViewById<LinearLayout>(R.id.mainChart)
    }

    val dataGraph by lazy {
        findViewById<LinearLayout>(R.id.dataGraph)
    }

    private var currentPage : Int = 1

    private lateinit var pagerAdapter: PagerAdapter

    private fun setDataBind(list:ArrayList<Grade>){
        mainChart.removeAllViews()
        dataGraph.removeAllViews()
        list.forEachIndexed { index, grade ->
            Log.d("weight","${grade.weight}")
            val textView = TextView(this)
            val params: TableRow.LayoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, grade.weight)
            textView.text = grade.text
            textView.id = grade.id
            textView.setTextColor(resources.getColor(R.color.white))
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            textView.setPadding(3)
            textView.setOnClickListener(this)
            textView.setBackgroundColor(resources.getColor(grade.color))
            textView.layoutParams = params
            mainChart.addView(textView)
        }

        for(i in 0..11){
            val textView = TextView(this)
            val params: TableRow.LayoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            if(i==0){
                textView.text = " ${i}%"
            }else{
                textView.text = "${i}0%"
            }
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10F)
            textView.setTextColor(resources.getColor(R.color.black))
            when(i){
                0 -> {
                    textView.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                }
                10 -> {
                    textView.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                }
                1,2,3,4,5 -> {
                    textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                }
                6,7,8,9 -> {
                    textView.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                }
            }
            /*if(i==10){
                textView.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            }else if(i==0){
                textView.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            }else{
                textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            }*/
            textView.layoutParams = params
            dataGraph.addView(textView)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add = findViewById<Button>(R.id.add)
        val remove = findViewById<Button>(R.id.remove)
        val viewOne = findViewById<Button>(R.id.viewOne)
        val viewTwo = findViewById<Button>(R.id.viewTwo)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.addTab(tabLayout.newTab().setText("One"))
        tabLayout.addTab(tabLayout.newTab().setText("Two"))
        pagerAdapter = PagerAdapter(supportFragmentManager,tabLayout.tabCount)
        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        val list : ArrayList<Grade> = arrayListOf()
        list.add(Grade(0,5f,"F",R.color.red_light))
        list.add(Grade(1,5f,"A",R.color.v2_colorAccent_light))
        setDataBind(list)

        add.setOnClickListener {
            when(list.size){
                2 -> {
                    list[1].weight = 2.5f
                    list.add(Grade(2,2.5f,"A*",R.color.cardYellow))
                    setDataBind(list)
                }
                3 -> {

                }
                4 ->{

                }
                5 ->{

                }
                6 ->{

                }
                7 ->{

                }
                8 ->{

                }
            }
        }

        remove.setOnClickListener {
            if(list.size==2){
                return@setOnClickListener
            }
            list[1].weight = 5f
            list.removeAt(list.lastIndex)
            setDataBind(list)

        }

        viewOne.setOnClickListener {
            currentPage = 1
        }

        viewTwo.setOnClickListener {
            currentPage = 2
        }
        Log.d("lifecycle","onCreate")

    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle","onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle","onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","onResume")

    }

    data class Grade(
        val id : Int,
        var weight:Float,
        val text : String,
        val color : Int,
    )

    override fun onClick(v: View?) {
        when(v?.id){
            0 -> {
                Toast.makeText(this,"0 click",Toast.LENGTH_SHORT).show()
            }
            1 -> {
                Toast.makeText(this,"1 click",Toast.LENGTH_SHORT).show()
            }
            2 -> {
                Toast.makeText(this,"2 click",Toast.LENGTH_SHORT).show()
            }
        }
    }
}