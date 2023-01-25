package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var zero: Button
    private lateinit var backspace: Button

    private lateinit var point: Button
    private lateinit var clear: Button
    private lateinit var equal: Button
    private lateinit var div: Button
    private lateinit var multiply: Button
    private lateinit var plus: Button
    private lateinit var minus: Button


    private lateinit var operand: TextView
    private lateinit var result: TextView


    private var isPoint = true
    private var isSimvol = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        zero.setOnClickListener(this)
        clear.setOnClickListener {
            operand.text = "0"
            result.text = "0"
            isPoint = true
            isSimvol = true
        }

        point.setOnClickListener {
            if (isPoint) {
                operand.text = operand.text.toString() + "."
                isPoint = false
            }
        }
        equal.setOnClickListener {
            operand.text=""
        }
        div.setOnClickListener {
            addSimvol("/")
        }
        multiply.setOnClickListener {
            addSimvol("x")
        }
        plus.setOnClickListener {
            addSimvol("+")
        }
        minus.setOnClickListener {
            addSimvol("-")
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(p0: View?) {

        var btn = findViewById<Button>(p0!!.id)
        if (operand.text == "0") {
            operand.text = ""
        }
        operand.text = operand.text.toString() + btn.text
        isSimvol = true
        result.text = caculate()
    }


    private fun caculate(): String {
        var list = createArray(operand.text.toString())
        list = hisobla1(list)
        hisobla2(list)
        return list[0].toString()
    }

    private fun createArray(s: String): MutableList<Any> {
        var list = mutableListOf<Any>()
        var temp = ""
        for (i in s) {
            if (i.isDigit() || i == '.') {
                temp += i
            } else {
                list.add(temp.toFloat())
                temp = ""
                list.add(i)
            }
        }
        if (temp.isNotEmpty()) {
            list.add(temp.toFloat())
        }
        return list
    }

    fun hisobla1(l: MutableList<Any>): MutableList<Any> {
        var list = l
        var i = 0
        while (list.contains('/') || list.contains('x')) {

            if (list[i] == 'x' || list[i] == '/') {
                var old = list[i - 1] as Float
                var next = list[i + 1] as Float
                var amal = list[i]
                var res = 0f
                when (amal) {
                    '/' -> {
                        res = old / next
                    }
                    'x' -> {
                        res = old * next
                    }
                }
                list[i - 1] = res
                list.removeAt(i)
                list.removeAt(i)
                i -= 2
            }
            i++
        }
        Log.d("AAA", list.toString())

        return l
    }

    fun hisobla2(l: MutableList<Any>): MutableList<Any> {
        var list = l
        var i = 0
        while (list.contains('+') || list.contains('-')) {

            if (list[i] == '-' || list[i] == '+') {
                var old = list[i - 1] as Float
                var next = list[i + 1] as Float
                var amal = list[i]
                var res = 0f
                when (amal) {
                    '+' -> {
                        res = old + next
                    }
                    '-' -> {
                        res = old - next
                    }
                }
                list[i - 1] = res
                list.removeAt(i)
                list.removeAt(i)
                i -= 2
            }
            i++
        }
        Log.d("TAG", list.toString())

        return l
    }

    @SuppressLint("SetTextI18n")
    private fun addSimvol(simvol: String) {
        if (isSimvol) {
            operand.text = operand.text.toString() + simvol
            isSimvol = false
        } else {
            operand.text = operand.text.dropLast(1).toString() + simvol
        }

    }

    fun initUI() {
        one = findViewById(R.id.bir)
        two = findViewById(R.id.ikki)
        three = findViewById(R.id.uch)
        four = findViewById(R.id.tort)
        five = findViewById(R.id.besh)
        six = findViewById(R.id.olti)
        seven = findViewById(R.id.yetti)
        eight = findViewById(R.id.sakkiz)
        nine = findViewById(R.id.toqqiz)
        zero = findViewById(R.id.nol)
        point = findViewById(R.id.point)
        clear = findViewById(R.id.clear)
        div = findViewById(R.id.div)
        multiply = findViewById(R.id.multiply)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        operand = findViewById(R.id.operand)
        result = findViewById(R.id.result)
        equal = findViewById(R.id.equal)
    }
}
