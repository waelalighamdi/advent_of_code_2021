import java.io.File

fun part1(input: List<Int>): Int {
    val depths = input.windowed(2)
    return depths.count { it.first() < it.last() }
}

fun part2(input: List<Int>): Int {
    val depths = input.windowed(4)
    return depths.count { it.first() < it.last() }
}

fun main(args: Array<String>) {
    println("Advent of Code - Day1 - Sonar Sweep")
    val basePath = "src/main/kotlin/"
    val inputFile = "input.txt"

    val input = File(basePath, inputFile).readLines().map { it.toInt() }
    println(input)

    println("Part1....")
    val depthIncreaseTimesPart1 = part1(input)
    println("Part1: $depthIncreaseTimesPart1")

    println("Part2....")
    val depthIncreaseTimesPart2 = part2(input)
    println("Part1: $depthIncreaseTimesPart2")

}