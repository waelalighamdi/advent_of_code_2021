import java.io.File

data class Point(
    val x: Int,
    val y: Int
)

fun verticalLine(start: Point, end: Point): List<Point> {
    val verticalPoints = mutableListOf<Point>()
    val from = if (start.y <= end.y) start.y else end.y
    val to = if (start.y > end.y) start.y else end.y

    for (index in from..to) {
        verticalPoints.add(Point(start.x, index))
    }
    return verticalPoints
}

fun horizontalLine(start: Point, end: Point): List<Point> {
    val horizontalPoints = mutableListOf<Point>()
    val from = if (start.x <= end.x) start.x else end.x
    val to = if (start.x > end.x) start.x else end.x
    for (index in from..to) {
        horizontalPoints.add(Point(index, start.y))
    }
    return horizontalPoints
}

fun diagonalLine(start: Point, end: Point): List<Point> {
    val diagonalPoints = mutableListOf<Point>()
    var x = start.x
    var y = start.y
    diagonalPoints.add(start)

    while (x != end.x) {
        // direction  left else right
        if (start.x > end.x) x -= 1 else x += 1
        // direction ascent else descent
        if (start.y > end.y) y -= 1 else y += 1

        diagonalPoints.add(Point(x, y))
    }
    return diagonalPoints
}

fun countPoints(points: List<Point>, pointsCount: MutableMap<Point, Int>) {
    points.forEach { point ->
        if (pointsCount.containsKey(point)) {
            val count = pointsCount[point]?.plus(1)
            pointsCount[point] = count!!
        } else pointsCount[point] = 1
    }
}

fun solution(lines: List<List<List<Point>>>): Int {
    val pointsCount = mutableMapOf<Point, Int>()

    for (line in lines) {
        val startPoint = line.first().first()
        val endPoint = line.last().first()
//        println("Start Point: $startPoint, End Point: $endPoint")
        if (startPoint.x == endPoint.x) { // the line is vertical
            //println("the line: $line is vertical")
            val verticalLines = verticalLine(startPoint, endPoint)
            countPoints(verticalLines, pointsCount)
        } else if (startPoint.y == endPoint.y) { // the line is horizontal
            //println("the line: $line is horizontal")
            val horizontalLines = horizontalLine(startPoint, endPoint)
            countPoints(horizontalLines, pointsCount)
        } else { // the line is diagonal
            //println("the line: $line is diagonal")
            val diagonalLines = diagonalLine(startPoint, endPoint)
            countPoints(diagonalLines, pointsCount)
        }
        //println("-------")
    }
    return pointsCount.filterValues { pointCount ->
        pointCount >= 2
    }.count()
}
fun main() {
    println("Advent of Code 2021 - Day5 - Hydrothermal Venture")
    val basePath = "src/main/kotlin/"
    val inputFile = "input.txt"

    val ventsLines = File(basePath, inputFile).readLines().map { lines ->
        lines.split(" -> ").map { line ->
            line.split(", ").map { points ->
                val (x, y) = points.split(",")
                Point(x.toInt(), y.toInt())
            }
        }
    }

    println(ventsLines.size)
    println(ventsLines)

    println("Part2 solution: ")
    println(solution(ventsLines))
}