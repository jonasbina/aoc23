package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import com.jonasbina.aoc23.solutions.Day01
import com.jonasbina.aoc23.solutions.Day08

fun main() {
    val input = Input.getDayInputLines(1)
    val testInput = Input.getTestInputLines(1)
    val test = true
    Day0(if (test) testInput else input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day0(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {

        return 0
    }

    fun part2(): Any {

        return 0
    }
}
