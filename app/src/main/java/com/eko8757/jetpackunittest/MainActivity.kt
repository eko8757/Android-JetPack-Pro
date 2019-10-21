package com.eko8757.jetpackunittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(CuboidModel())

        //click listener
        btn_save.setOnClickListener(this)
        btn_calculate_circumference.setOnClickListener(this)
        btn_calculate_surface_area.setOnClickListener(this)
        btn_calculate_volume.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val length: String = ed_length.text.toString().trim()
        val width: String = ed_width.text.toString().trim()
        val height: String = ed_height.text.toString().trim()

        //validation edittext
        if (TextUtils.isEmpty(length)) {
            ed_length.setError("Field not be empty")
        } else if (TextUtils.isEmpty(width)) {
            ed_width.setError("Field not be empty")
        } else if (TextUtils.isEmpty(height)) {
            ed_height.setError("Field not be empty")
        } else {
            val l = java.lang.Double.parseDouble(length)
            val w = java.lang.Double.parseDouble(width)
            val h = java.lang.Double.parseDouble(height)

            when (v?.id) {
                R.id.btn_save -> {
                    mainViewModel.save(l, w, h)
                    visible()
                }
                R.id.btn_calculate_circumference -> {
                    tv_result.setText(mainViewModel.circumference.toString())
                    gone()
                }
                R.id.btn_calculate_surface_area -> {
                    tv_result.setText(mainViewModel.surfaceArea.toString())
                    gone()
                }
                R.id.btn_calculate_volume -> {
                    tv_result.setText(mainViewModel.volume.toString())
                    gone()
                }
            }
        }
    }

    fun visible() {
        btn_calculate_volume.visible()
        btn_calculate_surface_area.visible()
        btn_calculate_circumference.visible()
        btn_save.gone()
    }

    fun gone() {
        btn_calculate_volume.gone()
        btn_calculate_surface_area.gone()
        btn_calculate_circumference.gone()
        btn_save.visible()
    }
}
