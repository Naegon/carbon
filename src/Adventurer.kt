import Adventurer.Movement.*
import Adventurer.Orientation.*

class Adventurer(data: List<String>) {
    private val name: String
    var posX: Int
    var posY: Int
    private var orientation: Orientation?
    private val movement: ArrayList<Movement?> = ArrayList()
    private var treasures: Int = 0

    init {
        // {A as "Aventurier"} - {Adventurer name} - {position in X} - {position in Y} - {Orientation} - {Movements}
        // if (data.size != 6)  // TODO: Throw error

        name = data[1]
        posX = data[2].toInt()
        posY = data[3].toInt()

        orientation = when(data[4]) {
            "N" -> Nord
            "S" -> Sud
            "O" -> Ouest
            "E" -> Est
            else -> null // TODO: Error - Unsuported identifier
        }

        data[5].forEach {
            movement.add(when(it.toString()) {
                "A" -> Avancer
                "G" -> TournerGauche
                "D" -> TournerDroite
                else -> null // TODO: Error - Unsuported identifier
            })
        }
    }

    enum class Movement {
        Avancer, TournerGauche, TournerDroite
    }

    enum class Orientation {
        Nord, Sud, Ouest, Est
    }

    override fun toString(): String {
        return "$name:\n" +
                "  - Current position: ($posX; $posY)\n" +
                "  - Orientation: $orientation\n" +
                "  - Movements: $movement\n" +
                "  - Number of treasure(s): $treasures"
    }

    override fun equals(other: Any?): Boolean =
        (other is Adventurer)
                && posX == other.posX
                && posY == other.posY
                && orientation == other.orientation
                && movement == other.movement
                && treasures == other.treasures

    fun getDisplayedName(): String {
        return "A(${
            if (name.length <= 5) name
            else name.take(4) + "…"
        })"
    }

    fun getNextMove(): Movement? {
        return if (movement.isNotEmpty()) movement[0] else null
    }

    fun applyNextMove(map: Array<Array<String>>) {
        when(getNextMove()) {
            Avancer -> forward(map)
            TournerGauche -> left()
            TournerDroite -> right()
            else -> { } // TODO: Error
        }
        movement.removeFirst()
    }

    fun forward(map: Array<Array<String>>) {
        val target: IntArray = intArrayOf(posX, posY)
        when(orientation) {
            Est -> target[0] += 1
            Ouest -> target[0] -= 1
            Nord -> target[1] -= 1
            Sud -> target[1] += 1
            null -> { } // TODO: Error
        }

        if (map.size <= target[1] || map[0].size <= target[0] || map[target[1]][target[0]] == "M") { // TODO: blocked by another adventurer
            return
        }


        when(orientation) {
            Est -> posX += 1
            Ouest -> posX -= 1
            Nord -> posY -= 1
            Sud -> posY += 1
            null -> { } // TODO: Error
        }

        val targetData = map[target[1]][target[0]]
        if (targetData.first().toString() == "T") {
            treasures += 1
            map[target[1]][target[0]] = if (targetData[2].toString().toInt() > 1) "T(${targetData[2].toString().toInt() - 1})" else "."
        }
    }

    fun left() {
        orientation = when(orientation) {
            Est -> Nord
            Nord -> Ouest
            Ouest -> Sud
            Sud -> Est
            null -> null // TODO: Error
        }
    }

    fun right() {
        orientation = when(orientation) {
            Est -> Sud
            Nord -> Est
            Ouest -> Nord
            Sud -> Ouest
            null -> null // TODO: Error
        }
    }

}
