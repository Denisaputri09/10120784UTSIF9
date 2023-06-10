package com.example.a10120784utsif9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.a10120784utsif9.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){

    private lateinit var buttonNext: Button

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Myself Apps",
                "Halo, Selamat datang di Myself Apps!",
                R.drawable.ic_home
            ),
            IntroSlide(
                "Daily Activity & Gallery",
                "Didalam myself apps terdapat daily activity dan juga gallery",
                R.drawable.ic_kamera
            ),
            IntroSlide(
                "Music Favorite & Profile",
                "Didalam myself apps juga terdapat music favorite dan profileku",
                R.drawable.ic_music
            )
        )
    )

    private lateinit var introSliderAdapterPager: ViewPager2
    private lateinit var binding: ActivityMainBinding
    private lateinit var indicatorsContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        introSliderAdapterPager = binding.introSliderViewPager
        introSliderAdapterPager.adapter = introSliderAdapter

        indicatorsContainer = binding.indicatorsContainer
        setupIndicators()
        setCurrentIndicator(0)

        var buttonNext = findViewById<Button>(R.id.buttonNext)
        buttonNext.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}