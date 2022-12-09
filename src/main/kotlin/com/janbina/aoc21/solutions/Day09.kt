package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import kotlin.math.absoluteValue

fun main() {
    val input = Input.getDayInputLines(100)
    Day09(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day09(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplitted by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
        val positions= mutableListOf<Pair<Int,Int>>()
        val board = listOf("......",
        "......",
        "......",
        "......",
        "H.....")
        var xH = 0
        var yH = 0
        var xT = 0
        var yT=0
        board.forEachIndexed { index, s ->
            if (s.contains('H')){
                s.forEachIndexed { ind, c ->
                    if (c=='H'){
                        xH = ind
                        xT=ind
                        yT = index
                        yH=index
                    }
                }
            }
        }
        positions.add(Pair(xT,yT))


        inputLines.forEachIndexed { index, line ->
            val s = line.split(' ')
            val direction = s[0].toLowerCase()
            val length =s[1].toInt()
           for (i in 1..length){
               println(length)
            if (direction=="u"){
                yH--
            }
            if (direction=="d"){
                yH++
            }
            if (direction=="l"){
                xH--
            }
            if (direction=="r"){
                xH++
            }

            var yAxis = 0
            var xAxis = 0
            if (xT!=xH){
                if (xT>xH){
                    //Go left
                    yAxis=xH-xT

                }
                if (xT<xH){
                    //Go Right
                    yAxis=xH-xT
                }
            }




            if (yT!=yH){
                if (yT>yH){
                    //Go Up
                    yAxis=yH-yT


                }
                if (yT<yH){
                    //Go Down
                    yAxis=yH-yT
                }
            }

            if (xAxis.absoluteValue!=1&&yAxis.absoluteValue!=1){

                if (xAxis.absoluteValue>yAxis.absoluteValue){

                    if (xAxis<0){
                        xT--
                    }else{
                        xT++
                    }

                }else{
                if (yAxis!=0){
                    if (yAxis<0){
                        yT--
                    }else{
                        yT++
                    }
                }}

            }

            positions.add(Pair(xT,yT))
        }
        }


        return positions.toSet().size
    }

    fun part2(): Any {

        return 0
    }
}
