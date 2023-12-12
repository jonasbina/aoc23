package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import com.jonasbina.aoc23.solutions.Day01
import com.jonasbina.aoc23.solutions.Day08

fun main() {
    val input = Input.getDayInputLines(12)
    val testInput = Input.getTestInputLines(12)
    val test = true
    Day12(if (test) testInput else input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day12(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(" ")
        }
    }

    fun part1(): Any {
        val springs = inputLinesSplit.map { it[0] }
        val numbers = inputLinesSplit.map { it[1] }.map { it.split(",").map { it.toInt() } }
        var possibleCombinationsSum = 0
        springs.forEachIndexed{i, spring->

            val nums = numbers[i]
            val combinations = generateCombinations(spring)

            combinations.forEach {combination->
                val numsCounted = mutableListOf<Int>()
                var currentNums = 0
                combination.forEach {
                    if (it=='#'){
                        currentNums++
                    }else{
                        if (currentNums>0){
                            numsCounted.add(currentNums)
                            currentNums=0
                        }
                    }
                }
                if (currentNums>0){
                    numsCounted.add(currentNums)
                    currentNums=0
                }
                if (nums==numsCounted){
                    possibleCombinationsSum++
                }
            }
        }
        return possibleCombinationsSum
    }
    fun generateCombinations(input: String): List<String> {
        val result = mutableListOf<String>()

        fun generateHelper(current: String, index: Int) {
            if (index == input.length) {
                result.add(current)
                return
            }

            if (input[index] == '?') {
                generateHelper("$current.", index + 1)
                generateHelper("$current#", index + 1)
            } else {
                generateHelper("$current${input[index]}", index + 1)
            }
        }

        generateHelper("", 0)
        return result
    }


    fun part2(): Any {
        val springs = inputLinesSplit.map { it[0]+'?'+it[0]+'?'+it[0]+'?'+it[0]+'?'+it[0] }
        val numbers = inputLinesSplit.map { it[1] }.map { it.split(",").map { it.toInt() } }
        var possibleCombinationsSum = 0
        springs.forEachIndexed{i, spring->
            val nums = numbers[i]
            val combinations = generateCombinations(spring)
            println("Line $i")
            combinations.forEach {combination->
                val numsCounted = mutableListOf<Int>()
                var currentNums = 0
                combination.forEach {
                    if (it=='#'){
                        currentNums++
                    }else{
                        if (currentNums>0){
                            numsCounted.add(currentNums)
                            currentNums=0
                        }
                    }
                }
                if (currentNums>0){
                    numsCounted.add(currentNums)
                    currentNums=0
                }
                if (nums==numsCounted){
                    possibleCombinationsSum++
                }
            }
        }
        return possibleCombinationsSum
    }
}
