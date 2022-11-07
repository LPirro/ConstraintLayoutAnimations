package com.lpirro.constraintlayoutanimations

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {

    private var show = false
    lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.circuit)

        constraintLayout = findViewById(R.id.constraint)

        val backgroundImage = findViewById<View>(R.id.backgroundImage)
        backgroundImage.setOnClickListener {
            show = !show
            if (show) changeConstraints(R.layout.circuit_detail) // if the animation is NOT shown, we animate the views
            else changeConstraints(R.layout.circuit) // if the animation is shown, we hide back the views
        }
    }

    private fun changeConstraints(@LayoutRes newConstraintLayout: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, newConstraintLayout)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraintLayout, transition)
        constraintSet.applyTo(constraintLayout)  //here constraint is the name of view to which we are applying the constraintSet
    }
}
