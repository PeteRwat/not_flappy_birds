package com.example.notflappybirds

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class GameActivity : AppCompatActivity() {
    private lateinit var mInitialCountDown: TextView
    private lateinit var mTimer: TextView
    private lateinit var mCharacterImg: ImageView
    private lateinit var mConstraintLayout: ConstraintLayout
    private lateinit var mCollisionDetection: CollisionDetection
    lateinit var mDrawView: DrawBars
    private val bars = Bars()
    private val lengthOfInitialCountDown = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        mInitialCountDown = findViewById(R.id.initalCountDown)
        mTimer = findViewById(R.id.playTime)
        mConstraintLayout = findViewById(R.id.gameConstraintLayout)
        mCharacterImg = findViewById(R.id.birdImage)
        mDrawView = DrawBars(this, bars.toDraw)

        mInitialCountDown.text = lengthOfInitialCountDown.toString()

        val gameTime = GameTime()
        val countDown = InitialCountDown(lengthOfInitialCountDown, mInitialCountDown,this)
        val character = Character(mCharacterImg, this)

        val scoreTimer = ScoreTimer(mTimer, this)
        mCollisionDetection = CollisionDetection(character, bars.toDraw)

        mConstraintLayout.setOnClickListener {
            character.jumpUp()
        }

        mCollisionDetection.collisionListeners.add{
            gameTime.stopGameTimer()
        }

        gameTime.timeListenersSeconds.add{secondsPassed ->
            scoreTimer.updateScore(secondsPassed)
            countDown.updateInitialCountDown(secondsPassed)
        }

        gameTime.timeListenersSixtiethSecond.add{
            character.driftDown()
            bars.updateBars(this, mDrawView, mConstraintLayout)
            mCollisionDetection.checkBars()
        }

        countDown.endOfInitialCountDownListeners.add{lengthOfCountDown ->
            character.initialCountDownEned()
            scoreTimer.initialCountDownEnded(lengthOfCountDown)
            bars.initialCountDownEned()
        }


        mDrawView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        mConstraintLayout.addView(mDrawView)

        gameTime.startGameTime()
    }


}
