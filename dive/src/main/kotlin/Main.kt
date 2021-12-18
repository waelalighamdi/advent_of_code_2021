import java.io.File


fun part1(commands: List<Command>): Int {
    var horizontalPosition = 0
    var depthPosition = 0

    for ((direction, position) in commands) {
        when(direction) {
            "forward" -> horizontalPosition += position
            "down" -> depthPosition += position
            "up" -> depthPosition -= position
        }
    }
    return horizontalPosition * depthPosition
}

fun part2(commands: List<Command>): Int {
    var horizontalPosition = 0
    var depthPosition = 0
    var aim = 0

    for ((direction, position) in commands) {
        when(direction) {
            "forward" -> {
                horizontalPosition += position
                depthPosition += aim * position
            }
            "down" -> aim += position
            "up" -> aim -= position
        }
    }
    return horizontalPosition * depthPosition
}

data class Command(
    val direction: String,
    val position: Int
)

fun main(args: Array<String>) {
    println("Advent of Code - Day 2 - Dive")

    val basePath = "src/main/kotlin/"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines()
//    println(input)

    val commands = input.map {
        it.split(" ")
    }.map {
        Command(it.first(), it.last().toInt())
    }
//    println(commands)

    println("Part1.....")
    val part1FinalPosition = part1(commands)
    println("Part1 - Final Position: $part1FinalPosition")

    println("Part2.....")
    val part2FinalPosition = part2(commands)
    println("Part2 - Final Position: $part2FinalPosition")
}