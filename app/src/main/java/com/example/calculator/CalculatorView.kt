package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.example.calculator.databinding.FragmentCalculatorViewBinding

class CalculatorView : Fragment() {

    lateinit var binding : FragmentCalculatorViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorViewBinding.inflate(inflater, container, false)
        val view = binding.root

        setNumberListener()
        setOperatorListener()

        return view
    }

    val numberButton = arrayListOf<Button>()
    var enterString = ""
    var result = 0.0

    fun setNumberListener(){
        numberButton.add(binding.zero)
        numberButton.add(binding.one)
        numberButton.add(binding.two)
        numberButton.add(binding.three)
        numberButton.add(binding.four)
        numberButton.add(binding.five)
        numberButton.add(binding.six)
        numberButton.add(binding.seven)
        numberButton.add(binding.eight)
        numberButton.add(binding.nine)
        numberButton.add(binding.dot)

        for(i in 0 .. 9){
            numberButton[i].setOnClickListener {
               enterString = enterString.plus(i.toString())
                binding.enterView.text = enterString
            }
        }
        numberButton[10].setOnClickListener {
            enterString = enterString.plus(".")
            binding.enterView.text = enterString
        }
    }

    fun setOperatorListener() {
        binding.add.setOnClickListener {
            if (enterString == "" || enterString.last().toString() == " ") {
                Toast.makeText(context, "Enter Number!", Toast.LENGTH_SHORT).show()
            } else {
                enterString= enterString.plus(" + ")
                binding.enterView.text = enterString
            }
        }
        binding.minus.setOnClickListener {
            if (enterString == "" || enterString.last().toString() == " ") {
                Toast.makeText(context, "Enter Number!", Toast.LENGTH_SHORT).show()
            } else {
                enterString = enterString.plus(" - ")
                binding.enterView.text = enterString
            }
        }
        binding.divide.setOnClickListener {
            if (enterString == "" || enterString.last().toString() == " ") {
                Toast.makeText(context, "Enter Number!", Toast.LENGTH_SHORT).show()
            } else {
                enterString = enterString.plus(" / ")
                binding.enterView.text = enterString
            }
        }
        binding.multiply.setOnClickListener {
            if (enterString == "" || enterString.last().toString() == " ") {
                Toast.makeText(context, "Enter Number!", Toast.LENGTH_SHORT).show()
            } else {
                enterString = enterString.plus(" * ")
                binding.enterView.text = enterString
            }
        }
        binding.equal.setOnClickListener {
            var numberAndOperator = listOf<String>()
            if(enterString.last().toString() == " "){
                enterString = enterString.dropLast(3)
            }
            numberAndOperator = enterString.split(" ")

            result = numberAndOperator[0].toDouble()
            for (i in 1 until numberAndOperator.size) {
                if(numberAndOperator[i] == "+") {
                    result = add(result, numberAndOperator[i + 1].toDouble())
                }
                if(numberAndOperator[i] == "-") {
                    result = minus(result, numberAndOperator[i + 1].toDouble())
                }
                if(numberAndOperator[i] == "*"){
                    result = multyply(result, numberAndOperator[i+1].toDouble())
                }
                if(numberAndOperator[i] == "/"){
                    result = divide(result , numberAndOperator[i+1].toDouble())
                }
            }
            binding.resultView.text = result.toString()
            enterString = result.toString()
        }

        binding.delete.setOnClickListener {
            //result = 0.0
            if(enterString.last().toString() == " "){
                enterString = enterString.dropLast(3)
            }
            else{
                enterString = enterString.dropLast(1)
            }
            binding.enterView.text = enterString
            //binding.resultView.text = result.toString()
        }
    }

    fun  add(a: Double, b : Double) = a + b
    fun  minus(a: Double, b : Double) = a - b
    fun  divide(a: Double, b : Double) : Double{
        if (b == 0.0){
            Toast.makeText(context, "divide on zero!!", Toast.LENGTH_SHORT).show()
        }
        return a / b
    }
    fun  multyply(a: Double, b : Double) = a * b


}
