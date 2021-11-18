package com.aungbophyoe.space.samplechart.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.aungbophyoe.space.samplechart.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.model.GradientColor

class TwoSmallFragment:Fragment(R.layout.fragment_small_two) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chart = view.findViewById<BarChart>(R.id.barChart)
        val myData : ArrayList<Grading> = arrayListOf()
        val entries : ArrayList<BarEntry> = arrayListOf()
        val xLabels : ArrayList<String > = arrayListOf()
        val yLabels : ArrayList<String> = arrayListOf()
        val colors : ArrayList<GradientColor> = arrayListOf()
        val bars : ArrayList<IBarDataSet> = arrayListOf()
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
            entries.add(BarEntry(index.toFloat(),(grading.percent).toFloat()))
            xLabels.add("${grading.assignmentNo}")
            if(index==myData.size-1){
                colors.add(GradientColor(resources.getColor(R.color.v2_colorAccent),resources.getColor(R.color.v2_colorAccent_light)))
            }else{
                colors.add(GradientColor(resources.getColor(R.color.teal_700),resources.getColor(R.color.green_light)))
            }
        }

        val dataSet = BarDataSet(entries,"Grading")
        dataSet.gradientColors = colors
        /*dataSet.setGradientColor(resources.getColor(R.color.v2_colorAccent),resources.getColor(R.color.v2_colorAccent_light))*/
        dataSet.valueFormatter = XAxisValueFormatter()
        dataSet.valueTextColor = resources.getColor(R.color.tintColor)
        bars.add(dataSet)

        /*val lastDataSet = BarDataSet(lastEntries,"Avg")
        *//*lastDataSet.setGradientColor(resources.getColor(R.color.teal_700),resources.getColor(R.color.green_light))*//*
        lastDataSet.valueFormatter = XAxisValueFormatter()
        lastDataSet.valueTextColor = resources.getColor(R.color.green_light)
        bars.add(lastDataSet)*/

        val barData = BarData(bars)
        barData.barWidth = 0.2f
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
        chart.setFitBars(true)
        chart.description.isEnabled = false
        chart.legend.isEnabled=false
        //chart.setDrawGridBackground(true)
        chart.animateY(2000)
        chart.invalidate()
    }

    class XAxisValueFormatter : ValueFormatter(){

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

    class MyAxisValueFormatter : ValueFormatter() {

        /*override fun getFormattedValue(value: Float): String {
            return "${value.toInt()}%"
        }*/

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            axis?.setLabelCount(6,true)
            return "${value.toInt()}%"
        }
    }

    data class Grading(
        val assignmentNo: String,
        val grade : String,
        val percent : Int
    )
}