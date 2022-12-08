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


        val columns = mutableListOf<MutableList<Int>>()
        //                              X   Y
        val seeableTrees = mutableListOf<Pair<Int,Int>>()

        inputLines.forEachIndexed { index, s ->
            s.forEachIndexed { ind, c ->
                if (columns.lastIndex >= ind) {
                    columns[ind].add(index, c.toInt())
                }else{
                    columns.add(ind, mutableListOf(c.toInt()))
                }
            }
        }

        inputLines.forEachIndexed { index, line ->
            var highest = 0
            line.forEachIndexed { ind, it ->

                val num = it.toInt()
                if (num > highest) {
                    highest = num
                    seeableTrees.add(Pair(index,ind))
                }
            }

        }
        println(columns)
        columns.forEachIndexed { index, ints ->
            var height = 0
            ints.forEachIndexed { ind, it ->
                if (it > height) {
                    height = it

                    seeableTrees.add(Pair(index,ind))
                    println("adding a tree to $index for x and $ind for y")
                }
            }
        }

        return seeableTrees.toSet().size
    }

    fun part2(): Any {

        return 0
    }
}
