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
    private val inputLinesSplit by lazy {
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


    //we are gonna act like this is my first try on part 2
    fun part2(): Any {
        val stringDigitMap = mapOf(
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
        return inputLines.map{

            val digits = it.mapIndexed { index, c -> Pair(index, c) }.filter { it.second.isDigit() }.map { Pair(it.first, it.second.toString().toInt()) }
            val lastStringDigit = it.findLastAnyOf(stringDigitMap.keys)
            val firstStringDigit = it.findAnyOf(stringDigitMap.keys)
            val allDigits = mutableListOf<Pair<Int, Int>>()
            allDigits.addAll(digits)
            if (firstStringDigit!=null){
                allDigits.add(Pair(firstStringDigit.first, stringDigitMap.get(firstStringDigit.second)!!))
            }
            if (lastStringDigit!=null){
                allDigits.add(Pair(lastStringDigit.first, stringDigitMap.get(lastStringDigit.second)!!))
            }
            val sorted = allDigits.sortedBy { it.first }.map { it.second }
            "${sorted.first()}${sorted.last()}".toInt()
        }.sum()
    }
}