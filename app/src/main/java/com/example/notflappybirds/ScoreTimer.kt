package com.example.notflappybirds

import android.util.Log
import android.widget.TextView

class ScoreTimer(private val scoreTextBox: TextView, private val gameActivity: GameActivity){
    private var releaseScoreTimer = false
    private var lengthOfInitialCountDown: Int? = null

    fun updateScore(tick: Int): Unit {
        if(releaseScoreTimer){
            gameActivity.runOnUiThread{
                if(lengthOfInitialCountDown != null){
                    val correctedTick = tick - lengthOfInitialCountDown!!

                    scoreTextBox.text = correctedTick.toString()
                }

            }
        }
    }

    fun initialCountDownEnded(lengthOfInitialCountDown: Int){
        releaseScoreTimer = true
        this.lengthOfInitialCountDown = lengthOfInitialCountDown
    }
}