package com.aungbophyoe.space.samplechart.view

import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.aungbophyoe.space.samplechart.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.anychart.chart.common.dataentry.ValueDataEntry

import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Column
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position

import com.anychart.enums.TooltipPositionMode
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler


private const val CLASSROOM_ID = "classId"
class OneSmallFragment : Fragment(R.layout.fragment_small_one) {
    private var classroomId : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            classroomId = it.getString(CLASSROOM_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chart = view.findViewById<BarChart>(R.id.barChart)
        val myData : ArrayList<Grading> = arrayListOf()
        val entries : ArrayList<BarEntry> = arrayListOf()
        val xLabels : ArrayList<String > = arrayListOf()
        val yLabels : ArrayList<String> = arrayListOf()
        yLabels.add("0%")
        yLabels.add("20%")
        yLabels.add("40%")
        yLabels.add("60%")
        yLabels.add("80%")
        yLabels.add("100%")
        myData.add(Grading(1,"C",50))
        myData.add(Grading(2,"A",78))
        myData.add(Grading(3,"B",67))
        myData.add(Grading(4,"A*",92))
        myData.add(Grading(5,"A",82))
        myData.forEachIndexed { index, grading ->
            entries.add(BarEntry(index.toFloat(),(grading.percent).toFloat()))
            xLabels.add("${grading.assignmentNo}")
        }
        val dataSet = BarDataSet(entries,"Grading")
//        dataSet.color = resources.getColor(R.color.v2_colorAccent_light)
        dataSet.setGradientColor(resources.getColor(R.color.v2_colorAccent),resources.getColor(R.color.v2_colorAccent_light))
        dataSet.valueFormatter = XAxisValueFormatter()
        dataSet.valueTextColor = resources.getColor(R.color.v2_colorAccent_light)
        val barData = BarData(dataSet)
        barData.barWidth = 0.1f
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


        /*val anyChart = view.findViewById<AnyChartView>(R.id.any_chart_view)
        val cartesian: Cartesian = AnyChart.column()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("1", 50))
        data.add(ValueDataEntry("2", 60))
        data.add(ValueDataEntry("3", 75))
        data.add(ValueDataEntry("4", 45))
        data.add(ValueDataEntry("5", 90))
        data.add(ValueDataEntry("6", 85))

        val column: Column = cartesian.column(data)
        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0.0)
            .offsetY(1.0)
            .format("{%Value}{groupsSeparator: }%")

        cartesian.animation(true)
        cartesian.title("Grading")

        cartesian.yScale().minimum(0.0)

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }%")

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)

        cartesian.xAxis(0).title("Lecture Assignments")
        cartesian.yAxis(0).title("Grade")
        anyChart.setChart(cartesian)
        anyChart.setLicenceKey("")*/
    }

    class XAxisValueFormatter : ValueFormatter(){
        override fun getFormattedValue(value: Float): String {
            return "${value.toInt()}%"
        }

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            /*axis?.setLabelCount(0,true)*/
            return "${value.toInt()}%"
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
        val assignmentNo: Int,
        val grade : String,
        val percent : Int
    )
}