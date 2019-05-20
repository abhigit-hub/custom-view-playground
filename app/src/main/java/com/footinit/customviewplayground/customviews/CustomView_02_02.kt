package com.footinit.customviewplayground.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CustomView_02_02 : View {

    private lateinit var paint: Paint
    private lateinit var rect: RectF
    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK
    private var borderWidth = 4.0f
    private var size = 320

    companion object {
        const val ONE_THIRD_LOWER_PADDING_ONE = 0.31f
        const val ONE_THIRD_UPPER_PADDING_ONE = 0.35f
        const val TWO_THIRD_LOWER_PADDING_ONE = 0.65f
        const val TWO_THIRD_UPPER_PADDING_ONE = 0.68f
        const val ONE_FIFTH = 0.2f
        const val FOUR_FIFTH = 0.8f
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

        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        rect = RectF()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas) {

        /*
        * Face
        * */
        val radius = size / 2f

        paint.color = faceColor
        paint.style = Paint.Style.FILL

        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        /*
        * Face Border
        * */
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth, paint)
    }

    private fun drawEyes(canvas: Canvas) {
        paint.color = eyesColor
        paint.style = Paint.Style.FILL


        rect.left = size * ONE_THIRD_LOWER_PADDING_ONE
        rect.right = size * ONE_THIRD_UPPER_PADDING_ONE
        rect.top = size * ONE_THIRD_LOWER_PADDING_ONE
        rect.bottom = size * ONE_THIRD_UPPER_PADDING_ONE
        canvas.drawRect(rect, paint)

        rect.left = size * TWO_THIRD_LOWER_PADDING_ONE
        rect.right = size * TWO_THIRD_UPPER_PADDING_ONE
        rect.top = size * ONE_THIRD_LOWER_PADDING_ONE
        rect.bottom = size * ONE_THIRD_UPPER_PADDING_ONE
        canvas.drawRect(rect, paint)
    }

    private fun drawMouth(canvas: Canvas) {
        paint.color = mouthColor
        paint.style = Paint.Style.FILL

        rect.left = size * ONE_FIFTH
        rect.right = size * FOUR_FIFTH
        rect.top = size * TWO_THIRD_LOWER_PADDING_ONE
        rect.bottom = size * TWO_THIRD_UPPER_PADDING_ONE
        canvas.drawRect(rect, paint)
    }
}