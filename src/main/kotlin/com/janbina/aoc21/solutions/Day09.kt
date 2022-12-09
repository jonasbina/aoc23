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
    fun part1():Any{
        val locations = mutableListOf<Location>()
        var xH = 0
        var yH=4
        var xT = 0
        var yT = yH
        inputLines.forEach {s->
            val command = s.split(' ')
            val direction = command[0]
            val length = command[1].toInt()
            for(i in 1..length){
                var xMove = 0
                var yMove =0
                if (direction== "U"){
                    yH--
                }
                if (direction== "D"){
                    yH++
                }
                if (direction== "L"){
                    xH--
                }
                if (direction== "R"){
                    xH++
                }

                xMove=xH-xT
                yMove=yH-yT
                if (xMove.absoluteValue+yMove.absoluteValue==1){
                    println("jsem vedle pico")
                }else{
                    if (xMove.absoluteValue==1&&yMove.absoluteValue==1){
                        println("diagonal pico")
                    }else{
                        if (xMove>0){
                            xT++
                        }
                        if (yMove>0){
                            yT++
                        }
                        if (xMove<0){
                            xT--
                        }
                        if (yMove<0){
                            yT--
                        }


                    }
                }
                locations.add(Location(xT,yT))
            }
        }

        return locations.toSet().size
    }
    fun part2(): Any {
        val locations = mutableListOf<Location>()
        var xH = 0
        var yH=4

        val tails = mutableListOf<Tail>()
        for (i in 1..9){
            tails.add(Tail(xH,yH))
        }
        locations.add(Location(xH,yH))
        inputLines.forEach {s->
            val command = s.split(' ')
            val direction = command[0]
            val length = command[1].toInt()
            for(i in 1..length){
                var xMove = 0
                var yMove =0
                if (direction== "U"){
                    yH--
                }
                if (direction== "D"){
                    yH++
                }
                if (direction== "L"){
                    xH--
                }
                if (direction== "R"){
                    xH++
                }

                tails.forEachIndexed { index, tail ->

                    if (index==0){
                        tail.move(xH,yH)
                    }else{
                        val previous = tails[index-1]
                        tail.move(previous.x,previous.y)
                    }
                    locations.add(Location(tail.x,tail.y))
                }

            }
            tails.forEach {
                println("${it.x}, ${it.y}")
            }
        }

        return locations.toSet().size
    }


}
data class Location(
    var x :Int,
    var y :Int
)
class Tail(
    var x :Int,
    var y :Int
) {
    fun move(xH: Int, yH: Int) {
        var xT = x
        var yT = y
        val xMove = xH - xT
        val yMove = yH - yT
        if (xMove.absoluteValue + yMove.absoluteValue == 1) {
            println("jsem vedle pico")
        } else {
            if (xMove.absoluteValue == 1 && yMove.absoluteValue == 1) {
                println("diagonal pico")
            } else {
                if (xMove > 0) {
                    xT++
                }
                if (yMove > 0) {
                    yT++
                }
                if (xMove < 0) {
                    xT--
                }
                if (yMove < 0) {
                    yT--
                }


            }
        }
        x = xT
        y = yT
    }
}


