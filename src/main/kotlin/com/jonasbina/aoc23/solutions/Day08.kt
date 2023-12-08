package com.jonasbina.aoc23.solutions

import com.janbina.aoc20.utils.Input
import com.janbina.aoc20.utils.repeatIndefinitely
import kotlin.math.max

fun main() {
    val input = Input.getDayInputLines(8)
    val testInput = Input.getTestInputLines(8)
    val test = false
    Day08(if (test) testInput else input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day08(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(",")
        }
    }
    val movesToDo = inputLines[0].toList().map { if (it=='L') 0 else 1 }
    val rules = inputLines.drop(2).associate {
        val split = it.split(" = ")
        val from = split[0]
        val to = split[1].replace("(","").replace(")","").replace(" ", "").split(",")
        from to listOf(to[0], to[1])
    }
    fun part1(): Any {
        return getMoves("AAA", {it == "ZZZ"})
    }
    fun getMoves(start:String, end: (String) -> Boolean):Int{

        var current = start
        var moves = 0
        while (true){
            if (end(current)){
                break
            }
            val next = rules[current]!!.get(movesToDo[moves % movesToDo.size])
            current = next
            moves++
        }
        return moves
    }
    fun part2(): Any {
        val moves = mutableListOf<Int>()
        rules.keys.filter { it.endsWith('A') }.forEach {
            moves.add(getMoves(it, {it.endsWith('Z')}))
        }
        return findLCMOfList(moves)
    }
    fun findLCMOfList(numbers:List<Int>):Long{
        var lcm = numbers[0].toLong()
        for (i in 1 until numbers.size) {
            lcm = findLCM(lcm, numbers[i].toLong())
        }
        return lcm
    }
    fun findLCM(first:Long, second:Long):Long{
        val bigger = max(first, second)
        var lcm = bigger
        while (lcm<first*second){
            if (lcm%first==0L&&lcm%second==0L){
                return lcm
            }
            lcm+=bigger
        }
        return lcm
    }
}
