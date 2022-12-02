package com.janbina.aoc20.solutions

import com.janbina.aoc20.utils.Input

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
            it.split(",")
        }
    }

    fun part1(): Any {
        var score =0
        inputLines.forEach {
            val split = it.split(' ')
            val opponentMove = split[0]
            val playedMove =split[1]
            score+=didPlayerWin(opponentMove,playedMove)

        }
        return score
    }

    fun part2(): Any {
        var score =0
        inputLines.forEach {
            val split = it.split(' ')
            val opponentMove = split[0]
            val end =split[1]
            score+= whatMoveToUse(opponentMove,end)

        }
        return score
    }
}
fun didPlayerWin(opponentMove:String, playedMove :String):Int{
    var won =false
    var draw =false

    val pMove = if(playedMove=="X") "A" else if(playedMove=="Y") "B" else "C"
    if(opponentMove==pMove){
        draw = true
    }else{
        if(opponentMove=="A"&&pMove=="B"){
            won=true
        }
        if(opponentMove=="B"&&pMove=="C"){
            won=true
        }
        if (opponentMove=="C"&&pMove=="A"){
            won = true
        }
    }



    return calculateScore(won, draw, pMove)
}
fun whatMoveToUse(opponentMove: String,end:String):Int{
    val won = end =="Z"
    val draw = end=="Y"
    var shape = ""
    val loseMoves = mapOf(
        "A" to "C",
        "B" to "A",
        "C" to "B"
    )
    val winMoves = mapOf(
        "A" to "B",
        "B" to "C",
        "C" to "A"
    )
    if(won){
         shape = winMoves[opponentMove]!!
    }else{
        if(draw){
            shape = opponentMove
        }else{
            shape = loseMoves[opponentMove]!!
        }
    }
    return calculateScore(won,draw,shape)

}
fun calculateScore(won:Boolean,draw:Boolean,pMove:String):Int{
    var score = if (won) 6 else if (draw) 3 else 0

    val scoresForShapes = mapOf(
        "A" to 1,
        "B" to 2,
        "C" to 3
    )
    val additionalScore =scoresForShapes[pMove]!!


    score+= additionalScore
    return score
}

