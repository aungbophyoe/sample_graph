package com.aungbophyoe.space.samplechart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.aungbophyoe.space.samplechart.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.model.GradientColor


class TwoFragment : Fragment(R.layout.fragment_two) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chart = view.findViewById<LineChart>(R.id.barChart)
        val myData : ArrayList<Grading> = arrayListOf()
        val entries : ArrayList<Entry> = arrayListOf()
        val xLabels : ArrayList<String > = arrayListOf()
        val yLabels : ArrayList<String> = arrayListOf()
        val colors : ArrayList<Int> = arrayListOf()
        yLabels.add("0%")
        yLabels.add("20%")
        yLabels.add("40%")
        yLabels.add("60%")
        yLabels.add("80%")
        yLabels.add("100%")
        myData.add(Grading("1", "C", 50))
        myData.add(Grading("2", "A", 78))
        myData.add(Grading("3", "B", 67))
        myData.add(Grading("4", "A*", 92))
        myData.add(Grading("5", "A", 82))
        myData.add(Grading("Total\nAvg", "A", 75))
        myData.forEachIndexed { index, grading ->
            entries.add(Entry(index.toFloat(),(grading.percent).toFloat()))
            xLabels.add("${grading.assignmentNo}")
            if(index==myData.size-1){
                /*colors.add(resources.getColor(R.color.v2_colorAccent_light))*/
                colors.add(resources.getColor(R.color.green_light))
            }else{
                colors.add(resources.getColor(R.color.v2_colorAccent_light))
                /*colors.add(GradientColor(resources.getColor(R.color.teal_700),resources.getColor(R.color.green_light)))*/
            }
        }

        val dataSet = LineDataSet(entries,"Grading")
        dataSet.setDrawCircles(true)
        /*dataSet.setDrawCircleHole(true)*/
        dataSet.color = resources.getColor(R.color.tintColor)
        /*dataSet.setCircleColor(resources.getColor(R.color.white))*/
        /*dataSet.circleHoleColor = resources.getColor(R.color.v2_colorAccent)*/
        dataSet.circleColors = colors
        dataSet.circleRadius = 4f
        /*dataSet.circleHoleRadius = 4f*/

        dataSet.valueFormatter = XAxisValueFormatter()
        dataSet.valueTextColor = resources.getColor(R.color.tintColor)


        val barData = LineData(dataSet)
        chart.data = barData


        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        /*xAxis.enableAxisLineDashedLine(1f,1f,1f)*/
        xAxis.granularity = 1f
        xAxis.valueFormatter = IndexAxisValueFormatter(xLabels)
        xAxis.labelCount = xLabels.size


        val yAxisLeft = chart.axisLeft
        yAxisLeft.setDrawGridLines(true)
        yAxisLeft.setDrawAxisLine(false)
        yAxisLeft.gridLineWidth = 1.5f
        yAxisLeft.gridColor = resources.getColor(R.color.tintColor)
        yAxisLeft.enableGridDashedLine(10f,10f,5f)
        yAxisLeft.axisMaximum = 100f
        yAxisLeft.axisMinimum = 0f
        yAxisLeft.valueFormatter = MyAxisValueFormatter()
        /*yAxisLeft.labelCount = yLabels.size*/
        /*yAxisLeft.setLabelCount(6,true)*/

        val yAxisRight  = chart.axisRight
        yAxisRight.valueFormatter = IndexAxisValueFormatter(yLabels)
        yAxisRight.setLabelCount(0,true)
        yAxisRight.axisMinimum = 0f
        yAxisRight.axisMaximum = 100f
        yAxisRight.setDrawGridLines(false)
        yAxisRight.setDrawAxisLine(false)
        yAxisRight.granularity = 1f



        chart.isDoubleTapToZoomEnabled = false
        chart.setPinchZoom(false)
        chart.setBackgroundColor(resources.getColor(R.color.white))
        chart.description.isEnabled = false
        chart.legend.isEnabled=false
        //chart.setDrawGridBackground(true)
        chart.animateY(2000)
        chart.invalidate()
    }

    private class XAxisValueFormatter : ValueFormatter(){

        override fun getFormattedValue(value: Float): String {
            /*return "${value.toInt()}%"*/
            return when(value.toInt()){
                in 0..19 -> {"E"}
                in 20..39 -> {"D"}
                in 40..59 -> {"C"}
                in 60..79 -> {"B"}
                in 80..100 -> {"A"}
                else -> "F"
            }
        }

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            /*axis?.setLabelCount(0,true)*/
            return when(value.toInt()){
                in 0..19 -> {"E"}
                in 20..39 -> {"D"}
                in 40..59 -> {"C"}
                in 60..79 -> {"B"}
                in 80..100 -> {"A"}
                else -> "F"
            }
        }
    }

    private class MyAxisValueFormatter : ValueFormatter() {

        /*override fun getFormattedValue(value: Float): String {
            return "${value.toInt()}%"
        }*/

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            axis?.setLabelCount(6,true)
            return "${value.toInt()}%"
        }
    }

    private data class Grading(
        val assignmentNo: String,
        val grade : String,
        val percent : Int
    )
}