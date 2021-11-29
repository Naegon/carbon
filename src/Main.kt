import java.io.BufferedReader
import java.io.File

fun main() {
    val bufferedReader: BufferedReader = File("src/maps/map_02.txt").bufferedReader()
    val mapData = bufferedReader.use { it.readText() }

    val map = Map(mapData)

    println(map)

    var movements = map.adventurers.filter { it.getNextMove() != null }
    do {
        movements.forEach { it.applyNextMove(map.map) }
        movements = map.adventurers.filter { it.getNextMove() != null }
    } while (movements.isNotEmpty())

    println("\n\n||=============== End of movements ===============||" +
            "\n\n$map")

}
