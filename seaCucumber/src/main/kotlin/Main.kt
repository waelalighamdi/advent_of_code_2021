import java.io.File

data class SeaCucumberMap(
    var matrix: MutableList<CharArray>,
    var noMovement: Boolean
)

fun moveEastForward(matrix: MutableList<CharArray>): SeaCucumberMap {
    val matrixRowSize = matrix.size
    val matrixColumnSize = matrix.first().size

    val newMatrix = matrix.map { it.copyOf() }.toMutableList()

    var noMovement = true

    for (row in 0 until matrixRowSize) {
        for (col in 0 until matrixColumnSize) {
            if (matrix[row][col] == '>') {
                if (col + 1 < matrixColumnSize) {
                    if (matrix[row][col + 1] == '.') {
                        newMatrix[row][col] = '.'
                        newMatrix[row][col + 1] = '>'
                        noMovement = false
                    }
                } else {
                    if (matrix[row][0] == '.') {
                        newMatrix[row][col] = '.'
                        newMatrix[row][0] = '>'
                        noMovement = false
                    }
                }
            }
        }
    }
    return SeaCucumberMap(newMatrix, noMovement = noMovement)
}

fun moveSouthForward(matrix: MutableList<CharArray>): SeaCucumberMap {
    val matrixRowSize = matrix.size
    val matrixColumnSize = matrix.first().size

    val newMatrix = matrix.map { it.copyOf() }.toMutableList()

    var noMovement = true

    for (row in 0 until matrixRowSize) {
        for (col in 0 until matrixColumnSize) {
            if (matrix[row][col] == 'v') {
                if (row + 1 < matrixRowSize) {
                    if (matrix[row + 1][col] == '.') {
                        newMatrix[row][col] = '.'
                        newMatrix[row + 1][col] = 'v'
                        noMovement = false
                    }
                } else {
                    if (matrix[0][col] == '.') {
                        newMatrix[row][col] = '.'
                        newMatrix[0][col] = 'v'
                        noMovement = false
                    }
                }
            }
        }
    }
    return SeaCucumberMap(newMatrix, noMovement = noMovement)
}

fun part1(input: List<CharArray>): Int {
    val seaCucumberMatrix = input.toMutableList()

    var step = 0
    var matrix = SeaCucumberMap(matrix = seaCucumberMatrix, noMovement = true)
    var noMovement = false

    while ((step < 1000) && !noMovement) { // in the test the program should finish at step 58
        matrix = moveEastForward(matrix.matrix)
        noMovement = matrix.noMovement
        matrix = moveSouthForward(matrix.matrix)
        if (noMovement) noMovement = matrix.noMovement
        step++
        println("After $step steps: ")
        matrix.matrix.forEach { line ->
            println(line)
        }
    }
    return step
}

fun main() {
    println("Advent of Code 2021 - Day 25 - Sea Cucumber ")

    val basePath = "src/main/kotlin/"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines().map { it.toCharArray() }

    println("Part1: ")

    println("Initial state:")
    input.forEach { line ->
        println(line)
    }
    part1(input)
}