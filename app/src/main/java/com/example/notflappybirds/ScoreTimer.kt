package com.example.notflappybirds

import android.widget.TextView

class ScoreTimer(private val startDelay: Int, private val scoreTextBox: TextView, private val gameActivity: GameActivity){
    fun updateScore(tick: Int): Unit {
        val correctedTick = tick - startDelay
        if(correctedTick > 0){
            gameActivity.runOnUiThread{
                scoreTextBox.text = correctedTick.toString()
            }
        }
    }
}