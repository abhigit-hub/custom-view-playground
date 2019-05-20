package com.footinit.customviewplayground.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.footinit.customviewplayground.R
import kotlinx.android.synthetic.main.activity_05.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class Activity_06 : AppCompatActivity() {

    companion object {
        val TAG = Activity_06::class.java.simpleName

        val KEY_MESSAGE = "title"

        fun getStartIntent(context: Context, message: String): Intent {
            val intent = Intent(context, Activity_06::class.java)
            intent.putExtra(KEY_MESSAGE, message)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_06)

        setUpToolbar()
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