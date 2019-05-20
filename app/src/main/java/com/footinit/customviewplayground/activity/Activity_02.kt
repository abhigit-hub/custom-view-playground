package com.footinit.customviewplayground.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.footinit.customviewplayground.R
import com.footinit.customviewplayground.customviews.CustomView_02_05
import kotlinx.android.synthetic.main.activity_02.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class Activity_02 : AppCompatActivity() {

    companion object {
        private val TAG = Activity_02::class.java.simpleName

        private val KEY_MESSAGE = "title"

        fun getStartIntent(context: Context, message: String): Intent {
            val intent = Intent(context, Activity_02::class.java)
            intent.putExtra(KEY_MESSAGE, message)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_02)

        setUpToolbar()

        setUpViews()
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

    private fun setUpViews() {
        cv02_05_happy_state.setOnClickListener {
            cv02_05.happinessState = CustomView_02_05.HAPPY
        }

        cv02_05_sad_state.setOnClickListener {
            cv02_05.happinessState = CustomView_02_05.SAD
        }
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