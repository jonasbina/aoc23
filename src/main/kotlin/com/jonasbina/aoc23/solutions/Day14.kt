package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import com.janbina.aoc20.utils.sorted
import com.jonasbina.aoc23.solutions.Day01
import com.jonasbina.aoc23.solutions.Day08

fun main() {
    val input = Input.getDayInputLines(14)
    val testInput = Input.getTestInputLines(14)
    val test = false
    Day14(if (test) testInput else input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day14(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
        println(inputLines)
        val columns = columnRowConverter(inputLines)
        println(columns.joinToString(","))
        val movedToNorth = letItFall(columns.map { it.reversed() })
        println(movedToNorth.joinToString(","))
        val movedToNorthRows = columnRowConverter(movedToNorth)
        println(movedToNorthRows.joinToString(",\n"))
        return movedToNorthRows.reversed().mapIndexed { index, s ->
            val x = s.count{it == 'O'} * (index+1)
            x
        }.sum()
    }
    fun letItFall(list:List<String>):List<String>{
        return list.map {
            var newColumn = ""
            var current = ""
            it.forEach {
                if (it=='#'){
                    newColumn+=current.sorted()+it
                    current=""
                }else{
                    current+=it
                }
            }
            newColumn+=current.sorted()
            newColumn.reversed()
        }
    }
    fun columnRowConverter(list: List<String>):List<String>{
        val newList = mutableListOf<String>()
        for (i in 0 until list[0].length){
            newList.add(list.map { it[i] }.joinToString(""))
        }
        return newList
    }
    fun part2(): Any {
        var currentLines = inputLines
        val roundCount = /*1000000000*/1
        var last = 0
        for (round in 1 .. roundCount){
            val percentage = (((round.toDouble()/roundCount)*100)*100).toInt().toDouble()/100
            if (round%1000==0) {
//                println("$percentage%")
            }
            //normal
            val west = letItFall(inputLines.map { it.reversed() }).map { it.reversed() }
            //normal
            val south = columnRowConverter(letItFall(columnRowConverter(west)))
            //normal
            val east  = letItFall(south)
            //normal
            val north = letItFall(columnRowConverter(east).map { it.reversed() })

            currentLines = columnRowConverter(north.map { it.reversed() })
        }
        return currentLines.reversed().mapIndexed { index, s ->
            val x = s.count{it == 'O'} * (index+1)
            x
        }.sum()
    }
}
