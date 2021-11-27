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
        val expectedMap = ".\t\t.\t\t.\t\t\n" +
                ".\t\tA(Indi…).\t\t\n" +
                ".\t\t.\t\tM\t\t\n" +
                "T(2)\tT(1)\t.\t\t\n\n" +
                "Indiana:\n" +
                "  - Current position: (1; 1)\n" +
                "  - Orientation: Sud\n" +
                "  - Movements: [Avancer, Avancer, TournerDroite, Avancer, TournerDroite, Avancer]\n" +
                "  - Number of treasure(s): 0"

        assertEquals(expectedMap, resultMap.toString())
    }

}
