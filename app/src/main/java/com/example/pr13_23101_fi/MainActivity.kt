package com.example.pr13_23101_fi

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isStarted = false
    private var isFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
    }

    fun Start(view: View?) {
        val button = findViewById<Button>(R.id.btnStart)
        if (!isFinished) {
            if (!isStarted) {
                button.setBackgroundColor(Color.RED)
                button.text = "Пауза"
                isStarted = true
            } else {
                button.setBackgroundColor(Color.GREEN)
                button.text = "Старт"
                isStarted = false
            }
        } else {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

    fun Drive1(view: View?) = moveCar(findViewById(R.id.Car1), 1)
    fun Drive2(view: View?) = moveCar(findViewById(R.id.Car2), 2)

    private fun moveCar(car: View, playerNum: Int) {
        if (!isStarted || isFinished) return

        val params = car.layoutParams as MarginLayoutParams
        params.leftMargin += 70
        params.rightMargin -= 70
        car.requestLayout()

        if (params.rightMargin <= -2150) {
            isFinished = true
            val tvResult = findViewById<TextView>(R.id.tvResult)
            val btnStart = findViewById<Button>(R.id.btnStart)

            tvResult.text = "ПОБЕДА $playerNum ИГРОКА!"
            tvResult.setTextColor(if (playerNum == 1) Color.MAGENTA else Color.RED)

            btnStart.text = "Заново"
            btnStart.setBackgroundColor(Color.BLUE)
        }
    }
}