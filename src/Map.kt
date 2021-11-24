class Map (data: String) {
    private val width: Int
    private var height: Int
    private var map: Array<Array<String>>

    init {
        val instructions = data.split("\n")
        val mapData = instructions.filter { it.startsWith("C") }
        val processedMapData = mapData[0].split(" - ")

        // if (mapData.size != 1)  // TODO: Throw error

        width = processedMapData[1].toInt()
        height = processedMapData[2].toInt()
        map = Array(height) { Array(width) { "." } }

        instructions.forEach {
            val item = it.split(" - ")
            when (item[0]) {
                "C", "#" -> { } // Ignore commented line as well as already processed C line
                "M" -> {
                    map[item[2].toInt()][item[1].toInt()] = item[0]
                }
                "T" -> {
                    map[item[2].toInt()][item[1].toInt()] = item[0] + "(2)" // TODO: Number of treasure
                }
                "A" -> {
                    map[item[2].toInt()][item[1].toInt()] = item[0]
                }
                else -> println("Unsupported identifier") // TODO: Unsupported identifier
            }
        }
    }

    override fun toString(): String {
        return buildString {
            map.forEach { line ->
                line.forEach { this.append("$it\t" + if(it.length == 4) "" else "\t") }
                this.append("\n")
            }
        }
    }

}
