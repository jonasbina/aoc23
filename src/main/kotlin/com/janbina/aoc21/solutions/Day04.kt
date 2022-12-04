package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input

fun main() {
    val input = Input.getDayInputLines(4)
    Day04(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day04(
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
        inputLines.forEach {
            val elves = it.split(',')
            val first = elves[0]
            val second = elves[1]

            val firstList = mutableListOf<Int>()
            for (i in first.split('-')[0].toInt()..first.split('-')[1].toInt()) {
                firstList.add(i)
            }

            val secondList = mutableListOf<Int>()
            for (i in second.split('-')[0].toInt()..second.split('-')[1].toInt()) {
                secondList.add(i)
            }
            if (firstList.lastIndex > secondList.lastIndex) {
                if (firstList.containsAll(secondList)) {
                    sum++
                }
            }
            if (firstList.lastIndex <= secondList.lastIndex) {
                if (secondList.containsAll(firstList)) {
                    sum++
                }
            }
        }
        return sum
    }

    fun part2(): Any {
        var sum = 0
        inputLines.forEach {
            val elves = it.split(',')
            val first = elves[0]
            val second = elves[1]

            val firstList = mutableListOf<Int>()
            for (i in first.split('-')[0].toInt()..first.split('-')[1].toInt()) {
                firstList.add(i)
            }

            val secondList = mutableListOf<Int>()
            for (i in second.split('-')[0].toInt()..second.split('-')[1].toInt()) {
                secondList.add(i)
            }
            if (firstList.lastIndex > secondList.lastIndex) {
                var summed =false
                secondList.forEach {
                    if (!summed){
                    if (firstList.contains(it)){

                        sum++
                        summed = true
                    }}
                }
            }
            if (firstList.lastIndex <= secondList.lastIndex) {
                var summed =false
                firstList.forEach {
                    if (!summed){
                        if (secondList.contains(it)){

                            sum++
                            summed = true
                        }}
                }
            }
        }
        return sum
    }
}
