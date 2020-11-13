package com.example.notflappybirds

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class BarCoord(var horizontalPosition: Float, gapSize: Float, gapHeight: Float){
    val barWidth = 60F

    var topBarLeft = horizontalPosition
    var topBarRight = horizontalPosition + barWidth
    var topBarTop = 0F
    var topBarBottom = gapHeight

    var bottomBarLeft = horizontalPosition
    var bottomBarRight = horizontalPosition + barWidth
    var bottomBarTop = gapHeight + gapSize
    var bottomBarBottom = 4000F
}

class Bars(){
    val toDraw = initiliseToDraw()

    fun initiliseToDraw(): MutableList<BarCoord>{
        val bar1 = BarCoord(900F, 350F,1000F)
        val bar2 = BarCoord(1300F, 350F,1000F)
        val bar3 = BarCoord(1700F, 350F,1000F)

        return  mutableListOf<BarCoord>(bar1, bar2, bar3)
    }

    fun updateBars(gameActivity : AppCompatActivity, drawView: DrawBars, constraintLayout: ConstraintLayout){
        toDraw.forEach{
            it.horizontalPosition -= 100F
//            Log.i("new H pos", it.horizontalPosition.toString())
        }

        gameActivity.runOnUiThread{
            constraintLayout.removeView(drawView)
            constraintLayout.addView(drawView)
        }
    }
}