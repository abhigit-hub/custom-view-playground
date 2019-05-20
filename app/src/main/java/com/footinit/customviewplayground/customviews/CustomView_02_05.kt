package com.footinit.customviewplayground.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.footinit.customviewplayground.R

class CustomView_02_05 : View {

    private lateinit var paint: Paint
    private lateinit var mouthPath: Path
    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH
    private var size = 150 * DEFAULT_SIZE_MULTIPLIER
    var happinessState = HAPPY
        set(state) {
            field = state
            invalidate()
        }

    companion object {
        const val HAPPY = 0L
        const val SAD = 1L

        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f
        private const val DEFAULT_SIZE_MULTIPLIER = 1.0f

        /*
        * MEASUREMENTS for drawing on Canvas
        * */
        const val ONE_THIRD = 0.33f
        const val TWO_THIRD = 0.66f
        const val ZERO = 0f
        const val HALF = 0.5f
        const val FULL = 1f

        const val ONE_THIRD_LOWER_PADDING_ONE = 0.31f
        const val ONE_THIRD_UPPER_PADDING_ONE = 0.35f
        const val TWO_THIRD_LOWER_PADDING_ONE = 0.65f
        const val TWO_THIRD_UPPER_PADDING_ONE = 0.68f
        const val ONE_THIRD_LOWER_PADDING_TWO = 0.23f
        const val ONE_THIRD_UPPER_PADDING_TWO = 0.43f
        const val TWO_THIRD_LOWER_PADDING_TWO = 0.56f
        const val TWO_THIRD_UPPER_PADDING_TWO = 0.76f

        const val ONE_FIFTH = 0.2f
        const val THREE_FIFTH = 0.6f
        const val FOUR_FIFTH = 0.8f
        const val ONE_TENTH = 0.1f
        const val SEVEN_TENTH = 0.7f
        const val NINE_TENTH = 0.9f
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
        paint.isAntiAlias = true
        mouthPath = Path()
        setUpAttributes(attrs)
    }

    private fun setUpAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomView_02_05)

            happinessState = typedArray.getInt(R.styleable.CustomView_02_05_state, HAPPY.toInt()).toLong()
            faceColor = typedArray.getColor(R.styleable.CustomView_02_05_faceColor, DEFAULT_FACE_COLOR)
            eyesColor = typedArray.getColor(R.styleable.CustomView_02_05_eyesColor, DEFAULT_EYES_COLOR)
            mouthColor = typedArray.getColor(R.styleable.CustomView_02_05_mouthColor, DEFAULT_MOUTH_COLOR)
            borderColor = typedArray.getColor(R.styleable.CustomView_02_05_borderColor, DEFAULT_BORDER_COLOR)
            borderWidth = typedArray.getDimension(R.styleable.CustomView_02_05_borderWidth, DEFAULT_BORDER_WIDTH)
            size *= typedArray.getFloat(R.styleable.CustomView_02_05_sizeMultiplier, DEFAULT_SIZE_MULTIPLIER)

            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mouthPath.reset()

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

        val leftEyeRect = RectF(
            size * ONE_THIRD_LOWER_PADDING_ONE,
            size * ONE_FIFTH,
            size * ONE_THIRD_UPPER_PADDING_TWO,
            size * TWO_THIRD_LOWER_PADDING_TWO
        )
        canvas.drawOval(leftEyeRect, paint)

        val rightEyeRect = RectF(
            size * TWO_THIRD_LOWER_PADDING_TWO,
            size * ONE_FIFTH,
            size * TWO_THIRD_UPPER_PADDING_ONE,
            size * TWO_THIRD_LOWER_PADDING_TWO
        )
        canvas.drawOval(rightEyeRect, paint)
    }

    private fun drawMouth(canvas: Canvas) {
        paint.color = mouthColor
        paint.style = Paint.Style.FILL

        mouthPath.moveTo(size * ONE_FIFTH, size * TWO_THIRD_UPPER_PADDING_TWO)

        if (happinessState == HAPPY) {
            // Happy mouth path
            mouthPath.quadTo(size * HALF, size * FULL, size * FOUR_FIFTH, size * TWO_THIRD_UPPER_PADDING_TWO)
            mouthPath.quadTo(size * HALF, size * (FULL + 0.1f), size * ONE_FIFTH, size * TWO_THIRD_UPPER_PADDING_TWO)
        } else {
            // Sad mouth path
            mouthPath.quadTo(size * HALF, size * HALF, size * FOUR_FIFTH, size * TWO_THIRD_UPPER_PADDING_TWO)
            mouthPath.quadTo(size * HALF, size * THREE_FIFTH, size * ONE_FIFTH, size * TWO_THIRD_UPPER_PADDING_TWO)
        }
        canvas.drawPath(mouthPath, paint)
    }
}