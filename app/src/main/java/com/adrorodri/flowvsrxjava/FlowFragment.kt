package com.adrorodri.flowvsrxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion

class FlowFragment : Fragment() {
    private val uiScope = MainScope()

    companion object {
        fun newInstance(): FlowFragment {
            return FlowFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.button).setOnClickListener {
            startTest(System.currentTimeMillis())
        }
    }

    private fun appendText(text: String) {
        view?.findViewById<TextView>(R.id.tvLogs)?.append("\n$text")
    }

    private fun startTest(timestampStart: Long) {
        appendText("Test started")
        IntRange(0, 10000).asFlow().onCompletion {
            val timestampEnd = System.currentTimeMillis()
            appendText("Test ended in ${timestampEnd - timestampStart} ms")
        }.launchIn(uiScope)
    }
}