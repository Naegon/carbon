class Adventurer(data: List<String>) {
    private val name: String
    val posX: Int
    val posY: Int
    private val orientation: Orientation?
    private val movement: ArrayList<Movement?> = ArrayList()
    private val treasures: Int = 0

    init {
        // {A as "Aventurier"} - {Adventurer name} - {position in X} - {position in Y} - {Orientation} - {Movements}
        // if (data.size != 6)  // TODO: Throw error

        name = data[1]
        posX = data[2].toInt()
        posY = data[3].toInt()

        orientation = when(data[4]) {
            "N" -> Orientation.Nord
            "S" -> Orientation.Sud
            "O" -> Orientation.Ouest
            "E" -> Orientation.Est
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
}
