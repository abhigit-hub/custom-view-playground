package com.footinit.customviewplayground.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.footinit.customviewplayground.R
import kotlinx.android.synthetic.main.activity_03.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class Activity_03 : AppCompatActivity() {

    companion object {
        private val TAG = Activity_03::class.java.simpleName

        private val KEY_MESSAGE = "title"

        fun getStartIntent(context: Context, message: String): Intent {
            val intent = Intent(context, Activity_03::class.java)
            intent.putExtra(KEY_MESSAGE, message)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_03)

        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
            intent.getStringExtra(KEY_MESSAGE)?.let { title ->
                it.title = title
            }
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