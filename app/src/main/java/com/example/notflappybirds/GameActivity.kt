package com.example.notflappybirds

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class GameActivity : AppCompatActivity() {
    private lateinit var mInitialCountDown: TextView
    private lateinit var mTimer: TextView
    private lateinit var mCharacterImg: ImageView
    private lateinit var mConstraintLayout: ConstraintLayout
    lateinit var mDrawView: DrawBars
    private val bars = Bars()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        mInitialCountDown = findViewById(R.id.initalCountDown)
        mTimer = findViewById(R.id.playTime)
        mConstraintLayout = findViewById(R.id.gameConstraintLayout)
        mCharacterImg = findViewById(R.id.birdImage)
        mDrawView = DrawBars(this, bars.toDraw)

        val gameTime = GameTime()
        val countDown = InitialCountDown(mInitialCountDown,this)
        val character = Character(countDown.lengthOfCountDown, mCharacterImg, this)
        val scoreTimer = ScoreTimer(countDown.lengthOfCountDown, mTimer, this)

        mConstraintLayout.setOnClickListener {
            character.jumpUp()
        }

        gameTime.timeListeners.add{secondsPassed ->
            character.driftDown(secondsPassed)
            scoreTimer.updateScore(secondsPassed)
            countDown.updateInitialCountDown(secondsPassed)
            bars.updateBars(this, mDrawView, mConstraintLayout)
        }


        mDrawView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        mConstraintLayout.addView(mDrawView)

        gameTime.startGameTime()
    }


}
