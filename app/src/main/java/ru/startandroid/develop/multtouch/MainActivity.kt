package ru.startandroid.develop.multtouch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.TextView

class MainActivity : AppCompatActivity(), OnTouchListener {

    private var sb: StringBuilder = StringBuilder()
    private lateinit var tv: TextView
    private var upPI = 0
    private var downPI = 0
    private var inTouch = false
    private var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tv = TextView(this)
        tv.textSize = 30F
        tv.setOnTouchListener(this)
        setContentView(tv)
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        val actionMask = event!!.actionMasked
        val pointIndex = event.actionIndex
        val pointCount = event.pointerCount

        when(actionMask) {
            MotionEvent.ACTION_DOWN -> inTouch = true
            MotionEvent.ACTION_POINTER_DOWN -> downPI = pointIndex
            MotionEvent.ACTION_UP -> inTouch = false
            MotionEvent.ACTION_MOVE -> {
                sb.setLength(0)

                for (i in 1..10) {
                    sb.append("Index = $i")
                    if (i < pointCount) {
                        sb.append(", ID = " + event.getPointerId(i))
                        sb.append(", X = " + event.getX(i))
                        sb.append(", Y = " + event.getY(i))
                    } else {
                        sb.append(", ID =")
                        sb.append(", X = ")
                        sb.append(", Y = ")
                    }
                    sb.append("\r\n")
                }
            }
        }
        result = "down: " + downPI + "\n" + "up: " + upPI + "\n"

        if (inTouch) {
            result += "pointerCount = " + pointCount + "\n" + sb.toString()
        }
        tv.text = result
        return true
    }
}