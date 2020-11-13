package com.example.notflappybirds

import kotlin.math.round
import kotlin.properties.Delegates

class GameTime(){
    val gameEnded = mutableListOf<() -> Unit>()

    var stopTimer: Int by Delegates.observable(0) { _, _, _ ->
        gameEnded.forEach{it()}
    }

    val timeListeners = mutableListOf<(Int) -> Unit>()

    var secondsPassed: Int by Delegates.observable(0) { _, _, newValue ->
        timeListeners.forEach{it(newValue)}
    }

    var timeStarted: Long = 0

    private val timeThread: Thread = object : Thread() {
        override fun run() {
            timeStarted = System.nanoTime()
            while(stopTimer == 0){
                // var minutes = round(((System.nanoTime() - mSurvivedTime) / 1000000000 / 60).toDouble()).toInt()
                var tick = round(((System.nanoTime() - timeStarted) / 1000000000 % 60).toDouble()).toInt()

                if(secondsPassed != tick){
                    secondsPassed = tick
                }

            }
        }
    }

    fun startGameTime (): Unit {
        timeThread.start()
    }
}