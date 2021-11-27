import java.io.BufferedReader
import java.io.File

fun main() {
    val bufferedReader: BufferedReader = File("src/maps/map_01.txt").bufferedReader()
    val mapData = bufferedReader.use { it.readText() }

    val map = Map(mapData)

    println(map)
}
