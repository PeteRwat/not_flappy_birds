package com.example.notflappybirds

import android.widget.ImageView

class Character(private val characterImageView: ImageView, val gameActivity: GameActivity){
    private var gravity: Int = 2
    private var releaseCharacter = false
    val characterImgBox = characterImageView

    fun jumpUp() {
        gameActivity.runOnUiThread {
            characterImgBox.y = characterImgBox.y - 60
        }
    }

    fun driftDown() {
        if(releaseCharacter){
            gameActivity.runOnUiThread {
                characterImgBox.y = characterImgBox.y + gravity
            }
        }
    }



    fun initialCountDownEned(){
        releaseCharacter = true
    }
}