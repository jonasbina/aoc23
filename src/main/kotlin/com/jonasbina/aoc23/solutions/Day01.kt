package com.jonasbina.aoc23.solutions

import com.janbina.aoc20.utils.Input
import kotlin.random.Random

fun main() {
    val input = Input.getDayInputLines(1)
    Day01(input).also {
//        println(it.part1())
        println(it.part22())
    }
}

class Day01(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplitted by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
        //Not as clean as I would like it to be but whatever
        return inputLines.map{
            val x = it.filter { it.isDigit() }; (x[0]+(x[x.lastIndex]).toString()).toInt()
        }.sum()
    }

    fun part2(): Any {
        val startTime = System.currentTimeMillis()
        val stringDigits = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )
        var sum = 0
        inputLines.forEach { line ->
            var firstInt:Int? = null
            var lastInt:Int? = null
            var currentStringInt = ""
            line.forEach {
                if (it.isDigit()){
                    currentStringInt = ""
                    if(firstInt==null){
                        firstInt = it.toString().toInt()
                    }
                    lastInt = it.toString().toInt()
                }else{
                    currentStringInt+=it
                    for (i in 0.. currentStringInt.lastIndex){
                        val partOfCurrentStringInt = currentStringInt.drop(i)
                        if (stringDigits.containsKey(partOfCurrentStringInt)){
                            val digit = stringDigits.get(partOfCurrentStringInt)
                            if (firstInt==null){
                                firstInt = digit
                            }
                            lastInt = digit
                            currentStringInt = ""
                            break
                        }
                    }
                }
            }
            val x = "$firstInt$lastInt".toInt()

//            println("Line: $it \n $firstInt, $lastInt")
//            println(x)
            sum+=x
        }
        println("Took ${System.currentTimeMillis()-startTime} millis!")
        return sum
    }
    fun part22():Any{
        var sum = 0

        inputLines.forEach {
            val digits = mutableListOf<Int>()
            var currentString = ""
            val alreadyFoundStringInt = mutableListOf<Int>()
            it.forEach { c->
                if (c.isDigit()){
                    digits.add(c.toString().toInt())
                    currentString = ""
                }else{
                    currentString+=c
                    val fromString = getDigitsFromString(currentString)
                    fromString.forEach { digit->


                        if (!alreadyFoundStringInt.contains(digit)){
                            digits.add(digit)
                            alreadyFoundStringInt.add(digit)
                        }
                    }
                }

            }
            println(it)
            println(digits.joinToString(","))
            val firstLast = "${digits.first()}${digits.last()}".toInt()
            println(firstLast)
            sum+=firstLast
        }
        return sum
    }
    fun getDigitsFromString(currentString: String):List<Int>{
        val stringDigits = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        ).entries
        var i = 0
        val res = mutableListOf<Int>()
        while (i<stringDigits.size){
            val entry = stringDigits.toList()[i]
            if (currentString.contains(entry.key)){
                res.add(entry.value)
            }

            i++
        }
        return res
    }
}