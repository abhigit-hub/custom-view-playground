package com.footinit.customviewplayground.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.footinit.customviewplayground.R


class CustomView_07 : View {

    private lateinit var textPaint: Paint
    private lateinit var dialPaint: Paint
    private var activeSelection = 0
    private var radius = 0f
    private var width = 0f
    private var height = 0f
    private var fanOnColor: Int = 0
    private var fanOffColor: Int = 0
    private var selectionCount: Int = 0
    private var defaultFanOnColor: Int = Color.GREEN
    private var defaultFanOffColor: Int = Color.GRAY

    private val mTempLabel = StringBuffer(8)
    private val mTempResult = FloatArray(2)

    companion object {
        const val SELECTION_COUNT = 4
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

    private fun init(attrs: AttributeSet?) {
        if (attrs == null)
            return

        textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint.style = Paint.Style.FILL_AND_STROKE
        textPaint.color = Color.BLACK
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = 40f

        dialPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        dialPaint.color = Color.GRAY

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView_07)
        fanOnColor = typedArray.getColor(R.styleable.CustomView_07_fanOnColor, defaultFanOnColor)
        fanOffColor = typedArray.getColor(R.styleable.CustomView_07_fanOffColor, defaultFanOffColor)
        selectionCount = typedArray.getInt(R.styleable.CustomView_07_selectionIndicators, SELECTION_COUNT)
        typedArray.recycle()

        setOnClickListener {
            activeSelection = (activeSelection + 1) % selectionCount

            if (activeSelection > 0) {
                dialPaint.color = fanOnColor
            } else {
                dialPaint.color = fanOffColor
            }
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //Draw Dial
        canvas?.drawCircle(width / 2, height / 2, radius, dialPaint)

        //Draw Labels
        val labelRadius = radius + 20
        val label = mTempLabel
        for (i in 0 until selectionCount) {
            val result = computeXYForPosition(i, labelRadius, true)
            val x = result[0]
            val y = result[1]

            label.setLength(0)
            label.append(i)
            canvas?.drawText(label, 0, label.length, x, y, textPaint)
        }

        //Draw Indicator Mark
        val markerRadius = radius - 35
        val result = computeXYForPosition(activeSelection, markerRadius, false)

        val x = result[0]
        val y = result[1]
        canvas?.drawCircle(x, y, 20f, textPaint)
    }

    private fun computeXYForPosition(pos: Int, radius: Float, isLabel: Boolean): FloatArray {
        val result = mTempResult
        val startAngle: Double?
        val angle: Double?
        if (selectionCount > 4) {
            startAngle = Math.PI * (3 / 2.0)
            angle = startAngle + pos * (Math.PI / selectionCount)
            result[0] = (radius * Math.cos(angle * 2)).toFloat() + width / 2
            result[1] = (radius * Math.sin(angle * 2)).toFloat() + height / 2
            if (angle > Math.toRadians(360.0) && isLabel) {
                result[1] += 20f
            }
        } else {
            startAngle = Math.PI * (9 / 8.0)
            angle = startAngle + pos * (Math.PI / selectionCount)
            result[0] = (radius * Math.cos(angle)).toFloat() + width / 2
            result[1] = (radius * Math.sin(angle)).toFloat() + height / 2
        }
        return result
    }

    fun selSelectionCount(count: Int) {
        selectionCount = count
        activeSelection = 0
        dialPaint.color = fanOffColor
        invalidate()
    }

    fun getSelectionCount(): Int {
        return selectionCount
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        width = w.toFloat()
        height = h.toFloat()
        radius = ((Math.min(width, height) / 2) * 0.8).toFloat()
    }
}