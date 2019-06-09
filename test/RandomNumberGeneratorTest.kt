import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import utils.RandomNumberGenerator

class RandomNumberGeneratorTest {

    @Test
    fun `generate one thousand number not duplicated`() {
        RandomNumberGenerator.init()
        val count = 1000
        val array = arrayListOf<Int>()
        (1..count).forEach { _ ->
            array.add(RandomNumberGenerator.nextInt(count))
        }
        println(array.toString())
        assertTrue(array.groupingBy { it }.eachCount().filter { it.value > 1 }.isEmpty())
    }
}