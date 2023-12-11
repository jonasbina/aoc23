package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import com.jonasbina.aoc23.solutions.Day01
import com.jonasbina.aoc23.solutions.Day08
import java.util.Vector

fun main() {
    val input = Input.getDayInputLines(10)
    val testInput = Input.getTestInputLines(10)
    val test = true
    Day10(if (test) testInput else input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day10(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(",")
        }
    }
    val compatibilityMap = mapOf(
        '|' to Pair(listOf('L', 'J', 'S'), listOf('F', '7', 'S')),
        '-' to Pair(listOf('L', 'J', 'S'), listOf('F', '7', 'S')),
        'L' to Pair(listOf('L', 'J', 'S'), listOf('F', '7', 'S')),
        'J' to Pair(listOf('L', 'J', 'S'), listOf('F', '7', 'S')),
        '7' to Pair(listOf('L', 'J', 'S'), listOf('F', '7', 'S')),
        'F' to listOf('L', 'J', 'F', 'S'),
        'S' to listOf('L', 'J', 'F', 'S', '-', '|', '7')
    )
    fun part1(): Any {
        val lineWithStart = inputLines.mapIndexed { index, s -> Pair(index, s) }.filter {it.second.contains("S")}.first()
        val startPosition = Pair(lineWithStart.second.indexOf('S'), lineWithStart.first)
        var loop = mutableListOf<Pair<Int, Int>>()
        loop.add(startPosition)
//        var currentPosition: Pair<Int,Int> = startPosition
//        var lastPosition : Pair<Int,Int>? = null
//        while (true){
//
//            val res = findNextPosition(currentPosition, lastPosition)
//            lastPosition = currentPosition
//            currentPosition = res
//            if (inputLines[currentPosition.second][currentPosition.first]!='S'){
//                loop.add(currentPosition)
//                println(loop.joinToString(",\n"))
//            }else{
//                break
//            }
//        }
        return loop.size
    }
    fun findNextPosition(position: Pair<Int, Int>, lastCharacter:Pair<Int,Int>?):List<Pair<Char?,Pair<Int,Int>>>{
        //ugly i know
        val south:Pair<Char?, Pair<Int,Int>>  = Pair(inputLines.getOrNull(position.second+1)?.getOrNull(position.first), Pair(position.second+1,position.first))
        val north:Pair<Char?, Pair<Int,Int>> = Pair(inputLines.getOrNull(position.second-1)?.getOrNull(position.first), Pair(position.second-1,position.first))
        val east:Pair<Char?, Pair<Int,Int>>  = Pair(inputLines.getOrNull(position.second)?.getOrNull(position.first+1), Pair(position.second,position.first+1))
        val west:Pair<Char?, Pair<Int,Int>>  = Pair(inputLines.getOrNull(position.second)?.getOrNull(position.first-1), Pair(position.second,position.first-1))


        return listOf(south, north, east, west)
    }

    fun part2(): Any {

        return 0
    }
}
