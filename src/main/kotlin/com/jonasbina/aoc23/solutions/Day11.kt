package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import com.jonasbina.aoc23.solutions.Day01
import com.jonasbina.aoc23.solutions.Day08
import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val input = Input.getDayInputLines(11)
    val testInput = Input.getTestInputLines(11)
    val test = false
    Day11(if (test) testInput else input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day11(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
        val galaxies = mutableListOf<Pair<Long,Long>>()
        inputLines.forEachIndexed { y, s ->
            s.forEachIndexed { x, c ->
                if (c=='#'){
                    galaxies.add(Pair(x.toLong(),y.toLong()))
                }
            }
        }
        val emptyRows = inputLines.indices.filter { i -> inputLines[i].all { it == '.' } }
        val emptyColumns = inputLines[0].indices.filter { j -> inputLines.all { it[j] == '.' } }
        val expandedGalaxies = galaxies.map {galaxy->
            var newX = galaxy.first
            var newY = galaxy.second
            emptyColumns.filter { it<galaxy.first}.forEach {
                newX+=1
            }
            emptyRows.filter { it<galaxy.second}.forEach {
                newY+=1
            }
            Pair(newX, newY)
        }
        val pairs = mutableListOf<Pair<Pair<Long,Long>,Pair<Long,Long>>>()
        expandedGalaxies.forEach { first->
            expandedGalaxies.forEach { second->
                if (first!=second){
                    if (first.first>second.first){
                        pairs.add(Pair(first, second))
                    }else{
                        if (first.first!=second.first){
                            pairs.add(Pair(second, first))
                        }else{
                            if (first.second>second.second){
                                pairs.add(Pair(first, second))
                            }else{
                                pairs.add(Pair(second, first))
                            }
                        }
                    }
                }
            }
        }



        return pairs.map {
            abs(it.first.first-it.second.first)+ abs(it.first.second-it.second.second)
        }.sum()/2
    }
    fun part2(): Any {
        val galaxies = mutableListOf<Pair<Long,Long>>()
        inputLines.forEachIndexed { y, s ->
            s.forEachIndexed { x, c ->
                if (c=='#'){
                    galaxies.add(Pair(x.toLong(),y.toLong()))
                }
            }
        }
        val emptyRows = inputLines.indices.filter { i -> inputLines[i].all { it == '.' } }
        val emptyColumns = inputLines[0].indices.filter { j -> inputLines.all { it[j] == '.' } }
        val expandedGalaxies = galaxies.map {galaxy->
            var newX = galaxy.first
            var newY = galaxy.second
            emptyColumns.filter { it<galaxy.first}.forEach {
                newX+=1000000
            }
            emptyRows.filter { it<galaxy.second}.forEach {
                newY+=1000000
            }
            Pair(newX, newY)
        }
        val pairs = mutableListOf<Pair<Pair<Long,Long>,Pair<Long,Long>>>()
        expandedGalaxies.forEach { first->
            expandedGalaxies.forEach { second->
                if (first!=second){
                    if (first.first>second.first){
                        pairs.add(Pair(first, second))
                    }else{
                        if (first.first!=second.first){
                            pairs.add(Pair(second, first))
                        }else{
                            if (first.second>second.second){
                                pairs.add(Pair(first, second))
                            }else{
                                pairs.add(Pair(second, first))
                            }
                        }
                    }
                }
            }
        }



        return pairs.map {
            abs(it.first.first-it.second.first)+ abs(it.first.second-it.second.second)
        }.sum()/2
    }
}
