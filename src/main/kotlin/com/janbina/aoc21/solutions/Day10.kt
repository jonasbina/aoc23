package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input

fun main() {
    val input = Input.getDayInputLines(100)
    Day10(input).also {
        println(it.part3())
        println(it.part2())
    }
}

class Day10(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplitted by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
        var cycle = 0
        var x= 1
        val listOfCycles = listOf(20, 60, 100, 140, 180, 220)
        var cycles = mutableListOf<Int>()
        inputLines.forEach {
            cycle++
            if (it.split(' ')[0] == "addx"){
                var num = it.split(' ')[1].toInt()
                for (i in 1..num){
                    if (listOfCycles.contains(cycle)){
                        println("Adding $x * $cycle == ${x*cycle}")
                        cycles.add(x*cycle)
                    }

                    cycle++

                }
                x+=num
            }
            if (listOfCycles.contains(cycle)){
                println("Adding $x * $cycle == ${x*cycle}")
                cycles.add(x*cycle)
            }
        }

        return cycles.sum()
    }

    fun part2(): Any {

        return 0
    }
    fun part3():Any{
        var cycle = 0
        var x = 1
        val listOfCycles = listOf(20, 60, 100, 140, 180, 220)
        var toAdd = mutableListOf<Int>()
        var cycles = mutableListOf<Int>()
        var i = -1
        var xPerCycle = mutableListOf<Int>()
        var noop =false

        while (i<=inputLines.lastIndex-1) {
            cycle++




            if (toAdd.isNotEmpty()) {
                x += toAdd[0]
                println("new $x after adding ${toAdd[0]}")
                toAdd = mutableListOf()
            }
            if (!noop) {
                i++


                val s = inputLines[i].split(' ')
                if (s[0] == "addx") {
                    val num = s[1].toInt()
                    toAdd.add(num)
                    println("adding $num, x is $x")
                }



                xPerCycle.add(x)

                if (listOfCycles.contains(cycle)) {
                    cycles.add(x * cycle)
                    println("as $cycle adding ${x * cycle}")
                }
                println("After cycle $cycle x is $x, and the line was ${inputLines[i]}")
                if (inputLines[i] == "noop") {

                    noop = true

                }
            }else{
                noop=false
            }


        }

        if (toAdd.isNotEmpty()) {
            cycle++
            x += toAdd[0]
            println("After cycle $cycle x is $x, and the line was ${inputLines[i]}")
            println("new $x after adding ${toAdd[0]}")

        }
        println(cycles)
        return x*cycle
    }
}
