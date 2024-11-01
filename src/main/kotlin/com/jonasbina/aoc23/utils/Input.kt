package com.janbina.aoc20.utils

import java.io.File

object Input {
    fun getDayInputText(day: Int): String {
        return getDayInputFile(day).readText()
    }

    fun getDayInputLines(day: Int): List<String> {
        return getDayInputFile(day).readLines()
    }
    fun getTestInputText(day: Int): String {
        return getTestInputFile(day).readText()
    }
    private fun getDayInputFile(day: Int): File {
        return File(javaClass.classLoader.getResource(getDayInputFileName(day)).toURI())
    }
    private fun getTestInputFile(day: Int): File {
        return File(javaClass.classLoader.getResource(getTestInputFileName(day)).toURI())
    }
    fun getTestInputLines(day: Int): List<String> {
        return getTestInputFile(day).readLines()
    }

    private fun getDayInputFileName(day: Int): String {
        return buildString {
            append("day")
            append(day.toString().padStart(2, '0'))
            append(".txt")
        }
    }
    private fun getTestInputFileName(day: Int): String {
        return buildString {
            append("test")
            append(day.toString().padStart(2, '0'))
            append(".txt")
        }
    }
}
