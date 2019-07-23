package tony.studenthomework.server.utils

import kotlin.random.Random

object RandomNumberGenerator {

    private val tempNumberList = arrayListOf<Int>()

    fun reset() {
        tempNumberList.clear()
    }

    fun randomNumber(until: Int): Int {
        return randomNumber(0, until)
    }

    fun randomNumber(from: Int, until: Int): Int {
        var duplicated: Boolean
        var number: Int
        do {
            number = Random.nextInt(from, until).also { randomNumber ->
                duplicated = tempNumberList.contains(randomNumber)
                if (!duplicated) {
                    tempNumberList.add(randomNumber)
                }
            }
        } while (duplicated)
        return number
    }

    fun generateNoDuplicatedNumberList(count: Int, until: Int): List<Int> {
        return generateNoDuplicatedNumberList(count, 0, until)
    }

    fun generateNoDuplicatedNumberList(count: Int, from: Int, until: Int): List<Int> {
        return List(count) { randomNumber(from, until)}
    }
}