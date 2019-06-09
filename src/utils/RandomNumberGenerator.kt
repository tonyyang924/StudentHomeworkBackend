package tony.studenthomework.server.utils

import kotlin.random.Random

object RandomNumberGenerator {

    private val randomNumberTempArrayList = arrayListOf<Int>()

    fun init() {
        randomNumberTempArrayList.clear()
    }

    fun nextInt(until: Int): Int {
        var duplicated: Boolean
        var number: Int
        do {
            number = Random.nextInt(until).also { randomNumber ->
                duplicated = randomNumberTempArrayList.contains(randomNumber)
                if (!duplicated) {
                    randomNumberTempArrayList.add(randomNumber)
                }
            }
        } while (duplicated)
        return number
    }
}