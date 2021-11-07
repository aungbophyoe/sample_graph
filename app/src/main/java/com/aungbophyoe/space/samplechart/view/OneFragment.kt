package com.aungbophyoe.space.samplechart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aungbophyoe.space.samplechart.R

private const val CLASSROOM_ID = "classId"
class OneFragment : Fragment(R.layout.fragment_one) {
    private var classroomId : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            classroomId = it.getString(CLASSROOM_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvData = view.findViewById<TextView>(R.id.tvData)
        tvData.text = "bababababa ${classroomId}"
    }
}