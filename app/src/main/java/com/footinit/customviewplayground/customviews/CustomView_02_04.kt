package com.footinit.customviewplayground.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CustomView_02_04 : View {

    private lateinit var paint: Paint
    private lateinit var rect: RectF
    private var faceColor = Color.YELLOW
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK
    private var glassesColor = Color.BLACK
    private var circleBorderWidth = 4.0f
    private var lineBorderWidth = 8.0f
    private var size = 320

    companion object {
        const val ONE_THIRD = 0.33f
        const val TWO_THIRD = 0.66f

        const val ONE_THIRD_LOWER_PADDING_ONE = 0.31f
        const val ONE_THIRD_UPPER_PADDING_ONE = 0.35f
        const val TWO_THIRD_LOWER_PADDING_ONE = 0.64f
        const val TWO_THIRD_UPPER_PADDING_ONE = 0.68f
        const val ONE_THIRD_LOWER_PADDING_TWO = 0.21f
        const val ONE_THIRD_UPPER_PADDING_TWO = 0.45f
        const val TWO_THIRD_LOWER_PADDING_TWO = 0.54f
        const val TWO_THIRD_UPPER_PADDING_TWO = 0.78f
        const val LOWEST_PADDING = 0.05f
        const val HIGHEST_PADDING = 0.95f
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
        drawMouth(canvas)
        drawGlasses(canvas)
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
        paint.strokeWidth = circleBorderWidth
        canvas.drawCircle(size / 2f, size / 2f, radius - circleBorderWidth, paint)
    }

    private fun drawMouth(canvas: Canvas) {
        paint.color = mouthColor
        paint.style = Paint.Style.FILL

        rect.left = size * ONE_THIRD_LOWER_PADDING_ONE
        rect.right = size * TWO_THIRD_UPPER_PADDING_ONE
        rect.top = size * TWO_THIRD_LOWER_PADDING_ONE
        rect.bottom = size * TWO_THIRD_UPPER_PADDING_ONE
        canvas.drawRect(rect, paint)
    }

    private fun drawGlasses(canvas: Canvas) {
        paint.color = glassesColor

        rect.left = size * ONE_THIRD_LOWER_PADDING_TWO
        rect.right = size * ONE_THIRD_UPPER_PADDING_TWO
        rect.top = size * ONE_THIRD_LOWER_PADDING_TWO
        rect.bottom = size * ONE_THIRD_UPPER_PADDING_TWO
        canvas.drawRect(rect, paint)

        rect.left = size * TWO_THIRD_LOWER_PADDING_TWO
        rect.right = size * TWO_THIRD_UPPER_PADDING_TWO
        rect.top = size * ONE_THIRD_LOWER_PADDING_TWO
        rect.bottom = size * ONE_THIRD_UPPER_PADDING_TWO
        canvas.drawRect(rect, paint)

        drawGlassLines(canvas)
    }

    private fun drawGlassLines(canvas: Canvas) {
        paint.strokeWidth = lineBorderWidth
        canvas.drawLine(
            size * ONE_THIRD, size * ONE_THIRD, size * TWO_THIRD,
            size * ONE_THIRD, paint
        )
        canvas.drawLine(
            size * ONE_THIRD_LOWER_PADDING_TWO, size * ONE_THIRD_LOWER_PADDING_TWO,
            size * LOWEST_PADDING,
            size * ONE_THIRD_UPPER_PADDING_ONE, paint
        )
        canvas.drawLine(
            size * TWO_THIRD_UPPER_PADDING_TWO, size * ONE_THIRD_LOWER_PADDING_TWO,
            size * HIGHEST_PADDING,
            size * ONE_THIRD_UPPER_PADDING_ONE, paint
        )
    }
}
