package com.example.notflappybirds

import android.view.View
import android.widget.TextView

class InitialCountDown(val initialCountDownView: TextView, val gameActivity: GameActivity){
    val lengthOfCountDown: Int = 4

    fun updateInitialCountDown(tick: Int): Unit {
        val countDown = lengthOfCountDown - tick

        if(countDown < 0){
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