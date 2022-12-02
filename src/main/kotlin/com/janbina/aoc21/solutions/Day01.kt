package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input

fun main() {
    val input = Input.getDayInputLines(1)
    Day01(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day01(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplitted by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
        val elfs = mutableListOf<Int>()
        var num = 0
        inputLines.forEachIndexed {ind,it->
            if(it==""){
                elfs.add(num)
                num =0
            }else{

                num +=it.toInt()
                if(ind ==inputLines.lastIndex){
                    elfs.add(num)
                }
            }
        }

        return elfs.maxByOrNull {it}!!
    }

    fun part2(): Any {
        val elfs = mutableListOf<Int>()
        var num = 0
        inputLines.forEachIndexed {ind,it->
            if(it==""){
                elfs.add(num)
                num =0
            }else{

                num +=it.toInt()
                if(ind ==inputLines.lastIndex){
                    elfs.add(num)
                }
            }
        }
        val sorted = elfs.sortedBy { it}.reversed()
        return sorted.first() + sorted[1]+sorted[2]

    }
}
