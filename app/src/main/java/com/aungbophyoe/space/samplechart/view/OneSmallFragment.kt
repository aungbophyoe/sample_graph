package com.aungbophyoe.space.samplechart.view

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
        /*val chart = view.findViewById<BarChart>(R.id.chart)
        val myData : ArrayList<Grading> = arrayListOf()
        val entries : ArrayList<BarEntry> = arrayListOf()
        myData.add(Grading("C",50))
        myData.add(Grading("A",78))
        myData.add(Grading("B",67))
        myData.forEachIndexed { index, grading ->
            entries.add(BarEntry(index.toFloat(),(grading.percent).toFloat()))
        }
        val dataSet = BarDataSet(entries,"Grading")
        val barData = BarData(dataSet)
        barData.barWidth = 0.1f
        chart.data = barData
        chart.setFitBars(true)
        chart.invalidate()*/
        val anyChart = view.findViewById<AnyChartView>(R.id.any_chart_view)
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
        anyChart.setLicenceKey("")
    }

    data class Grading(
        val grade : String,
        val percent : Int
    )
}