import java.io.File
import kotlin.math.absoluteValue

// https://rosettacode.org/wiki/Averages/Median#Kotlin
fun median(l: List<Int>) = l.sorted().let {
    (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
}

fun part1(horizontalPositions: List<Int>): Int {
    val leastFuelCost = median(horizontalPositions)
    println(leastFuelCost)

    val fuelCost = horizontalPositions.sumOf { position ->
        (position - leastFuelCost).absoluteValue
    }
    return fuelCost
}

// how to sum 1+2+3+4 till 100
// https://betterexplained.com/articles/techniques-for-adding-the-numbers-1-to-100/
fun sumOfSteps(steps: Int): Int {
    return (steps * (steps + 1) / 2)
}

fun part2(horizontalPositions: List<Int>): Int {
    var leastFuelCost: Int? = null
    val min = horizontalPositions.minOrNull() ?: throw IllegalStateException("No min")
    val max = horizontalPositions.maxOrNull()

    for (position in min..max!!) {
        val sumOfStepsToPosition = horizontalPositions.sumOf { crabPosition ->
            val steps = (crabPosition - position).absoluteValue
            sumOfSteps(steps)
        }

        if (leastFuelCost == null) leastFuelCost = sumOfStepsToPosition
        else if (sumOfStepsToPosition < leastFuelCost) leastFuelCost = sumOfStepsToPosition
    }
    return leastFuelCost!!
}

fun main() {
    println("Advent of Code 2021 - Day7 - The Treachery of Whales")

    val basePath = "src/main/kotlin/"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines().first().split(",").map { it.toInt() }
    println(input)

    println("Part 1 - Fuel Cost: ${part1(input)}")

    println("Part 2 - Fuel Cost: ${part2(input)}")
}