import java.io.File

fun fillBingoBoards(boards: List<List<List<Int>>>): List<BingoBoard> {
    val bingoBoards = mutableListOf<BingoBoard>()
    for (board in boards) {
        val bingoBoard = BingoBoard()
        bingoBoard.fillBoard(board)
        bingoBoards.add(bingoBoard)
    }
    return bingoBoards
}

fun part1(bingoNumbers: List<Int>, bingoBoards: List<BingoBoard>): Int {
    for (number in bingoNumbers) {
        for (board in bingoBoards) {
            board.markValue(number)
            println("Marked Value: $number")
            if(board.isWin()) {
                println("we have a winner!!")
                val sum = board.sumOfUnmarkedValues()
                println("the sum of all unmarked values: $sum")
                return sum * number
            }
        }
    }
    println("There is no winner!")
    return 0
}

fun part2(bingoNumbers: List<Int>, bingoBoards: List<BingoBoard>): Int {
    val winningNumbers = mutableListOf<Int>()
    val winningBoards = mutableListOf<BingoBoard>()

    for (number in bingoNumbers) {
        for (board in bingoBoards) {
            if (!winningBoards.contains(board)) {
                board.markValue(number)
                println("Marked Value: $number")
                if (board.isWin()) {
                    println("we have a winner!!")
                    winningNumbers.add(number)
                    winningBoards.add(board)
                }
            }
        }
    }

    val sum = winningBoards.last().sumOfUnmarkedValues()
    println("the sum of all unmarked values of the last board: $sum")
    val number = winningNumbers.last()
    println("The last winning number: $number")
    return sum * number
}

fun main() {
    println("Advent of Code - Day 4 - Giant Squid")

    val basePath = "src/main/kotlin"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines()
    println(input)

    val inputNumbers = input.first().split(",").map { it.toInt() }
    println(inputNumbers)

    val inputBoards = input.drop(1).chunked(6).map { board ->
        board.filter { row ->
            row.isNotBlank()
        }.map { row ->
            row.trim().split("\\s+".toRegex()).map { it.toInt() }
        }
    }
    println(inputBoards)
    val bingoBoards = fillBingoBoards(inputBoards)
    println("the size of bingoBoards: ${bingoBoards.size}")

    println("Part1....")
    val part1BingoScore = part1(inputNumbers, bingoBoards)
    println("the Bingo Score of Part1: $part1BingoScore")

    println("Part2....")
    val part2BingoScore = part2(inputNumbers, bingoBoards)
    println("the Bingo Score of Part1: $part2BingoScore")
}