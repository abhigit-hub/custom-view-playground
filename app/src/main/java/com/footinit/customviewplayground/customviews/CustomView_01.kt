package com.footinit.customviewplayground.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.footinit.customviewplayground.R

class CustomView_01 : View {

    private val DEFAULT_COLOR = Color.DKGRAY
    private val VIEW_CHANGED = true
    private val VIEW_NOT_CHANGED = false

    private lateinit var paint: Paint
    private lateinit var rect: Rect

    companion object {
        private var squareColor: Int = 0
        private var mPadding = 0
        private val originX = 0
        private val originY = 0
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

    private fun init(set: AttributeSet?) {
        if (set == null)
            return

        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        rect = Rect()

        val typedArray = context.obtainStyledAttributes(set, R.styleable.CustomView_01)
        squareColor = typedArray.getColor(R.styleable.CustomView_01_square_color, DEFAULT_COLOR)
        paint.color = squareColor
        typedArray.recycle()
    }

    fun swapColor() {
        paint.color = if (paint.color == squareColor) Color.BLACK else squareColor
        postInvalidate()
    }

    fun paddingUp(padding: Int): Boolean {
        if (isPaddingWithinBounds(mPadding + padding)) {
            mPadding += padding
            postInvalidate()
            return VIEW_CHANGED
        }
        return VIEW_NOT_CHANGED
    }

    fun paddingDown(padding: Int): Boolean {
        if (isPaddingWithinBounds(mPadding - padding)) {
            mPadding -= padding
            postInvalidate()
            return VIEW_CHANGED
        }
        return VIEW_NOT_CHANGED
    }

    private fun isPaddingWithinBounds(padding: Int): Boolean {
        return checkBoundsForPaddingUp(padding) && checkBoundsForPaddingDown(padding)
    }

    private fun checkBoundsForPaddingUp(padding: Int): Boolean {
        return (originX + padding < width - padding) && (originY + padding < height - padding)
    }

    private fun checkBoundsForPaddingDown(padding: Int): Boolean {
        return padding > 0
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setViewBounds()
        canvas?.drawRect(rect, paint)
    }

    private fun setViewBounds() {
        rect.left = originX + mPadding
        rect.right = width - mPadding
        rect.top = originY + mPadding
        rect.bottom = height - mPadding
    }
}