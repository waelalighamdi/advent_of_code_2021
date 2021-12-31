import java.io.File

const val DAYS = 256
const val TIMER_RESET = 6
const val NEW_BORN_DAY = 8

fun part1(input: List<Int>): Int {
    val days = input as MutableList<Int>

    println("**** Init. Day 0: $days")
    for (theDay in 1..DAYS) {
        val spawnSize = days.size
        for (fishTimer in 0 until spawnSize) {
            days[fishTimer] -= 1
            if (days[fishTimer] < 0) {
                days[fishTimer] = TIMER_RESET
                days.add(NEW_BORN_DAY)
            }
        }
        println("**** After Day $theDay")
    }

    return days.size
}

fun part2(input: List<Int>): Long {
    val spawnFish = input.groupingBy { it }.eachCount().mapValues { it.value.toLong()}.toMutableMap()
    println(spawnFish)
    // Initial state
    for (key in 0..NEW_BORN_DAY) {
        if (!spawnFish.keys.contains(key)) spawnFish[key] = 0
    }

    // Simulate lantern fish produce new generation
    for (theDay in 1..DAYS) {
        val currentFishCountOfZeroDay = spawnFish[0]!!
        for (day in 1.. NEW_BORN_DAY) {
            spawnFish[day-1] = spawnFish[day]!!
        }
        // Day 0 case
        spawnFish[TIMER_RESET] = spawnFish[TIMER_RESET]!! + currentFishCountOfZeroDay
        spawnFish[NEW_BORN_DAY] = currentFishCountOfZeroDay
    }

    print(spawnFish.values)
    println()
    return spawnFish.values.sum()
}

fun main() {
    println("Advent of Code - Day6 - Lantern fish")

    val basePath = "src/main/kotlin/"
    val fileName = "input.txt"
    val input = File(basePath, fileName).readLines().first().split(",").map { it.toInt() }

    println(input)
    println("Number of Lantern Fish: ${input.size}")

//    println("Part1 ....")
//    val lanternFishCountPart1 = part1(input)
//    println("Number of Lantern Fish: $lanternFishCountPart1")

    println("Part2 ....")
    val lanternFishCountPart2 = part2(input)
    println("Number of Lantern Fish: $lanternFishCountPart2")

}