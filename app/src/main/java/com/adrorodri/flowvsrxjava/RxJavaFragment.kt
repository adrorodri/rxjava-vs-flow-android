package com.adrorodri.flowvsrxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class RxJavaFragment : Fragment() {

    companion object {
        fun newInstance(): RxJavaFragment {
            return RxJavaFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rxjava, container, false)
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
        val disposable =
            Observable.range(0, 10000).doFinally {
                val timestampEnd = System.currentTimeMillis()
                appendText("Test ended in ${timestampEnd - timestampStart} ms")
            }.subscribe()
    }
}