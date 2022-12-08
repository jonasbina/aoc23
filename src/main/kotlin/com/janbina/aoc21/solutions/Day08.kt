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


        var columns = mutableListOf<MutableList<Int>>()
        //                              X   Y
        val seeableTrees = mutableListOf<Pair<Int,Int>>()

        inputLines.forEachIndexed { index, s ->
            s.forEachIndexed { ind, c ->

                if (columns.lastIndex >= ind) {
                    columns[ind].add(index, c.toString().toInt())
                }else{
                    val newList = mutableListOf<Int>()

                    newList.add(index,c.toString().toInt())
                    columns.add(ind, newList)
                }
            }
        }


        inputLines.forEachIndexed { index, line ->
            val before = seeableTrees.toList()
            var highest = 0
            line.forEachIndexed { ind, it ->

                val num = it.toInt()
                if (num > highest||highest==0) {
                    highest = num
                    seeableTrees.add(Pair(ind,index))

                }

            }
            var highestBackwards = 0
            line.reversed().forEachIndexed { indd, it ->
                val ind = line.length-1-indd
                val num = it.toInt()
                if (num > highestBackwards||highestBackwards==0) {
                    highestBackwards = num
                    seeableTrees.add(Pair(ind,index))

                }
            }
            println("after - before :${seeableTrees-before}, line $line")

        }

        println(columns)
        columns.forEachIndexed { index, ints ->
            var height = 0
            val before = seeableTrees.toList()
            println("column : $ints")
            ints.forEachIndexed { ind, it ->
                if (it > height||height==0) {
                    height = it

                    seeableTrees.add(Pair(index,ind))
                    println("adding a tree to $index for x and $ind for y")
                }
            }
            var heightBackwards = 0
            ints.reversed().forEachIndexed { indd, it ->
                val ind = ints.lastIndex-indd
                if (it > heightBackwards||heightBackwards ==0) {
                    heightBackwards = it

                    seeableTrees.add(Pair(index,ind))
                    println("adding a tree to $index for x and $ind for y")

                }
            }
            println(seeableTrees.size-before.size)
        }
        val newTrees = mutableListOf<Pair<Int,Int>>()


        println(seeableTrees)
        return seeableTrees.toSet().size
    }

    fun part2(): Any {

        return 0
    }
}
