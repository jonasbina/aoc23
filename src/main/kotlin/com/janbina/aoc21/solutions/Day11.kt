package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input
import kotlin.math.floor
import kotlin.math.round

fun main() {
    val input = Input.getDayInputLines(11)
    Day11(input).also {
        println(it.part1())
        println(it.part2())
    }
}


class Day11(
    private val inputLines: List<String>
) {
    private var monkeys =  mutableListOf<Monkey>()
    private val inputInts by lazy { inputLines.map { it.toInt() } }
    private val inputLinesSplitted by lazy {
        inputLines.map {
            it.split(",")
        }
    }
    fun part1(): Any {

        monkeys.add(Monkey(mutableListOf(74, 73, 57, 77, 74),"* 11",19,6,7,0))
        monkeys.add(Monkey(mutableListOf(99, 77, 79),"+ 8",2,6,0,0))
        monkeys.add(Monkey(mutableListOf(64, 67, 50, 96, 89, 82, 82),"+ 1",3,5,3,0))
        monkeys.add(Monkey(mutableListOf(88),"* 7",17,5,4,0))
        monkeys.add(Monkey(mutableListOf(80, 66, 98, 83, 70, 63, 57, 66),"+ 4",13,0,1,0))
        monkeys.add(Monkey(mutableListOf(81, 93, 90, 61, 62, 64),"+ 7",7,1,4,0))
        monkeys.add(Monkey(mutableListOf(69, 97, 88, 93),"* old",5,7,2,0))
        monkeys.add(Monkey(mutableListOf(59, 80),"+ 6",11,2,3,0))

//        monkeys.add(Monkey(mutableListOf(79, 98),"* 19",23,2,3,0))
//        monkeys.add(Monkey(mutableListOf(54, 65, 75, 74),"+ 6",19,2,0,0))
//        monkeys.add(Monkey(mutableListOf(79, 60,97),"* old",13,1,3,0))
//        monkeys.add(Monkey(mutableListOf(74),"+ 3",17,0,1,0))


        for (round in 1..20){
            println("${round-1}")

            monkeys.forEach {
                println("items ${it.startingItems}")
                println("interactions ${it.interactions}")
                it.operationOnStartingItems(monkeys)
            }
            var s = "After round $round, monkeys are holding following items: \n"
            monkeys.forEach {
                s+=it.startingItems+"\n"
            }
            println(s)


        }
       val best = monkeys.sortedBy { it.interactions }.reversed().take(2)
       best.forEach {
           println(it.interactions)
       }
        return best[0].interactions*best[1].interactions
    }

    fun part2(): Any {

        return 0
    }

    class Monkey(
        var startingItems : MutableList<Int>,
        //Operation is in format like that: "* 6"
        var operation: String,
        var testDivisibleBy :Int,
        var indexOfTrueMonkey : Int,
        var indexFalseMonkey : Int,
        var interactions :Int

    ){

        fun whichMonkeyThrowTo(item:Int):Int{
            if (item%testDivisibleBy==0){
                return indexOfTrueMonkey
            }else{
                return indexFalseMonkey
            }
        }
        fun operationOnStartingItems(monkeys:List<Monkey>){
            val l = mutableListOf<Int>()
            val o = operation.split(' ')[0]
            val num = operation.split(' ')[1]

            startingItems.forEach {
                var sum = 0
                interactions++
                if (o=="*"){
                    if (num=="old"){
                        sum = it*it
                    }else {
                        sum = it * num.toInt()
                    }
                }
                if (o=="+"){
                    if (num=="old"){
                        sum = it+it
                    }else {
                        sum =it + num.toInt()
                    }
                }

                l.add(round(((sum/3).toDouble())).toInt())

            }
            startingItems=l.toList().toMutableList()


            l.forEachIndexed { index, i ->

                monkeys[whichMonkeyThrowTo(i)].startingItems.add(i)

                startingItems.removeAt(startingItems.indexOf(i))

            }



        }

    }
}

