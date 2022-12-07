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

        var location = mutableListOf<String>()

        var files = mutableMapOf<String,Int>()

        inputLines.forEach {

            if (it.startsWith('$')){
                val new = it.drop(2)
                val s = new.split(" ")

                val command = s[0]
                if (command=="cd"){
                    val arg = s[1]

                    if (arg == "/"){
                        location= mutableListOf("/")
                    }else{
                        if (arg==".."){
                            location = location.dropLast(1).toMutableList()

                        }else{
                            var buildName = "/"
                            location.drop(1).forEach {
                                buildName+= "$it/"
                            }
                        location.add("$buildName$arg/")
                        }
                    }
                }
            }else{
                val split = it.split(' ')
                val bytes = split[0].toIntOrNull()
                if (location.isEmpty()){
                    if (bytes!=null){
                    files.put("/", bytes!!)
                    }else{
                        files.put("/", 0)
                    }
                }else{
                val dirs = location

                if (bytes!=null){
                    dirs.forEach {dir->
                    if (files.contains(dir)){
                        val dr = files[dir]
                        files[dir] = dr!!+bytes
                    }else{
                        files[dir] = bytes
                    }}

                }}
            }
        }

        println(files)
        return files.filter { it.value<=100000 }.values.sum()
    }

    fun part2(): Any {
        val totalSpace = 70000000
        val neededSpace  =30000000
        var location = mutableListOf<String>()

        var files = mutableMapOf<String,Int>()

        inputLines.forEach {

            if (it.startsWith('$')){
                val new = it.drop(2)
                val s = new.split(" ")

                val command = s[0]
                if (command=="cd"){
                    val arg = s[1]

                    if (arg == "/"){
                        location= mutableListOf("/")
                    }else{
                        if (arg==".."){
                            location = location.dropLast(1).toMutableList()

                        }else{
                            var buildName = "/"
                            location.drop(1).forEach {
                                buildName+= "$it/"
                            }
                            location.add("$buildName$arg/")
                        }
                    }
                }
            }else{
                val split = it.split(' ')
                val bytes = split[0].toIntOrNull()
                if (location.isEmpty()){
                    if (bytes!=null){
                        files.put("/", bytes!!)
                    }else{
                        files.put("/", 0)
                    }
                }else{
                    val dirs = location

                    if (bytes!=null){
                        dirs.forEach {dir->
                            if (files.contains(dir)){
                                val dr = files[dir]
                                files[dir] = dr!!+bytes
                            }else{
                                files[dir] = bytes
                            }}

                    }}
            }
        }
        val spaceFilled= files["/"]!!


        val spaceLeft = totalSpace - spaceFilled
        val spaceNeeded = neededSpace-spaceLeft
        println("$spaceNeeded is needed")
        return files.filter { it.value>=spaceNeeded}.values.reversed().first()

    }
}
