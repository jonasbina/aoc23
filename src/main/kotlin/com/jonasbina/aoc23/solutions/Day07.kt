package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import com.jonasbina.aoc23.solutions.Day01

fun main() {
    val input = Input.getDayInputLines(7)
    val testInput = Input.getTestInputLines(7)
    val test = true
    Day07(if (test) testInput else input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day07(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplit by lazy {
        inputLines.map {
            it.split(",")
        }
    }
    val cardToValue = mapOf(
        '2' to 2,
        '3' to 3,
        '4' to 4,
        '5' to 5,
        '6' to 6,
        '7' to 7,
        '8' to 8,
        '9' to 9,
        'T' to 10,
        'J' to 11,
        'Q' to 12,
        'K' to 13,
        'A' to 14
    )
    val cardToValuePart2 = mapOf(
        'J' to 1,
        '2' to 2,
        '3' to 3,
        '4' to 4,
        '5' to 5,
        '6' to 6,
        '7' to 7,
        '8' to 8,
        '9' to 9,
        'T' to 10,
        'Q' to 11,
        'K' to 12,
        'A' to 13
    )

    fun part1(): Any {

        val hands = inputLines.map { line ->
            val split = line.split(" ")
            val cards = split[0]
            val bid = split[1]
            Hand(cards.map { cardToValue[it]!! }, bid.toInt())
        }
        val handsWithCardCounts = mutableListOf<Map<Int, Int>>()

        for (hand in hands) {
            val cardCounts = mutableMapOf<Int, Int>()
            for (card in hand.cards) {
                cardCounts[card] = cardCounts.getOrDefault(card, 0) + 1
            }
            handsWithCardCounts.add(cardCounts)
        }
        val handsWithTypes = hands.mapIndexed { index, hand ->
            val handWithCardCounts = handsWithCardCounts[index]
            val sortedCounts = handWithCardCounts.values.sortedDescending()
            val firstCount = sortedCounts[0]

            val grade = when {
                firstCount == 5 -> 6
                firstCount == 4 -> 5
                firstCount == 3 && sortedCounts[1] == 2 -> 4
                firstCount == 3 -> 3
                firstCount == 2 && sortedCounts[1] == 2 -> 2
                firstCount == 2 -> 1
                else -> 0
            }
            Pair(hand, grade)
        }

        return handsWithTypes.sortedWith(
            compareBy(Pair<Hand, Int>::second)
                .thenBy { it.first.cards.first() }
                .thenBy { it.first.cards.getOrNull(1) }
                .thenBy { it.first.cards.getOrNull(2) }
                .thenBy { it.first.cards.getOrNull(3) }
                .thenBy { it.first.cards.getOrNull(4) }
        ).mapIndexed { index, pair ->
            pair.first.bid * (index + 1)
        }.sum()
    }

    fun part2(): Any {
        val hands = inputLines.map { line ->
            val type = getType(line.split(" ")[0], true)
            Pair(Pair(Pair(line, line.split(" ")[0].map { cardToValuePart2[it]!! }),type), type.strength)
        }
        return hands.sortedWith(
            compareBy(Pair<Pair<Pair<String, List<Int>>, Types>, Int>::second)
                .thenBy {it.first.first.second.first()}
                .thenBy {it.first.first.second[1]}
                .thenBy {it.first.first.second[2]}
                .thenBy {it.first.first.second[3]}
                .thenBy {it.first.first.second[4]}
        )
            .map { "${it.first.first.first} - ${it.second}" }.joinToString(",\n")
//            .mapIndexed { index, pair ->
//            (index+1)*pair.first.first.first.split(" ")[1].toInt()
//        }.sum()

    }



    fun getType(string: String, withJoker: Boolean): Types {
        var counts = string.groupingBy { it }.eachCount().toMutableMap()
        if (withJoker) {

            val jokerCount = counts['J'] ?: 0
            if (jokerCount==5){
                return Types.five_of_kind
            }
            val max = counts.maxByOrNull { it.value }
            counts[max!!.key] = counts[max.key]!! + jokerCount
        }
        return when {
            counts.any { it.value == 5 } -> Types.five_of_kind
            counts.any { it.value == 4 } -> Types.four_of_kind
            counts.any { it.value == 3 } && counts.any { it.value == 2 } -> Types.full_house
            counts.any { it.value == 3 } -> Types.three_of_kind
            counts.count { it.value == 2 } == 2 -> Types.two_pairs
            counts.any { it.value == 2 } -> Types.one_pair
            else -> Types.high_card
        }
    }

    enum class Types(val strength: Int) {
        five_of_kind(6),
        four_of_kind(5),
        full_house(4),
        three_of_kind(3),
        two_pairs(2),
        one_pair(1),
        high_card(0)
    }
}

data class Hand(
    val cards: List<Int>,
    val bid: Int
)