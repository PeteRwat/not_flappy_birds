package com.example.notflappybirds

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.round
import kotlin.properties.Delegates

class InitialCountDown(gameActivity: GameActivity){
    val countDownEnded = mutableListOf<() -> Unit>()

    var startTiming: Int by Delegates.observable(0) { _, _, _ ->
        countDownEnded.forEach{it()}
    }

    val countDownThread: Thread = object : Thread() {
        var x = 3
        override fun run() {
            try {
                while (!this.isInterrupted && x >= 0) {
                    gameActivity.runOnUiThread {
                        if(x == 0){
                            gameActivity.mInitalCountDown.text = "GO!"
                        }else{
                            gameActivity.mInitalCountDown.text = x.toString()
                        }
                    }
                    sleep(1000)
                    x -= 1
                }
            } catch (e: InterruptedException) {
            }

            gameActivity.runOnUiThread {
                gameActivity.mInitalCountDown.visibility = View.GONE
            }
            startTiming = 1
        }
    }
}

class Timer(gameActivity: GameActivity){
    var mSurvivedTime: Long = 0

    private val timerThread: Thread = object : Thread() {
        override fun run() {
            mSurvivedTime = System.nanoTime()
            while(true){
                var minutes = round(((System.nanoTime() - mSurvivedTime) / 1000000000 / 60).toDouble()).toInt()
                var seconds = round(((System.nanoTime() - mSurvivedTime) / 1000000000 % 60).toDouble()).toInt()

                gameActivity.runOnUiThread {
                    gameActivity.mTimer.text = "${minutes.toString()} : ${seconds.toString()}"
                }
            }
        }
    }

    fun startTimer (): Unit {
        timerThread.start()
    }
}

class GameActivity : AppCompatActivity() {
    lateinit var mInitalCountDown: TextView
    lateinit var mTimer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        mInitalCountDown = findViewById(R.id.initalCountDown)
        mTimer = findViewById(R.id.playTime)

        val scoreTimer = Timer(this)
        val countDown = InitialCountDown(this)

        countDown.countDownEnded.add {
            scoreTimer.startTimer()
        }

        countDown.countDownThread.start()

    }


}