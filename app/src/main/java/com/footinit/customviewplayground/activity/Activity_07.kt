package com.footinit.customviewplayground.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.footinit.customviewplayground.R
import com.footinit.customviewplayground.customviews.CustomView_03
import kotlinx.android.synthetic.main.activity_05.toolbar
import kotlinx.android.synthetic.main.activity_07.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class Activity_07 : AppCompatActivity(), CustomView_03.ValueChangeListener {

    companion object {
        val TAG = Activity_07::class.java.simpleName

        val KEY_MESSAGE = "title"

        fun getStartIntent(context: Context, message: String): Intent {
            val intent = Intent(context, Activity_07::class.java)
            intent.putExtra(KEY_MESSAGE, message)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_07)

        setUpToolbar()

        setUpViews()
    }

    private fun setUpViews() {
        cv03.setMinValue(1)
        cv03.setMaxValue(10)
        cv03.setValueChangeListener(this)
        cv03.setCurrentValue(cv07.getSelectionCount())
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            intent.getStringExtra(KEY_MESSAGE)?.let { title ->
                it.title = title
            }
        }
    }

    override fun onValueChanged(value: Int) {
        if (value > 0) cv07.selSelectionCount(value)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}