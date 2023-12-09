package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import com.jonasbina.aoc23.solutions.Day01
import com.jonasbina.aoc23.solutions.Day08

fun main() {
    val input = Input.getDayInputLines(9)
    val testInput = Input.getTestInputLines(9)
    val test = false
    Day09(if (test) testInput else input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day09(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
        val layeredLines = inputLines.map {
            layerLine(it.split(" ").map { it.toInt() })
        }
        val predicted = layeredLines.map { predictNext(it) }
        return predicted.sum()
    }

    fun layerLine(line: List<Int>): List<List<Int>> {
        val layers = mutableListOf<List<Int>>()
        layers.add(line)
        var i = 0
        while (true){
            val layer = layers[i]
            val newLayer = mutableListOf<Int>()
            layer.drop(1).forEachIndexed { index, int ->
                newLayer.add(int-layer[index])
            }
            layers.add(newLayer)
            if (newLayer.filter { it==0 }.size == newLayer.size){
                break
            }
            i++
        }
        return layers
    }
    fun predictNext(layers: List<List<Int>>):Int{
        val betterLayers = layers.map { it.toMutableList() }.toMutableList()
        betterLayers.last().add(0)
        betterLayers.reversed().drop(1).forEachIndexed { ind, ints ->


            val index = betterLayers.indexOf(ints)
            val lineUnder = betterLayers[index+1]
            val lastInt = ints.last()
            val lastIntLineUnder = lineUnder.last()
            betterLayers[index].add(lastInt+lastIntLineUnder)
        }
        return betterLayers[0].last()
    }
    fun predictNextReversed(layers: List<List<Int>>):Int{
        val betterLayers = layers.map { it.toMutableList() }.toMutableList()
        betterLayers.last().add(0)
        betterLayers.reversed().drop(1).forEachIndexed { ind, ints ->


            val index = betterLayers.indexOf(ints)
            val lineUnder = betterLayers[index+1]
            val firstInt = ints.first()
            val firstIntLineUnder = lineUnder.first()

            betterLayers[index].add(0,firstInt-firstIntLineUnder)
        }
        return betterLayers[0].first()
    }

    fun part2(): Any {
        val layeredLines = inputLines.map {
            layerLine(it.split(" ").map { it.toInt() })
        }
        val predicted = layeredLines.map { predictNextReversed(it) }
        return predicted.sum()
    }
}
