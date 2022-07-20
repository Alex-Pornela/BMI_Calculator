package com.activity.bmicalculator

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.math.pow
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private var userWeight: Double = 0.0
    private var userHeight: Double = 0.0
    private var bmi : Double = 0.0
    private lateinit var bmiResult : TextView
    private lateinit var bmiCategories: TextView
    private lateinit var bmiTips: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight = findViewById<EditText>(R.id.etWeight)
        val height = findViewById<EditText>(R.id.etHeight)
        bmiCategories = findViewById(R.id.resultStat)
        bmiTips = findViewById(R.id.resultInfo)
        bmiResult = findViewById(R.id.index)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            when {
                weight.text.isEmpty() && height.text.isEmpty() -> {
                    Toast.makeText(this@MainActivity,"Please enter your weight and height",Toast.LENGTH_LONG).show()
                }
                height.text.isEmpty() -> {
                    Toast.makeText(this@MainActivity,"Please enter your height",Toast.LENGTH_LONG).show()
                }
                weight.text.isEmpty() -> {
                    Toast.makeText(this@MainActivity,"Please enter your weight",Toast.LENGTH_LONG).show()
                }
                else -> {
                    userWeight = weight.text.toString().toDouble()
                    userHeight = height.text.toString().toDouble()
                    computeBMI(userWeight,userHeight)
                    bmiInfo(bmi)
                }
            }

        }

    }

    @SuppressLint("SetTextI18n")
    private fun bmiInfo(bmi: Double) {
        var color = 0
        val bmiIndex  = String.format("%.2f",bmi)
        bmiResult.text = bmiIndex

        when{
            bmi < 18.5 -> {
                bmiCategories.text = "Underweight"
                color = R.color.underweight
                bmiTips.text = "Choose nutrient-rich foods."
            }
            bmi in 18.5..24.9 -> {
                bmiCategories.text = "Normal"
                color = R.color.normal
                bmiTips.text = "You are Healthy"
            }
            bmi in 25.0..29.9 -> {
                bmiCategories.text = "Overweight"
                color = R.color.overweight
                bmiTips.text = "You need to diet"
            }
            bmi > 30.0 -> {
                bmiCategories.text = "Obese"
                color = R.color.obese
                bmiTips.text = "You need to exercise regularly"
            }
        }

        bmiCategories.setTextColor(ContextCompat.getColor(this,color))

    }

    private fun computeBMI(weight: Double, height: Double): Double {
        val heightInMeter = height / 100
        bmi = weight / (heightInMeter.pow(2.0))
        return bmi
    }
}