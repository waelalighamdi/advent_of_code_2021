class BingoBoard {
    data class Cell(val row:Int, val col:Int, val value:Int, var isMark: Boolean)
    data class Rows(val row: MutableList<Cell>)

    private val bingo = mutableListOf<Rows>()

    fun fillBoard(board: List<List<Int>>) {
        for ((indexRow, row) in board.withIndex()) {
            val bingoRow = mutableListOf<Cell>()
            for ((indexCol, cell) in row.withIndex()){
                bingoRow.add(Cell(indexRow,indexCol,cell, false))
            }
            bingo.add(Rows(bingoRow))
//            println(bingo[indexRow].row)
        }
    }

    fun markValue(value: Int) {
        bingo.forEach { rows ->
            rows.row.map { cell ->
                if (cell.value == value) {
                    cell.isMark = true
                    println("Marked: (${cell.row}, ${cell.col}, ${cell.value})")
                }
            }
        }
    }

    fun isWin(): Boolean {
        // row winning
        val rowWon = bingo.any { rows ->
            rows.row.all { it.isMark }
        }
        if (rowWon) return true

        // column winning
        for (col in 0 until bingo.first().row.size) {
            if ((0 until bingo.size).map { bingo[it].row[col] }.all {it.isMark}) {
                return true
            }
        }
        return false
    }

    fun sumOfUnmarkedValues(): Int {
        var sum = 0
        bingo.forEach { rows ->
            rows.row.filter { !it.isMark }.map { sum += it.value }
        }
        return sum
    }
}