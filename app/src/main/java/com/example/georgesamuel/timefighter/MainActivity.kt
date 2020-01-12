package com.example.georgesamuel.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var btnTapMe : Button
    internal lateinit var tvGameScore : TextView
    internal lateinit var tvTimeLeft : TextView
    internal var score = 0
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown : Long = 5000
    internal val countDownInterval : Long = 1000
    internal var gameStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       init()
    }

    private fun init(){
        btnTapMe = findViewById(R.id.btn_tab_me)
        tvGameScore = findViewById(R.id.tv_game_score)
        tvTimeLeft = findViewById(R.id.tv_time_left)
        resetGame()
        btnTapMe.setOnClickListener {
            incrementScore()
        }
    }

    private fun resetGame() {
        score = 0
        tvGameScore.text = getString(R.string.your_score, score.toString())
        val initialTimeLeft = initialCountDown / 1000
        tvTimeLeft.text = getString(R.string.time_left, initialTimeLeft.toString())
        countDownTimer = object: CountDownTimer(initialCountDown, countDownInterval) {
            override fun onFinish() {
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000;
                tvTimeLeft.text = getString(R.string.time_left, timeLeft.toString())

            }
        }
        gameStarted = false
    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.game_over_message, score.toString()), Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun incrementScore() {
        if(!gameStarted){
            startGame()
        }
        score++
        val newScore = getString(R.string.your_score, score.toString())
        tvGameScore.text = newScore
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }
}
