package com.example.notflappybirds

import kotlin.math.round
import kotlin.properties.Delegates

class GameTime(){
    var stopTimer = 0

    val timeListenersSeconds = mutableListOf<(Int) -> Unit>()

    var secondsPassed: Int by Delegates.observable(0) { _, _, newValue ->
        timeListenersSeconds.forEach{it(newValue)}
    }

    val timeListenersSixtiethSecond = mutableListOf<() -> Unit>()

    var sixtiethPassed: Int by Delegates.observable(0) { _, _, _ ->
        timeListenersSixtiethSecond.forEach{it()}
    }

    var timeStarted: Long = 0

    private val timeThread: Thread = object : Thread() {
        override fun run() {
            timeStarted = System.nanoTime()
            while(stopTimer == 0){
                // var minutes = round(((System.nanoTime() - mSurvivedTime) / 1000000000 / 60).toDouble()).toInt()
                var secondsTick = round(((System.nanoTime() - timeStarted) / 1000000000 % 60).toDouble()).toInt()
                var sixtiethTick = round(((System.nanoTime() - timeStarted) / (1000000000 / 60) % 60).toDouble()).toInt()

                if(secondsPassed != secondsTick){
                    secondsPassed = secondsTick
                }

                if(sixtiethPassed != sixtiethTick){
                    sixtiethPassed = sixtiethTick
                }

            }
        }
    }

    fun startGameTime (): Unit {
        timeThread.start()
    }

    fun stopGameTimer () {
        stopTimer = 1
    }
}