package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import com.jonasbina.aoc23.solutions.Day01
import com.jonasbina.aoc23.solutions.Day08

fun main() {
    val input = Input.getDayInputLines(13)
    val testInput = Input.getTestInputLines(13)
    val test = true
    Day13(if (test) testInput else input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day13(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
        val lists = splitList(inputLines)
        val res = lists.sumOf { rows ->
            val columns = mutableListOf<String>()
            for (i in 0 until rows[0].length) {
                columns.add(rows.map { it[i] }.joinToString(""))
            }
            var sum = 0
            rows.forEachIndexed { index, s ->
                if (index > 0) {
                    if (rows[index - 1] == s) {
                        sum += index * 100
                    }
                }
            }

            columns.forEachIndexed { index, s ->
                if (index > 0) {
                    if (columns[index - 1] == s) {
                        sum += index
                    }
                }
            }

            sum
        }
        return res
    }

    fun splitList(input: List<String>): List<List<String>> {
        val result = mutableListOf<List<String>>()
        var currentList = mutableListOf<String>()

        for (line in input) {
            if (line.isBlank()) {
                if (currentList.isNotEmpty()) {
                    result.add(currentList.toList())
                    currentList.clear()
                }
            } else {
                currentList.add(line)
            }
        }

        if (currentList.isNotEmpty()) {
            result.add(currentList.toList())
        }

        return result
    }


    fun part2(): Any {

        return 0
    }
}
