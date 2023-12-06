package com.jonasbina.aoc23.solutions

import com.janbina.aoc20.utils.Input

fun main() {
    val input = listOf(listOf(54, 70, 82, 75), listOf(239, 1142, 1295, 1253))
    val part2Input = listOf(54708275L, 239114212951253L)


    val testInput = listOf(listOf(7, 15, 30), listOf(9, 40, 200))
    val test2Input = listOf(71530L, 940200L)
    val test = false
    Day06(if (test) testInput else input, if (test) test2Input else part2Input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day06(
    private val inputLines: List<List<Int>>,
    private val inputLinesPart2: List<Long>
) {

    fun part1(): Any {
        var product = 1
        val times = inputLines[0]
        val distances = inputLines[1]
        for (i in 0..times.lastIndex) {
            val time = times[i]
            val distance = distances[i]
            var longerDistanceCounter = 0


            for (releaseTime in 0..time) {
                val speed = releaseTime
                val finalDistance = speed * (time - releaseTime)
                if (finalDistance > distance) {
                    longerDistanceCounter++
                }
            }
            product *= longerDistanceCounter
        }
        return product
    }

    fun part2(): Any {
        var res = 0


        val time = inputLinesPart2[0]
        val distance = inputLinesPart2[1]



        for (releaseTime in 0..time) {
            val speed = releaseTime
            val finalDistance = speed * (time - releaseTime)
            if (finalDistance > distance) {
                res++
            }
        }

        return res
    }
}
