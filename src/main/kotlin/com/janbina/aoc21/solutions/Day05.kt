package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input

fun main() {
    val input = Input.getDayInputLines(5)
    Day05(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day05(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplitted by lazy {
        inputLines.map {
            it.split(",")
        }
    }


    fun part1(): Any {
        var stacks = mutableMapOf<Int, MutableList<String>>()
        inputLines.take(8).forEach {
            it.split(' ').forEachIndexed { index, s ->
                if (s.isNotBlank()&&s!="...") {
                    if (stacks.contains(index)) {
                        stacks[index]!!.add(s)
                    } else {
                        stacks[index] = mutableListOf(s)
                    }
                }


            }
        }
        stacks = stacks.toSortedMap()
        stacks.forEach { i, strings ->
            println("$i : $strings")
        }


        val commands = inputLines.drop(10)
        println(commands)
        commands.forEach { command ->
            val s = command.split(' ')
            val amount = s[1].toInt()
            val from = s[3].toInt() - 1
            val to = s[5].toInt() - 1
            println("amount $amount, from $from, to $to, command $command")
            var flist = stacks[from]!!
            var tlist = stacks[to]!!
            val removed = flist.take(amount)
            flist = flist.drop(amount).toMutableList()
            val newTlist = if (tlist.isNotEmpty()) tlist.reversed().toMutableList() else mutableListOf()
            removed.reversed().forEach {
                newTlist.add(it)
            }
            tlist = if (newTlist.isNotEmpty()) {
                newTlist.reversed().toMutableList()
            }else{
                mutableListOf()
            }
            stacks.set(from, flist)
            stacks.set(to, tlist)
        }

        var stringWithBrackets = ""
        stacks.forEach { i, strings ->
            if (strings.isNotEmpty()) {
                println("$i, $strings")
                stringWithBrackets += strings[0]
            }
        }
        var string = ""
        stringWithBrackets.forEach {
            if (it != ' ' && it != '[' && it != ']') {
                string += it
            }
        }

        return string
    }

    fun part2(): Any {

        return 0
    }
}
