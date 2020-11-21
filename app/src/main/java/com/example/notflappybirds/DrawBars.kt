package com.example.notflappybirds

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View

class DrawBars(context: Context, barsToDraw: MutableList<BarCoord>) : View(context) {
    private var paint: Paint = Paint()
    var barsToDraw = barsToDraw

    override fun onDraw(canvas: Canvas) {
        barsToDraw.forEach {
            paint.color = Color.BLACK
            canvas.drawRect(it.topBarLeft, it.topBarTop, it.topBarRight , it.topBarBottom, paint)
            canvas.drawRect(it.bottomBarLeft, it.bottomBarTop, it.bottomBarRight, it.bottomBarBottom, paint)
        }
    }
}