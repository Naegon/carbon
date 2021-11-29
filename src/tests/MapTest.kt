package tests

import Map
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MapTest {
    @Test
    fun testMapWithValidFile() {
        // Given
        val mapData = "C - 3 - 4\n" +
                "M - 1 - 1\n" +
                "M - 2 - 2\n" +
                "T - 0 - 3 - 2\n" +
                "T - 1 - 3 - 1\n" +
                "A - Indiana - 1 - 1 - S - AADADA"

        // When
        val resultMap = Map(mapData)

        // Then
        val expectedMap = "\u001B[32m.\t\t\u001B[0m\u001B[32m.\t\t\u001B[0m\u001B[32m.\t\t\u001B[0m\n" +
                "\u001B[32m.\t\t\u001B[0m\u001B[31mA(Indi…)\u001B[0m\u001B[32m.\t\t\u001B[0m\n" +
                "\u001B[32m.\t\t\u001B[0m\u001B[32m.\t\t\u001B[0m\u001B[34mM\t\t\u001B[0m\n" +
                "\u001B[33mT(2)\t\u001B[0m\u001B[33mT(1)\t\u001B[0m\u001B[32m.\t\t\u001B[0m\n" +
                "\n" +
                "Indiana:\n" +
                "  - Current position: (1; 1)\n" +
                "  - Orientation: Sud\n" +
                "  - Movements: [Avancer, Avancer, TournerDroite, Avancer, TournerDroite, Avancer]\n" +
                "  - Number of treasure(s): 0\n\n"

        assertEquals(expectedMap, resultMap.toString())
    }

}
