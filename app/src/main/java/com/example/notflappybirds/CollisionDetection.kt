package com.example.notflappybirds

import android.util.Log
import kotlin.properties.Delegates

class CollisionDetection (character: Character, bars: MutableList<BarCoord>){
    val character = character
    val bars = bars

    val collisionListeners = mutableListOf<() -> Unit>()

    var collision: Int by Delegates.observable(0) { _, _, _ ->
        collisionListeners.forEach{it()}
    }

    fun checkBars(){
        val charaterPos: IntArray = IntArray(size = 2)
        character.characterImgBox.getLocationOnScreen(charaterPos) //<- must be a way to use character from constructor

        val characterTop = charaterPos[1] - character.characterImgBox.height
        val characterBottom = charaterPos[1]
        val characterLeft = charaterPos[0]
        val characterRight = charaterPos[0] + character.characterImgBox.width

       bars.forEach{
            if((characterTop < it.topBarBottom) && (characterRight > it.topBarLeft) && (characterLeft < it.topBarRight)){
                collision = 1
                Log.i("pr - collision", "1b/2b")
//                Log.i("pr - ctop", characterTop.toString())
//                Log.i("pr - top bar bottom", it.topBarBottom.toString())
//                Log.i("pr - cRight", characterRight.toString())
//                Log.i("pr - top bar left", it.topBarLeft.toString())
//                Log.i("pr - cleft", characterLeft.toString())
//                Log.i("pr - top bar Right", it.topBarRight.toString())
            }
           if(characterTop < it.topBarBottom && characterRight > it.topBarRight && characterLeft < it.topBarRight){
               collision = 1
               Log.i("pr - collision", "2d")
               Log.i("pr - ctop", characterTop.toString())
               Log.i("pr - top bar bottom", it.topBarBottom.toString())
               Log.i("pr - cRight", characterRight.toString())
               Log.i("pr - top bar left", it.topBarLeft.toString())
               Log.i("pr - cleft", characterLeft.toString())
               Log.i("pr - top bar Right", it.topBarRight.toString())
           }

           if(characterTop < it.topBarBottom && characterRight < it.topBarRight && characterLeft > it.topBarLeft){
               collision = 1
               Log.i("pr - collision", "2c")
               Log.i("pr - ctop", characterTop.toString())
               Log.i("pr - top bar bottom", it.topBarBottom.toString())
               Log.i("pr - cRight", characterRight.toString())
               Log.i("pr - top bar left", it.topBarLeft.toString())
               Log.i("pr - cleft", characterLeft.toString())
               Log.i("pr - top bar Right", it.topBarRight.toString())
           }
           if(characterBottom < it.bottomBarTop && characterRight < it.bottomBarRight && characterLeft > it.bottomBarLeft){
               collision = 1
               Log.i("pr - collision", "4c")
               Log.i("pr - cBottom", characterBottom.toString())
               Log.i("pr - ctop", characterTop.toString())
               Log.i("pr - top bar bottom", it.topBarBottom.toString())
               Log.i("pr - cRight", characterRight.toString())
               Log.i("pr - top bar left", it.topBarLeft.toString())
               Log.i("pr - cleft", characterLeft.toString())
               Log.i("pr - top bar Right", it.topBarRight.toString())
           }

           if(characterBottom > it.bottomBarTop && characterRight > it.bottomBarLeft && characterLeft < it.topBarRight){
               collision = 1
               Log.i("pr - collision", "4b/5b")
               Log.i("pr - cBottom", characterBottom.toString())
               Log.i("pr - ctop", characterTop.toString())
               Log.i("pr - bar bottom", it.bottomBarTop.toString())
               Log.i("pr - cRight", characterRight.toString())
               Log.i("pr - bar left", it.bottomBarLeft.toString())
               Log.i("pr - cleft", characterLeft.toString())
               Log.i("pr - bar Right", it.topBarRight.toString())
           }
           if(characterBottom < it.bottomBarTop && characterRight > it.bottomBarRight && characterLeft < it.topBarRight){
               collision = 1
               Log.i("pr - collision", "4d")
               Log.i("pr - cBottom", characterBottom.toString())
               Log.i("pr - ctop", characterTop.toString())
               Log.i("pr - bar bottom", it.bottomBarTop.toString())
               Log.i("pr - cRight", characterRight.toString())
               Log.i("pr - bar left", it.bottomBarLeft.toString())
               Log.i("pr - cleft", characterLeft.toString())
               Log.i("pr - bar Right", it.topBarRight.toString())
           }

       }
    }
}