package com.jonasbina.aoc23.solutions

import com.janbina.aoc20.solutions.Day14
import com.janbina.aoc20.utils.Input

fun main() {
    val input = Input.getDayInputText(15)
    val testInput = Input.getTestInputText(15)
    Day15(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day15(
    private val input: String
) {
    fun part1(): Int =
        input.split(",").sumOf { sequence ->
            decodeSequence(sequence)
        }

    fun part2(): Int {
        val map = hashMapOf<Int, List<Pair<String,Int>>>()
        input.split(",").forEach { sequence ->
            val processed = getBoxId(sequence)
            val boxId = processed.first
            val name = processed.second.first
            val value = processed.second.second

            if (map.containsKey(boxId)){
                if (value==-1){
                    map[boxId] = map[boxId]!!.filter { it.first!=name }
                }else{
                    val box = map[boxId]!!.toMutableList()
                    if (box.none { it.first == name }){
                        map[boxId] = box+processed.second
                    }else{
                        val index = box.indexOfFirst { it.first==name }
                        box[index] = processed.second
                        map[boxId] = box
                    }
                }
            }else{
                if (value!=-1){
                    map[boxId]= listOf(processed.second)
                }
            }

        }
        println(map)
        return map.map {list->
            var index = 0
            list.value.sumOf {
                index++
                (list.key+1)*it.second*index
            }
        }.sum()
    }
}

fun decodeSequence(sequence: String): Int {
    var current = 0
    sequence.forEach {
        current += it.code
        current *= 17
        current %= 256
    }
    return current
}

fun getBoxId(sequence: String): Pair<Int,Pair<String,Int>> {
    return if (sequence.contains("-")) {
        val split = sequence.split("-")
        decodeSequence(split.first()) to (split.first() to -1)
    } else {
        val split = sequence.split("=")
        decodeSequence(split.first()) to (split.first() to split[1].toInt())
    }
}