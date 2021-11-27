import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AdventurerTest {
    @Test
    fun testMapWithValidFile() {
        // Given
        val data = listOf("A", "Indiana", "1", "1", "S", "AADADA")

        // When
        val adventurer = Adventurer(data)

        // Then
        val expectedAdventurer = "Indiana:\n" +
                "  - Current position: (1; 1)\n" +
                "  - Orientation: Sud\n" +
                "  - Movements: [Avancer, Avancer, TournerDroite, Avancer, TournerDroite, Avancer]\n" +
                "  - Number of treasure(s): 0"

        assertEquals(expectedAdventurer, adventurer.toString())
    }

    @Test
    fun testGetDisplayedNameWithLongValue() {
        // Given
        val name = "Very_long_name"

        // When
        val adventurer = Adventurer(listOf("A", name, "1", "1", "S", "AADADA"))

        // Then
        assertEquals("A(Very…)", adventurer.getDisplayedName())
    }

    @Test
    fun testGetDisplayedNameWithShortValue() {
        // Given
        val name = "Bob"

        // When
        val adventurer = Adventurer(listOf("A", name, "1", "1", "S", "AADADA"))

        // Then
        assertEquals("A(Bob)", adventurer.getDisplayedName())
    }

}
