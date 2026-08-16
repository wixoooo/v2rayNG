package com.v2ray.ang.ui.main

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.card.MaterialCardView
import com.v2ray.ang.R

class TagDropActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tagdrop)

        val btnTheme = findViewById<ImageButton>(R.id.btn_sun_moon)
        val fab = findViewById<FloatingActionButton>(R.id.fab_add)
        val card = findViewById<MaterialCardView>(R.id.card_center)
        val tvBrand = findViewById<TextView>(R.id.tv_brand)

        btnTheme.setOnClickListener { v ->
            // short vibration
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
            try {
                vibrator?.let {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        it.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
                    } else {
                        @Suppress("DEPRECATION")
                        it.vibrate(20)
                    }
                }
            } catch (ignored: Exception) {
            }

            // toggle night mode
            val current = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            if (current == Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            // click animation
            v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(80).setInterpolator(AccelerateDecelerateInterpolator())
                .withEndAction { v.animate().scaleX(1f).scaleY(1f).duration = 100 }.start()
        }

        fab.setOnClickListener {
            // placeholder: open add-server bottom sheet (not implemented yet)
            Toast.makeText(this, "Add server (placeholder)", Toast.LENGTH_SHORT).show()
            // small pulse animation on card
            card.animate().scaleX(0.98f).scaleY(0.98f).setDuration(120).withEndAction { card.animate().scaleX(1f).scaleY(1f).duration = 180 }.start()
        }

        // small intro animation for brand
        tvBrand.alpha = 0f
        tvBrand.translationY = 10f
        tvBrand.animate().alpha(1f).translationY(0f).setDuration(450).setInterpolator(AccelerateDecelerateInterpolator()).start()
    }
}
