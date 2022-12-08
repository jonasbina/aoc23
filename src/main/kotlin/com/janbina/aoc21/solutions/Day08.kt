package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import com.janbina.aoc20.utils.max


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
        val trees = mutableListOf<Pair<Int,Int>>()

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
            val before = trees.toList()
            var highest = 0
            line.forEachIndexed { ind, it ->

                val num = it.toInt()
                if (num > highest||highest==0) {
                    highest = num
                    trees.add(Pair(ind,index))

                }

            }
            var highestBackwards = 0
            line.reversed().forEachIndexed { indd, it ->
                val ind = line.length-1-indd
                val num = it.toInt()
                if (num > highestBackwards||highestBackwards==0) {
                    highestBackwards = num
                    trees.add(Pair(ind,index))

                }
            }
            println("after - before :${trees-before}, line $line")

        }

        println(columns)
        columns.forEachIndexed { index, ints ->
            var height = 0
            val before = trees.toList()
            println("column : $ints")
            ints.forEachIndexed { ind, it ->
                if (it > height||height==0) {
                    height = it

                    trees.add(Pair(index,ind))
                    println("adding a tree to $index for x and $ind for y")
                }
            }
            var heightBackwards = 0
            ints.reversed().forEachIndexed { indd, it ->
                val ind = ints.lastIndex-indd
                if (it > heightBackwards||heightBackwards ==0) {
                    heightBackwards = it

                    trees.add(Pair(index,ind))
                    println("adding a tree to $index for x and $ind for y")

                }
            }
            println(trees.size-before.size)
        }
        val newTrees = mutableListOf<Pair<Int,Int>>()


        println(trees)
        return trees.toSet().size-12
    }

    fun part2(): Any {
        val trees = mutableListOf<Int>()
        inputLines.forEachIndexed { index, s ->
            s.forEachIndexed { ind, c ->
                val int = c.toString().toInt()
                var left =0
                var lblocked = false
                if (ind>0){
                    for(i in ind-1 downTo 0){
                        if (!lblocked){
                        if (s[i].toString().toInt() >= int){
                            left++
                            lblocked=true
                        }else{
                            left++
                        }
                        }
                    }
                }
                var right = 0
                var rblocked = false
                if (ind<s.lastIndex){
                    println("${ind+1} downTo ${s.lastIndex}")
                    for(i in ind+1 .. s.lastIndex){
                        if (!rblocked){
                            if (s[i].toString().toInt() >= int){
                                right++
                                rblocked=true
                            }else{
                                right++
                            }
                        }
                    }
                }
                var up =0
                var ublocked =false

                if (index>0){

                    for (i in index-1 downTo 0){
                        if (!ublocked){
                            val column = inputLines[i]
                            val tree = column[ind].toString().toInt()
                            up++
                            if (tree>=int){
                                ublocked=true
                            }

                        }
                    }
                }

                var down =0
                var dblocked =false
                if (index<inputLines.lastIndex){

                    for (i in index+1 .. inputLines.lastIndex){
                        if (!dblocked){
                            val column = inputLines[i]
                            val tree = column[ind].toString().toInt()
                            down++
                            if (tree>=int){
                                dblocked=true
                            }

                        }
                    }
                }
                println("l $left, r $right, u $up, d $down")
                trees.add(left*up*down*right)

            }
        }
        return trees.max()
    }
}


