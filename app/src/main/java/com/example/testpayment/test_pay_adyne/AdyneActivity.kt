package com.example.testpayment.test_pay_adyne

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.example.testpayment.R

class AdyneActivity : AppCompatActivity() {
    private var boolean: Boolean = true
    private lateinit var lotti: LottieAnimationView
    private lateinit var car: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adyne)
        lotti = findViewById(R.id.animeeeeeeeee);
        lotti.setOnClickListener({
            if (boolean) {
                lotti.speed = 5f
                lotti.playAnimation()
                boolean = false

            } else {
                lotti.speed = -5f
                lotti.playAnimation()
                boolean = true
            }
        })

        car = findViewById(R.id.car);
        val imageView: ImageView = findViewById(R.id.imageView);
        imageView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val parms: ConstraintLayout.LayoutParams = imageView.layoutParams as ConstraintLayout.LayoutParams
                when (event!!.action) {
                    MotionEvent.ACTION_MOVE -> {
                        var x: Int = event.rawX.toInt()
                        var y = event.rawY.toInt()
                        if (x > windowManager.defaultDisplay.width)
                            x = windowManager.defaultDisplay.width
                        if (y > windowManager.defaultDisplay.height)
                            y = windowManager.defaultDisplay.height
                        parms.leftMargin = x
                        parms.rightMargin = y
                        imageView.layoutParams = parms

                    }

                }



                return true
            }

        })

        car.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val parms: ConstraintLayout.LayoutParams = car.layoutParams as ConstraintLayout.LayoutParams
                when (event!!.action) {
                    MotionEvent.ACTION_MOVE -> {
                        val x = event.rawX
                        val y = event.rawY
                        parms.leftMargin = x.toInt()
                        parms.rightMargin = y.toInt()
                        car.layoutParams = parms

                    }

                }



                return true
            }

        })


    }
}