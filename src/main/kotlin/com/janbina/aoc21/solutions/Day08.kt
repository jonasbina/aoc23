package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input

fun main() {
    val input = Input.getDayInputLines(8)
    Day08(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day08(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplitted by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
        var sum = 0
        val colums = inputLines.size - 1
        val columns = mutableListOf<MutableList<Int>>()

        inputLines.forEachIndexed { index, s ->
            s.forEachIndexed { ind, c ->
                if (columns.lastIndex >= ind) {
                    columns[ind].set(index, c.toInt())
                }
            }
        }

        inputLines.forEach { line ->
            var highest = 0
            line.forEach {

                val num = it.toInt()
                if (num > highest) {
                    highest = num
                    sum++
                }
            }

        }
        columns.forEachIndexed { index, ints ->
            var height = 0
            ints.forEach {
                if (it > height) {
                    height = it
                    sum++
                }
            }
        }

        return sum
    }

    fun part2(): Any {

        return 0
    }
}
