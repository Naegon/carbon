import Adventurer.Orientation.*

class Adventurer(data: List<String>) {
    private val name: String
    var posX: Int
    var posY: Int
    private var orientation: Orientation?
    private val movement: ArrayList<Movement?> = ArrayList()
    private val treasures: Int = 0

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
                "A" -> Movement.Avancer
                "G" -> Movement.TournerGauche
                "D" -> Movement.TournerDroite
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

    fun getDisplayedName(): String {
        return "A(${
            if (name.length <= 5) name
            else name.take(4) + "…"
        })"
    }

    fun getNextMove(): Movement? {
        return movement[0]
    }

    override fun equals(other: Any?): Boolean =
        (other is Adventurer)
            && posX == other.posX
            && posY == other.posY
            && orientation == other.orientation
            && movement == other.movement
            && treasures == other.treasures

    fun forward() {
        when(orientation) {
            Est -> posX += 1
            Ouest -> posX -= 1
            Nord -> posY -= 1
            Sud -> posY += 1
            null -> { } // TODO: Error
        }
        movement.removeFirst()
    }

    fun left() {
        orientation = when(orientation) {
            Est -> Nord
            Nord -> Ouest
            Ouest -> Sud
            Sud -> Est
            null -> null // TODO: Error
        }
        movement.removeFirst()
    }

    fun right() {
        orientation = when(orientation) {
            Est -> Sud
            Nord -> Est
            Ouest -> Nord
            Sud -> Ouest
            null -> null // TODO: Error
        }
        movement.removeFirst()
    }

}
