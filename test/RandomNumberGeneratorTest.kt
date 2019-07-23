import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import tony.studenthomework.server.utils.RandomNumberGenerator

class RandomNumberGeneratorTest {

    @Test
    fun `generate one thousand number not duplicated`() {
        val randomNumberList = RandomNumberGenerator.generateNoDuplicatedNumberList(1000, Int.MAX_VALUE)
        assertTrue(randomNumberList.groupingBy { it }.eachCount().filter { it.value > 1 }.isEmpty())
    }
}