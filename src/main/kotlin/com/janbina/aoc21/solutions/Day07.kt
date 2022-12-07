package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input

fun main() {
    val input = Input.getDayInputLines(7)
    Day07(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day07(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplitted by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
        var location = ""

        val files = mutableMapOf<String,Int>()

        inputLines.forEach {
            if (it.startsWith('$')){
                val new = it.drop(2)
                val s = new.split(" ")

                val command = s[0]
                if (command=="cd"){
                    val arg = s[1]

                    if (arg == "/"){
                        location=""
                    }else{
                        if (arg==".."){
                            var l = location.split('/').toMutableList()
                            l.drop(1)
                            l = l.reversed().drop(1).reversed().toMutableList()
                            var s = ""
                            l.drop(1).forEach {
                                println("$s, /$it}")
                                s+="/$it"
                            }
                            location=s

                        }else{
                        location+= "/$arg"
                        }
                    }
                }
            }else{
                val split = it.split(' ')
                val bytes = split[0].toIntOrNull()
                val s = location.split('/')
                val dir = s[s.lastIndex]
                if (bytes!=null){
                    if (files.contains(dir)){
                        val dr = files[dir]
                        files[dir] = dr!!+bytes
                    }else{
                        files[dir] = bytes
                    }
                }
            }
        }
        return files.filter { it.value<=100000 }.values.sum()
    }

    fun part2(): Any {

        return 0
    }
}
