class Map (data: String) {
    private val width: Int
    private var height: Int
    var map: Array<Array<String>>
    val adventurers: ArrayList<Adventurer> = ArrayList()

    init {
        val instructions = data.split("\n")
        val mapData = instructions.filter { it.startsWith("C") }
        val processedMapData = mapData[0].split(" - ")

        // if (mapData.size != 1)  // TODO: Throw error

        // {C as "carte"} - {width} - {height}
        // if (processedMapData.size != 3)  // TODO: Throw error
        width = processedMapData[1].toInt()
        height = processedMapData[2].toInt()
        map = Array(height) { Array(width) { "." } }

        instructions.forEach {
            val item = it.split(" - ")
            when (item[0]) {
                "C", "#" -> { } // Ignore commented line as well as already processed C line
                "M" -> {
                    // {M as "Montagne"} - {position in X} - {position in Y}
                    map[item[2].toInt()][item[1].toInt()] = "M"
                }
                "T" -> {
                    // {T as "Trésor"} - {position in X} - {position in Y} - {number of treasure}
                    map[item[2].toInt()][item[1].toInt()] = "T(${item[3]})"
                }
                "A" -> {
                    val adventurer = Adventurer(item)
                    adventurers.add(adventurer)
                    map[adventurer.posY][adventurer.posX] = adventurer.getDisplayedName()
                }
                else -> println("Unsupported identifier") // TODO: Unsupported identifier
            }
        }
    }

    override fun toString(): String {
        return buildString {
            map.forEach { line ->
                line.forEach {
                    this.append(it)
                    this.append("\t".repeat(kotlin.math.ceil((8 - it.length) / 4.0).toInt()))
                }
                this.append("\n")
            }
            this.append("\n")
            adventurers.forEach {
                this.append(it.toString())
            }
        }
    }

}
