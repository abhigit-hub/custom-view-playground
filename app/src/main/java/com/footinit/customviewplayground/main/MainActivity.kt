package com.footinit.customviewplayground.main

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.footinit.customviewplayground.R
import com.footinit.customviewplayground.activity.*
import com.footinit.customviewplayground.model.CustomModel
import kotlinx.android.synthetic.main.activity_01.toolbar
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : AppCompatActivity(), MainGridAdapter.Callback {

    lateinit var adapter: MainGridAdapter

    lateinit var colors: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colors = resources.getStringArray(R.array.colors)

        setUpToolbar()

        setUpAdapter()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.app_name)
    }

    private fun setUpAdapter() {
        adapter = MainGridAdapter(prepareList(), this)
        val layoutManager = GridLayoutManager(applicationContext, 2)
        layoutManager.orientation = RecyclerView.VERTICAL
        rvMain.layoutManager = layoutManager
        rvMain.adapter = adapter
    }

    private fun prepareList(): ArrayList<CustomModel> {
        val list = ArrayList<CustomModel>()
        list.add(CustomModel("1", "Shapes (Square)", getRandomColor()))
        list.add(CustomModel("2", "Smiley (Basic drawxxx() Commands)", getRandomColor()))
        list.add(CustomModel("3", "Compound Views (using View Groups) Pt.1", getRandomColor()))
        list.add(CustomModel("4", "Compound Views (using View Groups) Pt.2", getRandomColor()))
        list.add(CustomModel("5", "Custom Views (using Views) Pt.1", getRandomColor()))
        list.add(CustomModel("6", "Custom Views (using Views) Pt.2", getRandomColor()))
        list.add(CustomModel("7", "Combining Shapes and Compound Views", getRandomColor()))
        return list
    }

    private fun getRandomColor(): Int {
        return Color.parseColor(colors[(0 until colors.size).random()])
    }

    override fun onItemSelected(id: Int, message: String) {
        when (id) {
            1 -> startActivity(Activity_01.getStartIntent(applicationContext, message))
            2 -> startActivity(Activity_02.getStartIntent(applicationContext, message))
            3 -> startActivity(Activity_03.getStartIntent(applicationContext, message))
            4 -> startActivity(Activity_04.getStartIntent(applicationContext, message))
            5 -> startActivity(Activity_05.getStartIntent(applicationContext, message))
            6 -> startActivity(Activity_06.getStartIntent(applicationContext, message))
            7 -> startActivity(Activity_07.getStartIntent(applicationContext, message))
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onResume() {
        super.onResume()
        adapter.setCallback(this@MainActivity)
    }

    override fun onPause() {
        adapter.removeCallback()
        super.onPause()
    }
}
