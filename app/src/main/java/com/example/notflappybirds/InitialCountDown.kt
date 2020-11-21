package com.example.notflappybirds

import android.view.View
import android.widget.TextView
import kotlin.properties.Delegates

class InitialCountDown(val lengthOfInitialCountDown: Int, val initialCountDownView: TextView, val gameActivity: GameActivity){
    val endOfInitialCountDownListeners = mutableListOf<(Int) -> Unit>()

    var initialCountDownEnded: Int by Delegates.observable(0) { _, _, newValue ->
        endOfInitialCountDownListeners.forEach{it(newValue)}
    }

    fun updateInitialCountDown(tick: Int): Unit {
        val countDown = lengthOfInitialCountDown - tick

        if(countDown < 0){
            initialCountDownEnded = lengthOfInitialCountDown
            gameActivity.runOnUiThread {
                initialCountDownView.visibility = View.GONE
            }
        }

        gameActivity.runOnUiThread {
            if (countDown > 0){
                initialCountDownView.text = countDown.toString()
            } else {
                initialCountDownView.text = "GO!"
            }

        }
    }
}