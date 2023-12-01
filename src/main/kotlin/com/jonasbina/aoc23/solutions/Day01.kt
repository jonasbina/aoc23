package com.jonasbina.aoc23.solutions

import com.janbina.aoc20.utils.Input
import kotlin.random.Random

fun main() {
    val input = Input.getDayInputLines(1)
    Day01(input).also {
        println(it.part1())
        println(it.part2())
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
        inputLines.forEach {
            var firstInt:Int? = null
            var lastInt:Int? = null
            var currentStringInt = ""
            it.forEach {
                if (it.isDigit()){
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
}