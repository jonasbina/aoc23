package com.jonasbina.aoc23.solutions

import com.janbina.aoc20.utils.Input
import com.janbina.aoc20.utils.Input.getTestInputLines
import kotlin.math.min

fun main() {
    val input = Input.getDayInputLines(5)
    val testInput = getTestInputLines(5)
    val test = false

    Day05(if (test) testInput else input).also {
        println("---\nPart 1: ${it.part1()}\n---")
        println("---\nPart 2: ${it.part2()}\n---")
    }
}

class Day05(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
        val maps= mutableListOf<List<Range>>()
        var currentMap = mutableListOf<Range>()

        val seeds = inputLines.first().split(":")[1].split(" ").filter { it.isNotEmpty() }.map { it.toLong() }



        inputLines.drop(2).forEach {
            if (it.isNotEmpty()){
                if (it.contains(":")){
                    if (currentMap.isNotEmpty()){
                        maps.add(currentMap)
                    }
                    currentMap = mutableListOf<Range>()
                }else{
                    val split = it.split(" ")
                    val from1 = split[1].toLong()
                    val from2 = split[0].toLong()
                    val length = split[2].toLong()
                    val range = Range(from1, from2, length)
                    currentMap.add(range)
                }
            }
        }
        if (currentMap.isNotEmpty()){
            maps.add(currentMap)
            currentMap = mutableListOf()
        }
        val locations = seeds.map {
            var currentInt = it
            maps.forEach { map->
                var found = false
                map.forEach { range->
                    if (currentInt in range.startFirst until range.endFirst&&!found){
                        currentInt = currentInt - range.startFirst + range.startSecond
                        found=true
                    }
                }
            }
            currentInt
        }
        return locations.sorted()[0]
    }

    fun part2(): Any {
        println("Part 2 just started!")
        val maps= mutableListOf<List<Range>>()
        var currentMap = mutableListOf<Range>()

        val seedRanges = inputLines.first().split(":")[1].split(" ").filter { it.isNotEmpty() }.map { it.toLong() }.chunked(2)
        val seeds = seedRanges.map { Seed(it[0], it[1]) }
        println("Amassed all seeds!")

        inputLines.drop(2).forEach {
            if (it.isNotEmpty()){
                if (it.contains(":")){
                    if (currentMap.isNotEmpty()){
                        maps.add(currentMap)
                    }
                    currentMap = mutableListOf<Range>()
                }else{
                    val split = it.split(" ")
                    val from1 = split[1].toLong()
                    val from2 = split[0].toLong()
                    val length = split[2].toLong()
                    val range = Range(from1, from2, length)
                    currentMap.add(range)
                }
            }
        }
        if (currentMap.isNotEmpty()){
            maps.add(currentMap)
            currentMap = mutableListOf()
        }
        println("Collected all maps!")
        println("Now getting locations - this is gonna take a while!")
        var minLocation = Long.MAX_VALUE
        var amountDone = 0L
        val totalNumber = seeds.map { it.length }.sum()
        val start = System.currentTimeMillis()
        seeds.forEach {seed->
            for (it in seed.start .. seed.end){
                var currentInt = it
                maps.forEach { map->
                    var found = false
                    map.forEach { range->
                        if (currentInt in range.startFirst until range.endFirst&&!found){
                            currentInt = currentInt - range.startFirst + range.startSecond
                            found=true
                        }
                    }
                }
                minLocation = min(minLocation, currentInt)
                amountDone++
                val percentageDone = (amountDone.toDouble()/totalNumber)*100
                val percentageDoneRounded = (percentageDone*1000).toInt().toDouble()/1000
                if (amountDone%1000000==0L){
                    val timePassed = System.currentTimeMillis()-start
                    val timePer1000000Seeds = timePassed / (amountDone/1000000)

                    println("$percentageDoneRounded% || Expected time left: ${timePer1000000Seeds*((totalNumber-amountDone)/1000000)/1000/60} minutes")
                }
            }
        }
        println("Locations done!")
        println("Part 2 took (only) ${System.currentTimeMillis()-start} millis!")
        return minLocation
    }
}
data class Range(
    val startFirst : Long,
    val startSecond : Long,
    val length : Long,
    val endFirst : Long = startFirst+length,
    val endSecond : Long = startSecond+length
)
data class Seed(
    val start : Long,
    val length: Long,
    val end : Long = start+length
)