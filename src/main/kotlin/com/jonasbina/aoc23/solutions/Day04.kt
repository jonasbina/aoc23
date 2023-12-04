package com.jonasbina.aoc23.solutions

import com.janbina.aoc20.utils.Input
import java.sql.Array
import java.util.*
import kotlin.math.min

fun main() {
    val input = Input.getDayInputLines(4)
    Day04(input).also {
        println(it.part1())
        println(it.part22())
    }
}

class Day04(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(":")[1]
        }
    }

    fun part1(): Any {
        var sum = 0
        inputLinesSplit.forEach {numbers->
            val numbersSplit = numbers.split("|")
            val winning = numbersSplit[0].split(" ").filter { it.isNotEmpty() }
            val picked = numbersSplit[1].split(" ").filter { it.isNotEmpty() }
            var points = 0
            winning.forEach {
                if (picked.contains(it)){
                    if (points==0){
                        points=1
                    }else{
                        points*=2
                    }
                }
            }
            sum+=points
        }
        return sum
    }

    fun part2(): Any {
        var scratchCards = inputLines as MutableList
        var i = 0
        while (i<scratchCards.size){
            val numbersSplit = scratchCards[i].split(':')[1].split('|')
            val winning = numbersSplit[0].split(" ").filter { it.isNotEmpty() }
            val picked = numbersSplit[1].split(" ").filter { it.isNotEmpty() }
            var points = 0
            winning.forEach {
                if (picked.contains(it)){
                    points++
                }
            }
            val toAdd = mutableListOf<Pair<String, Int>>()
            for (ind in 1 .. min(points, scratchCards.size-i-1)){
                toAdd.add(Pair(scratchCards[i+ind], i+ind+1))
            }
            println(scratchCards.map { it.split(':')[0]})
            val newScratchCards = mutableListOf<String>()
            newScratchCards.addAll(scratchCards)
            scratchCards.forEachIndexed {index,string->
                val card = string.split(":")[0]
                val includingCard = toAdd.filter { it.first.contains(card) }
                if (includingCard.isNotEmpty()){
                    newScratchCards.add(index+1, includingCard[0].first)
                }
            }
            scratchCards = newScratchCards
            i++
        }
        return scratchCards.size
    }
    fun part22(): Any {
        val cards = inputLinesSplit.map { numbers ->
            val numbersSplit = numbers.split("|")
            val winning = numbersSplit[0].split(" ").filter { it.isNotEmpty() }
            val picked = numbersSplit[1].split(" ").filter { it.isNotEmpty() }
            winning to picked
        }
        val cardsCount = IntArray(cards.size)

        for (i in cards.indices) {
            cardsCount[i] = 1
        }
        for (i in cards.indices) {
            var matches = 0
            for (number in cards[i].second) {
                if (number in cards[i].first) {
                    matches++
                }
            }
            for (j in 1..matches) {
                if (i + j < cards.size) {
                    cardsCount[i+j] += cardsCount[i]
                }
            }
        }
        var totalCards = 0
        for (count in cardsCount) {
            totalCards += count
        }

        return totalCards==5744979
    }


}
