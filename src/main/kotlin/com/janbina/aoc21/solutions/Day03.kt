package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input

fun main() {
    val input = Input.getDayInputLines(3)
    Day03(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day03(
    private val inputLines: List<String>
) {

    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplitted by lazy {
        inputLines.map {
            it.split(",")
        }
    }

    fun part1(): Any {
         var sum = 0
        var sameChars = ""
        val startTime = System.currentTimeMillis()
        inputLines.forEach { str ->
            val len = str.length/2
            val first = str.take(len)
            sameChars=""

            val second = str.drop(len)
//            println("${second.length}, ${first.length}")
            first.forEach {
                if(second.contains(it)){
                    sameChars+=it
//                    println("second $second contains $it")
                }
            }
            if (sameChars!=""){
//                val points = mapOf('a' to 1,'b' to 2,'c' to 3, 'd' to 4, 'e' to 5,'f' to 6,'g' to 7, 'h' to 8,'i' to 9,'j' to 10,'k' to 11,'l' to 12,'m' to 13, 'n' to 14,'o' to 15, 'p' to 16,'q' to 17,'r' to 18, 's' to 19, 't' to 20, 'u' to 21,'v' to 22,'w' to 23,'x' to 24,'y' to 25,'z' to 26)
                val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
//            sameChars.forEach {
                sum += alphabet.indexOf(sameChars.first())+1
//            }
            }


        }


        println(System.currentTimeMillis()-startTime)

        return sum
    }

    fun part2(): Any {
        val startTime = System.currentTimeMillis()

        val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var sum = 0
        var three = mutableListOf<String>()
        var toTranslate = mutableListOf<Char>()
        inputLines.forEach {
            three.add(it)
            if(three.lastIndex==2){
                val sameChars = mutableMapOf<Char,Int>()
                val first = three[0]
                    val second = three[1]
                        val third = three[2]

                    first.forEach {
                        if (second.contains(it)){
                            if (third.contains(it)){
                                if(sameChars.contains(it)){
                                sameChars.set(it,sameChars[it]!!+1)
                                }else{
                                    sameChars.set(it,1)
                                }
                            }
                        }

                }
                val mostRepeated = sameChars.maxByOrNull { it.value }!!
                toTranslate.add(mostRepeated.key)
                three = mutableListOf()
            }
        }
        toTranslate.forEach{
            sum+=alphabet.indexOf(it)+1
        }
        println("Part 2 in ${System.currentTimeMillis()-startTime} milliseconds")

        return sum
    }
}
