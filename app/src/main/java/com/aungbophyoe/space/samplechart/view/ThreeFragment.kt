package com.aungbophyoe.space.samplechart.view

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.aungbophyoe.space.samplechart.R
import kotlin.math.ceil

class ThreeFragment : Fragment(R.layout.fragment_three),View.OnClickListener {
    private val dataList : ArrayList<AssignmentQuestion> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainChart = view.findViewById<LinearLayout>(R.id.nextChart)
        dataList.add(AssignmentQuestion(6,1))
        dataList.add(AssignmentQuestion(7,2))
        dataList.add(AssignmentQuestion(8,3))
        dataList.add(AssignmentQuestion(5,4))
        dataList.add(AssignmentQuestion(8,5))
        dataList.add(AssignmentQuestion(9,6))
        dataList.add(AssignmentQuestion(10,7))
        dataList.add(AssignmentQuestion(5,8))
        val rowCount = ceil((dataList.size/7).toDouble()).toInt()
        var count = 0
           for (i in 0..rowCount){
               var next = 0
                val linearLayout = LinearLayout(context)
                linearLayout.orientation = LinearLayout.HORIZONTAL
                linearLayout.weightSum = 7f
                val params: TableRow.LayoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
                linearLayout.layoutParams = params
               run bar@{
                   dataList.forEach { assignmentQuestion ->
                       if(count<dataList.size){
                           val textView = TextView(context)
                           val tvParams: TableRow.LayoutParams = TableRow.LayoutParams(90, 80)
                           textView.textSize = 7f
                           textView.text = "${dataList[count].score}/${dataList[count].score}"
                           textView.id = dataList[count].questionId
                           textView.setTextColor(resources.getColor(R.color.white))
                           textView.gravity = Gravity.CENTER
                           textView.setPadding(20)
                           tvParams.setMargins(1)
                           textView.setOnClickListener(this)
                           if(dataList[count].score<=5){
                               textView.setBackgroundColor(resources.getColor(R.color.v2_colorAccent_light))
                           }else{
                               textView.setBackgroundColor(resources.getColor(R.color.v2_colorAccent))
                           }
                           textView.layoutParams = tvParams
                           linearLayout.addView(textView)
                       }
                       count++
                       next++
                       val d = next%7
                       if(d==0){
                           mainChart.addView(linearLayout)
                           return@bar
                       }
                   }
                   mainChart.addView(linearLayout)
               }

            }

    }

    data class AssignmentQuestion(
        val score : Int,
        val questionId : Int
    )

    override fun onClick(v: View?) {
        Toast.makeText(context,"${v?.id} click", Toast.LENGTH_SHORT).show()
    }

}