package com.jonasbina.aoc23.solutions

import com.janbina.aoc20.utils.Input
import com.janbina.aoc20.utils.max


fun main() {
    val input = Input.getDayInputLines(16)
    val testInput = Input.getTestInputLines(16)
    Day16(input).also {
        println(it.part1())
        println(it.part2())
    }
}

class Day16(
    private val inputLines: List<String>
) {
    val map = mutableListOf<List<Space>>()

    fun part1(): Int {
        inputLines.forEach {
            map.add(
                it.map {
                    when (it) {
                        '-' -> Space.HorizontalSplitter
                        '|' -> Space.VerticalSplitter
                        '/' -> Space.LeaningRight
                        '\\' -> Space.LeaningLeft
                        '.' -> Space.Empty
                        else -> throw Exception("Invalid character $it")
                    }
                }
            )
        }
        return calculateBeam(Beam(-1, 0, heading = Direction.Right),map)
    }
    fun calculateBeam(beam: Beam, map: List<List<Space>>):Int{
        var beams = listOf(beam)
        val alreadyExplored = mutableListOf<Beam>()
        while (beams.isNotEmpty()) {
            beams = beams.map {
                it.move(map)
            }.flatten().filter { !alreadyExplored.contains(it) && areCoordinatesLegal(it.x, it.y, map) }
            alreadyExplored.addAll(beams)
        }
        return alreadyExplored.map { it.x to it.y }.toSet().size
    }

    fun part2(): Int {
        println("PART 2 INITIATING, PROBABLY GONNA TAKE A WHILE :D")
        var max = 0
        for (i in 0 until map.size){
            val beams = listOf(Beam(-1, i, heading = Direction.Right),Beam(map[0].size, i, heading = Direction.Left))
            val localMax = beams.map {
                calculateBeam(it,map)
            }.max()
            if (localMax>max){
                max = localMax
            }
        }
        for (i in 0 until map[0].size){
            val beams = listOf(Beam(i, -1, heading = Direction.Down),Beam(i, map.size, heading = Direction.Up))
            val localMax = beams.map {
                calculateBeam(it,map)
            }.max()
            if (localMax>max){
                max = localMax
            }
        }
        return max
    }
}

data class Beam(
    var x: Int = -1,
    var y: Int = 0,
    var heading: Direction
) {
    fun move(map: List<List<Space>>): List<Beam> {
        val nextX = x + heading.xVel
        val nextY = y + heading.yVel
        if (areCoordinatesLegal(nextX, nextY, map)) {
            val nextSpace = map[nextY][nextX]
            val nextReflections =
                if (heading == Direction.Down) nextSpace.upReflection else if (heading == Direction.Up) nextSpace.downReflection else if (heading == Direction.Left) nextSpace.rightReflection else nextSpace.leftReflection
            return nextReflections.map { Beam(nextX, nextY, it) }
        }else{
            return listOf()
        }
    }
}

enum class Direction(val xVel: Int, val yVel: Int) {
    Down(0, 1),
    Up(0, -1),
    Left(-1, 0),
    Right(1, 0);
}

//These directions are the directions the beam will reflect to when coming from said direction i will probably get lost in it either way but whatever
enum class Space(
    val leftReflection: List<Direction>,
    val rightReflection: List<Direction>,
    val downReflection: List<Direction>,
    val upReflection: List<Direction>
) {
    Empty(
        leftReflection = listOf(Direction.Right),
        rightReflection = listOf(Direction.Left),
        downReflection = listOf(Direction.Up),
        upReflection = listOf(Direction.Down)
    ),
    LeaningRight(
        leftReflection = listOf(Direction.Up),
        rightReflection = listOf(Direction.Down),
        downReflection = listOf(Direction.Right),
        upReflection = listOf(Direction.Left)
    ),
    LeaningLeft(
        leftReflection = listOf(Direction.Down),
        rightReflection = listOf(Direction.Up),
        downReflection = listOf(Direction.Left),
        upReflection = listOf(Direction.Right)
    ),
    HorizontalSplitter(
        leftReflection = listOf(Direction.Right),
        rightReflection = listOf(Direction.Left),
        downReflection = listOf(Direction.Left, Direction.Right),
        upReflection = listOf(Direction.Left, Direction.Right)
    ),
    VerticalSplitter(
        leftReflection = listOf(Direction.Up, Direction.Down),
        rightReflection = listOf(Direction.Up, Direction.Down),
        upReflection = listOf(Direction.Down),
        downReflection = listOf(Direction.Up)
    )
}

fun areCoordinatesLegal(x: Int, y: Int, map: List<List<Space>>): Boolean {
    return x >= 0 && y >= 0 && y < map.size && x < map[0].size
}


