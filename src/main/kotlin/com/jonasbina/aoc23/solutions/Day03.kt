package com.jonasbina.aoc23.solutions

import com.janbina.aoc20.utils.Input
import kotlin.math.abs

fun main() {
    val input = Input.getDayInputLines(3)
    Day03(input).also {
        val part1 = it.part1()
        println(part1.map { it.number }.sum())
        println(it.part2(part1))
    }
}

@Suppress("MemberVisibilityCanBePrivate")
class Day03(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(",")
        }
    }
    fun part1(): List<PartNumber> {
        val partNumbers = mutableListOf<PartNumber>()
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
                            partNumbers.add(PartNumber(lineIndex, index-1, currentInt.toInt()))
                        }
                    }
                    symbol=areSymbolsAround

                    currentInt = ""
                }
                if(index==line.lastIndex&&currentInt.isNotEmpty()){
                    if (symbol){
                        partNumbers.add(PartNumber(lineIndex, index, currentInt.toInt()))
                    }
                }
            }
        }
        return partNumbers
    }
    fun Char.isSymbol() = !isDigit() && !equals('.')
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

    fun part2(partNumbers:List<PartNumber>): Any {
        var sum = 0
        inputLines.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { index, c ->
                if (c=='*'){
                    val around = partNumbersAround(lineIndex,index,partNumbers)
                    if (around.size==2){
                        sum+=around[0].number*around[1].number
                    }
                }
            }
        }
        return sum
    }
    fun partNumbersAround(lineIndex: Int, index: Int, partNumbers: List<PartNumber>):List<PartNumber>{
        val around = mutableListOf<PartNumber>()
        partNumbers.forEach {
            val size = it.number.toString().length
            val endIndex = it.index
            val startIndex = it.index-size
            val line = it.lineIndex
            if (abs( lineIndex - line) <=1){
                if (index in startIndex .. endIndex+1){
                    around.add(it)
                }
            }
        }
        return around
    }
    data class PartNumber(
        val lineIndex: Int,
        val index: Int,
        val number: Int
    )
}
