package com.footinit.customviewplayground.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.footinit.customviewplayground.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_01.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class Activity_01 : AppCompatActivity() {

    companion object {
        val TAG = Activity_01::class.java.simpleName

        val KEY_MESSAGE = "title"

        fun getStartIntent(context: Context, message: String): Intent {
            val intent = Intent(context, Activity_01::class.java)
            intent.putExtra(KEY_MESSAGE, message)
            return intent
        }

        private val paddingAmount = 30
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_01)

        setUpToolbar()

        setUpViews()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
            intent.getStringExtra(KEY_MESSAGE)?.let { title -> it.title = title }
        }
    }

    private fun setUpViews() {
        btnAdd.setOnClickListener {
            incrementPadding(paddingAmount)
        }

        btnRemove.setOnClickListener {
            decrementPadding(paddingAmount)
        }

        btnChangeColor.setOnClickListener {
            swapColor()
        }
    }

    private fun incrementPadding(paddingAmount: Int) {
        val isChanged = cv01.paddingUp(paddingAmount)
        if (!isChanged)
            showSnackBar(getString(R.string.bounds_exceeding))
    }

    private fun decrementPadding(paddingAmount: Int) {
        val isChanged = cv01.paddingDown(paddingAmount)
        if (!isChanged)
            showSnackBar(getString(R.string.bounds_exceeding))
    }

    private fun swapColor() {
        cv01.swapColor()
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

    private fun showSnackBar(text: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), text, 1000)
        val view = snackbar.view
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.red_color))
        snackbar.show()
    }
}