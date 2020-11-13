package com.example.notflappybirds

import android.widget.ImageView

class Character(private val startDelay: Int, val characterImageView: ImageView, val gameActivity: GameActivity){
    private var gravity: Int = 15

    fun jumpUp() {
        gameActivity.runOnUiThread {
            characterImageView.y = characterImageView.y - 25
        }
    }

    fun driftDown(tick: Int) {
        if(tick > startDelay){
            gameActivity.runOnUiThread {
                characterImageView.y = characterImageView.y + gravity
            }
        }
    }
}