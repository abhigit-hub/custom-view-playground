package com.footinit.customviewplayground.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.footinit.customviewplayground.R

class CustomView_05_01 : AppCompatEditText {

    var clearIconDrawable: Drawable? = null

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs == null)
            return

        clearIconDrawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_clear_opaque, null)

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showClearIcon()
            }

        })

        setOnTouchListener { v, event ->
            if (compoundDrawablesRelative[2] != null) {
                val clearButtonStart: Float
                var isClearButtonClicked = false

                if (layoutDirection == View.LAYOUT_DIRECTION_LTR) {
                    clearButtonStart = width - paddingEnd - clearIconDrawable!!.intrinsicWidth.toFloat()

                    if (event.x > clearButtonStart)
                        isClearButtonClicked = true
                }

                if (isClearButtonClicked) {
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        clearIconDrawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_clear_black, null)
                        showClearIcon()
                    }

                    if (event.action == MotionEvent.ACTION_UP) {
                        clearIconDrawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_clear_opaque, null)
                        text?.clear()
                        hideClearIcon()
                        return@setOnTouchListener true
                    }

                } else {
                    return@setOnTouchListener false
                }
            }
            false
        }
    }


    /*
    *
    * Hide Clear Icon
    * */
    private fun hideClearIcon() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,
            null,
            null,
            null
        )
    }

    /*
    *
    * Show Clear Icon
    * */
    private fun showClearIcon() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,
            null,
            clearIconDrawable,
            null
        )
    }
}