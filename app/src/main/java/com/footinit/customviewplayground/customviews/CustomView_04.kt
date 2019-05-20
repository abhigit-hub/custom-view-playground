package com.footinit.customviewplayground.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.footinit.customviewplayground.R
import kotlinx.android.synthetic.main.item_main.view.*

class CustomView_04 : FrameLayout {

    private var toggle: Boolean = false
    private lateinit var plusIcon: Drawable
    private lateinit var minusIcon: Drawable
    private lateinit var tvTopBar: TextView
    private lateinit var tvBottomBar: TextView
    private lateinit var topBar: View
    private lateinit var bottomBar: ViewGroup
    private lateinit var ivToggle: ImageView
    private val defaultColor = resources.getColor(R.color.colorPrimary)

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
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

        val root = View.inflate(context, R.layout.reveal_view, this)
        tvTopBar = root.findViewById(R.id.top_bar_title)
        tvBottomBar = root.findViewById(R.id.bottom_bar_text)
        ivToggle = root.findViewById(R.id.toggle_button)
        topBar = root.findViewById(R.id.topBar)
        bottomBar = root.findViewById(R.id.bottomBar)

        topBar.setOnClickListener(setToggleListener(ivToggle, tvBottomBar))

        plusIcon = resources.getDrawable(R.drawable.valueselect_plus)
        minusIcon = resources.getDrawable(R.drawable.valueselect_minus)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView_04)
        toggle = typedArray.getBoolean(R.styleable.CustomView_04_showMessage, false)
        bottomBar.visibility = if (toggle) View.VISIBLE else View.GONE
        ivToggle.setImageDrawable(
            if (toggle) resources.getDrawable(R.drawable.valueselect_minus)
            else resources.getDrawable(R.drawable.valueselect_plus)
        )

        typedArray.getString(R.styleable.CustomView_04_barTitle)?.let {
            tvTopBar.text = it
        }

        typedArray.getString(R.styleable.CustomView_04_message)?.let {
            tvBottomBar.text = it
        }

        typedArray.getColor(R.styleable.CustomView_04_colorBar, defaultColor)?.let {
            topBar.setBackgroundColor(it)
        }

        typedArray.getColor(R.styleable.CustomView_04_contentBar, defaultColor)?.let {
            bottomBar.setBackgroundColor(it)
        }

        typedArray.recycle()
    }

    fun setTitle(title: String) {
        tvTopBar.text = title
    }

    fun setMessage(message: String) {
        tvBottomBar.text = message
    }

    fun setTitleBarColor(color: Int) {
        topBar.setBackgroundColor(resources.getColor(color))
    }

    fun setBottomBarColor(color: Int) {
        bottomBar.setBackgroundColor(resources.getColor(color))
    }

    private fun setToggleListener(toggleButton: ImageView, bottomBar: View): OnClickListener {
        return OnClickListener {
            var drawable = minusIcon
            var visibility = View.VISIBLE
            if (toggle) {
                drawable = plusIcon
                visibility = View.GONE
                toggle = false
            } else {
                toggle = true
            }
            toggleButton.setImageDrawable(drawable)
            bottomBar.visibility = visibility
        }
    }
}