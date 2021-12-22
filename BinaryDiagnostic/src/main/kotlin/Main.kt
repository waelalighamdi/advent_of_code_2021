import java.io.File

fun isEqual(list: List<String>, i : Int): Boolean {
    val groupedInput = list.map { it[i] }.groupBy { it }
    println("groupedInput: $groupedInput")
    return groupedInput.values.first().size == groupedInput.values.last().size
}

fun oxygenGeneratorRating(diagnosticReport: List<String>): Int {
    var oxygenList = diagnosticReport
    var i = 0

    while (oxygenList.size > 1) {
        var oxygenBit = oxygenList.map { it[i] }.groupBy { it }.maxByOrNull { it.value.size }?.key
        println(oxygenBit)
        //if (oxygenList.size == 2) oxygenBit = '1'
        if (isEqual(oxygenList, i)) oxygenBit = '1'
        oxygenList = oxygenList.filter { it[i] == oxygenBit }
        println(oxygenList)
        i += 1
    }
    return oxygenList.first().toInt(2)
}

fun co2ScrubberRating(diagnosticReport: List<String>): Int {
    var co2List = diagnosticReport
    var i = 0

    while (co2List.size > 1) {
        var co2Bit = co2List.map { it[i] }.groupBy { it }.minByOrNull { it.value.size }?.key
        println(co2Bit)
//        if (co2List.size == 2) co2Bit = '0'
        if (isEqual(co2List, i)) co2Bit = '0'
        co2List = co2List.filter { it[i] == co2Bit }
        println(co2List)
        i += 1
    }
    return co2List.first().toInt(2)
}

fun part2(diagnosticReport: List<String>): Int {
    // Oxygen Generator Rate
    println("**** Oxygen Generator Rate Calculation")
    val oxygenRate = oxygenGeneratorRating(diagnosticReport)
    println("Oxygen Rate: $oxygenRate")

    // CO2 Scrubber Rate
    println("**** CO2 Scrubber Rate Calculation")
    val co2Rate = co2ScrubberRating(diagnosticReport)
    println("CO2 Rate: $co2Rate")

    return oxygenRate * co2Rate
}

fun part1(diagnosticReport: List<String>): Int {
    var gamma = ""

    for (i in diagnosticReport.first().indices) {
        gamma += diagnosticReport.map { it[i] }.groupBy { it }.maxByOrNull { it.value.size }?.key
    }
    println(gamma.toInt(2))
    val epsilon = gamma.map { if (it == '0') '1' else '0' }.joinToString("")
    println(epsilon.toInt(2))
    return gamma.toInt(2) * epsilon.toInt(2)
}

fun main() {
    println("Advent of Code 2021 - Day3 - Binary Diagnostic")

    val basePath = "src/main/kotlin/"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines()
    println(input)

//    println("Part1....")
//    val powerConsumption = part1(input)
//    println("The Power Consumption: $powerConsumption")

    println("Part2....")
    val lifeSupportRating = part2(input)
    println("The Life Support rating: $lifeSupportRating")

}