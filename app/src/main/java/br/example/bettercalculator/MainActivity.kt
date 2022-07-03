package br.example.bettercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var tvResult: TextView? = null
    private var tvClear: TextView? = null

    private var originalNumber: String = "0"
    private var resultNumber: Double? = 0.0

    private var newOperation: Boolean = true
    private var dot: Int = 1
    private var minus: Boolean = false
    private var finalNumber: Any = 0.0
    private var op: String = "none"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        tvClear = findViewById(R.id.btnClear)
    }

    fun onNumberEvent(view: View) {
        if(newOperation) tvResult?.text = ""

        newOperation = false
        val selectedButton = view as Button
        var clickedButtonValue = tvResult?.text.toString()

        when(selectedButton.id) {
            R.id.btnZero    -> clickedButtonValue += "0"
            R.id.btnOne     -> clickedButtonValue += "1"
            R.id.btnTwo     -> clickedButtonValue += "2"
            R.id.btnThree   -> clickedButtonValue += "3"
            R.id.btnFour    -> clickedButtonValue += "4"
            R.id.btnFive    -> clickedButtonValue += "5"
            R.id.btnSix     -> clickedButtonValue += "6"
            R.id.btnSeven   -> clickedButtonValue += "7"
            R.id.btnEight   -> clickedButtonValue += "8"
            R.id.btnNine    -> clickedButtonValue += "9"
            R.id.btnComa    -> {
                if (dot == 1) {
                    if (clickedButtonValue == "") clickedButtonValue = "0."
                    else clickedButtonValue += "."

                    dot = 0
                }
            }
        }

        if (clickedButtonValue != "") tvClear?.text = "C"

        tvResult?.text = clickedButtonValue
        finalNumber = clickedButtonValue.toDouble()
    }

    fun onSignChange(view: View) {
        val numSign: Any
        numSign = if (dot == 0) tvResult?.text.toString().toDouble() * -1 else tvResult?.text.toString().toInt() * -1

        tvResult?.text = numSign.toString()
        finalNumber = numSign.toDouble()
    }

    fun onOpEvent(view: View) {
        val selectedButton = view as Button
        when(selectedButton.id) {
            R.id.btnMultiply    -> op = "*"
            R.id.btnDivide      -> op = "/"
            R.id.btnMinus       -> op = "-"
            R.id.btnPlus        -> op = "+"
        }

        originalNumber = tvResult?.text.toString()
        newOperation = true
        dot = 1
    }

    fun onEqual(view: View) {
        if (tvResult?.text.toString() == ".") tvResult?.text = "0.0"
        val equalNumber = tvResult?.text.toString()

        when(op) {
            "*"     -> resultNumber = originalNumber.toDouble() * equalNumber.toDouble()
            "/"     -> resultNumber = originalNumber.toDouble() / equalNumber.toDouble()
            "+"     -> resultNumber = originalNumber.toDouble() + equalNumber.toDouble()
            "-"     -> resultNumber = originalNumber.toDouble() - equalNumber.toDouble()
        }

        op = "none"
        tvResult?.text = resultNumber.toString()
        dot = 1
        newOperation = true
    }

    fun onPercent(view: View) {
        val percentNumber: Double = tvResult?.text.toString().toDouble() / 100
        resultNumber = percentNumber
        tvResult?.text = percentNumber.toString()
        dot = 1
        newOperation = true
    }

    fun onClear(view: View) {
        newOperation = true
        dot = 1
        tvResult?.text = "0"
        tvClear?.text = "AC"
    }
}