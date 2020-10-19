package com.example.notflappybirds

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.round
import kotlin.properties.Delegates

class InitialCountDown(val initialCountDownView: TextView, val gameActivity: GameActivity){
    val lengthOfCountDown: Int = 4

    fun updateInitialCountDown(tick: Int): Unit {
        val countDown = lengthOfCountDown - tick

        if(countDown < 0){
            gameActivity.runOnUiThread {
                initialCountDownView.visibility = View.GONE
            }
        }

        gameActivity.runOnUiThread {
            if (countDown > 0){
                initialCountDownView.text = countDown.toString()
            } else {
                initialCountDownView.text = "GO!"
            }

        }
    }
}

class gameTime(gameActivity: GameActivity){
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

class Character(val startDelay: Int, val characterImageView: ImageView, val gameActivity: GameActivity){

    fun driftDown(tick: Int): Unit {
        if(tick > startDelay){
            gameActivity.runOnUiThread {
                characterImageView.y = characterImageView.y + 10
            }
        }
    }
}

class ScoreTimer(val startDelay: Int, val scoreTextBox: TextView, val gameActivity: GameActivity){
    fun updateScore(tick: Int): Unit {
        val correctedTick = tick - startDelay
        if(correctedTick > 0){
            gameActivity.runOnUiThread{
                scoreTextBox.text = correctedTick.toString()
            }
        }
    }
}

class GameActivity : AppCompatActivity() {
    lateinit var mInitialCountDown: TextView
    lateinit var mTimer: TextView
    lateinit var mCharacterImg: ImageView
    lateinit var mConstraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        mInitialCountDown = findViewById(R.id.initalCountDown)
        mTimer = findViewById(R.id.playTime)
        mConstraintLayout = findViewById(R.id.gameConstraintLayout)
        mCharacterImg = findViewById(R.id.birdImage)

        mConstraintLayout.setOnClickListener {
            // Log.i("PR", "tapped")
        }

        val gameTime = gameTime(this)
        val countDown = InitialCountDown(mInitialCountDown,this)
        val character = Character(countDown.lengthOfCountDown, mCharacterImg, this)
        val scoreTimer = ScoreTimer(countDown.lengthOfCountDown, mTimer, this)

        gameTime.timeListeners.add{secondsPassed ->
            character.driftDown(secondsPassed)
            scoreTimer.updateScore(secondsPassed)
            countDown.updateInitialCountDown(secondsPassed)
        }

        gameTime.startGameTime()
    }


}