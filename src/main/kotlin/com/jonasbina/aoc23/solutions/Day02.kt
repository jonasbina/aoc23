package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import com.jonasbina.aoc23.solutions.Day01
import kotlin.math.max

fun main() {
    val input = Input.getDayInputLines(2)
    Day02(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day02(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplitted by lazy {
        inputLines.map {
            it.split(":")
        }
    }

    fun part1(): Any {
        var sum = 0
        inputLinesSplitted.forEach {
            val game = it[0]
            val packs = it[1]
            var valid = true
            packs.split(";").forEach {
                var redForPack = 0
                var greenForPack = 0
                var blueForPack = 0



                val colors = it.split(",")
                colors.forEach {singleColor->
                    val noSpaceSingleColor = singleColor.replace(" ", "")

                    val amount = noSpaceSingleColor.filter { it.isDigit() }.toString().toInt()
                    if (noSpaceSingleColor.contains("red")){
                        redForPack+= amount
                    }
                    if (noSpaceSingleColor.contains("green")){
                        greenForPack+=amount
                    }
                    if (noSpaceSingleColor.contains("blue")){
                        blueForPack+=amount
                    }
                }
                if (!(redForPack<=12&&greenForPack<=13&&blueForPack<=14)){
                   valid = false
                }
            }
            if (valid){
                sum+=game.replace("Game ", "").toInt()
            }
        }
        return sum
    }

    fun part2(): Any {
        var sum = 0
        inputLinesSplitted.forEach {
            val game = it[0]
            val packs = it[1]
            var redMax = 0
            var greenMax = 0
            var blueMax = 0
            packs.split(";").forEach {
                var redForPack = 0
                var greenForPack = 0
                var blueForPack = 0



                val colors = it.split(",")
                colors.forEach {singleColor->
                    val noSpaceSingleColor = singleColor.replace(" ", "")

                    val amount = noSpaceSingleColor.filter { it.isDigit() }.toString().toInt()
                    if (noSpaceSingleColor.contains("red")){
                        redForPack+= amount
                    }
                    if (noSpaceSingleColor.contains("green")){
                        greenForPack+=amount
                    }
                    if (noSpaceSingleColor.contains("blue")){
                        blueForPack+=amount
                    }
                }
                redMax = max(redMax, redForPack)
                greenMax = max(greenMax, greenForPack)
                blueMax = max(blueMax, blueForPack)
            }
            sum+=redMax*greenMax*blueMax
        }
        return sum
    }
}
