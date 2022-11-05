package com.example.peerproj

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity() {
    private var appLayouts: MutableList<LinearLayout> = ArrayList()
    private var laysIniCols: MutableList<Int> = ArrayList()

    fun onProgCh(prog: Int) {
        var hsvCols = FloatArray(3)

        for (i in 0 until appLayouts.size) {
            Color.colorToHSV(laysIniCols[i], hsvCols)
            hsvCols[0] = (hsvCols[0] + prog) % 360
            appLayouts[i].setBackgroundColor(Color.HSVToColor(hsvCols))
        }
    }

    fun getAllLayouts(layoutId: Int) {
        val currViewGroup: ViewGroup? = findViewById(layoutId) ?: return

        for (i in 0..currViewGroup!!.childCount) {
            val child = currViewGroup.getChildAt(i)
            if (child is LinearLayout) {
                getAllLayouts(child.id)
                appLayouts.add(child)
            }
        }
    }

    fun getAllCols() {
        for (i in 0 until appLayouts.size) {
            val origCol: Int = (appLayouts[i].background as ColorDrawable).color
            laysIniCols.add(origCol)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllLayouts(R.id.constraint_layout)
        getAllCols()
        Log.e("ads", "qwerty")

        val seekBar: SeekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, prog: Int, fromUser: Boolean) {
                onProgCh(prog)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_modern_art, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId

        if (id == R.id.action_more_information) {
            val dialFrag: DialogFragment = MoreInfoDialogFragment()
            dialFrag.show(supportFragmentManager, "more-info")
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}