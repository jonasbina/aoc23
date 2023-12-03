package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import com.jonasbina.aoc23.solutions.Day01
import java.lang.Exception
import kotlin.random.Random

fun main() {
    val input = Input.getDayInputLines(3)
    Day03(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day03(
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
        inputLines.forEachIndexed {lineIndex, line->
            var symbol = false
            var currentInt = ""
            line.forEachIndexed {index, c ->
                if (c.isDigit()){
                    currentInt+=c
                    if (areSymbolsAround(lineIndex, index)){
                        symbol = true
                    }

                }else{
                    val areSymbolsAround = areSymbolsAround(lineIndex, index)
                    if (areSymbolsAround){
                        symbol = true
                    }
                    if (currentInt.isNotEmpty()){
                        if (symbol) {
                            sum += currentInt.toInt()
                        }
                    }else{

                        symbol=areSymbolsAround

                    }
                    currentInt = ""
                }
                if(index==line.lastIndex&&currentInt.isNotEmpty()){
                    if (symbol){
                        sum+=currentInt.toInt()
                    }
                }
            }
        }
        return sum
    }
    val symbols = "!@#$%^&*()_-+=~`';:}[{]/>?,<|"

    fun Char.isSymbol(): Boolean {
        return symbols.contains(this)
    }
    fun areSymbolsAround(lineIndex:Int, index:Int):Boolean{
        if (inputLines.size-1>=lineIndex+1){
            val nextLine = inputLines[lineIndex+1]
            val characterAbove = nextLine[index]
            if (characterAbove.isSymbol()){
                return true
            }
        }
        if (lineIndex-1>=0){
            val lastLine = inputLines[lineIndex-1]
            val characterUnder = lastLine[index]
            if (characterUnder.isSymbol()){
                return true
            }
        }

        return inputLines[lineIndex][index].isSymbol()
    }

    fun part2(): Any {
        return 0
    }
}
