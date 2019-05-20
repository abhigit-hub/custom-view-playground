package com.footinit.customviewplayground.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.footinit.customviewplayground.R

class CustomView_03 : RelativeLayout {

    private var isPlusButtonPressed = false
    private var isMinusButtonPressed = false


    private lateinit var tvValue: TextView
    private lateinit var root: View
    private lateinit var plusButton: View
    private lateinit var minusButton: View
    private var callback: ValueChangeListener? = null

    var MIN_VALUE = Integer.MIN_VALUE
    var MAX_VALUE = Integer.MAX_VALUE

    companion object {
        const val REPEAT_INTERVAL_MS: Long = 101
    }

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(attrs)
    }

    fun getMinValue(): Int {
        return MIN_VALUE
    }

    fun setMinValue(value: Int) {
        MIN_VALUE = value
    }

    fun getMaxValue(): Int {
        return MAX_VALUE
    }

    fun setMaxValue(value: Int) {
        MAX_VALUE = value
    }

    fun getCurrentValue(): Int {
        return tvValue.text.toString().toInt()
    }

    fun setCurrentValue(newValue: Int) {
        var value = newValue
        if (value < MIN_VALUE) {
            value = MIN_VALUE
        } else if (value > MAX_VALUE) {
            value = MAX_VALUE
        }

        tvValue.text = value.toString()
    }

    fun setValueChangeListener(listener: ValueChangeListener) {
        callback = listener
    }

    fun removeValueChangeListener() {
        callback = null
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs == null)
            return

        root = View.inflate(context, R.layout.value_selector, this)
        tvValue = root.findViewById(R.id.valueTextView)
        plusButton = root.findViewById(R.id.plusButton)
        minusButton = root.findViewById(R.id.minusButton)

        plusButton.setOnClickListener {
            increment()
        }

        plusButton.setOnLongClickListener {
            isPlusButtonPressed = true
            handler.post(AutoIncrementer())
            false
        }

        plusButton.setOnTouchListener { v: View?, event: MotionEvent? ->
            event?.let {
                if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL)
                    isPlusButtonPressed = false
            }

            false
        }

        minusButton.setOnClickListener {
            decrement()
        }

        minusButton.setOnLongClickListener {
            isMinusButtonPressed = true
            handler.post(AutoDecrementer())
            false
        }

        minusButton.setOnTouchListener { v: View?, event: MotionEvent? ->
            event?.let {
                if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL)
                    isMinusButtonPressed = false
            }
            false
        }
    }

    private fun increment() {
        val value = tvValue.text.toString().toInt()
        if (value < MAX_VALUE) {
            callback?.onValueChanged(value + 1)
            tvValue.text = (value + 1).toString()
        }
    }

    private fun decrement() {
        val value = tvValue.text.toString().toInt()
        if (value > MIN_VALUE) {
            callback?.onValueChanged(value - 1)
            tvValue.text = (value - 1).toString()
        }
    }

    interface ValueChangeListener {
        fun onValueChanged(value: Int)
    }

    inner class AutoIncrementer : Runnable {
        override fun run() {
            if (isPlusButtonPressed) {
                increment()
                handler.postDelayed(AutoIncrementer(), REPEAT_INTERVAL_MS)
            }
        }
    }

    inner class AutoDecrementer : Runnable {
        override fun run() {
            if (isMinusButtonPressed) {
                decrement()
                handler.postDelayed(AutoDecrementer(), REPEAT_INTERVAL_MS)
            }
        }
    }
}