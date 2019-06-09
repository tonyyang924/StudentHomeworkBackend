package tony.studenthomework.server.utils

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import tony.studenthomework.server.model.Homeworks
import tony.studenthomework.server.model.Records
import tony.studenthomework.server.model.Students


object DatabaseFactory {

    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(Students, Homeworks, Records)
            RandomNumberGenerator.init()
            (1..10).forEach { nameSuffix ->
                Students.insert {
                    it[name] = "Student %s".format(nameSuffix)
                    it[number] = "%s".format(RandomNumberGenerator.nextInt(100))
                }
            }
            Homeworks.insert {
                it[title] = "Two Sum"
            }
            Homeworks.insert {
                it[title] = "Add Two Numbers"
            }
            Homeworks.insert {
                it[title] = "Longest Substring Without Repeating Characters"
            }
            Homeworks.insert {
                it[title] = "Median of Two Sorted Arrays"
            }
            Homeworks.insert {
                it[title] = "Longest Palindromic Substring"
            }
            Homeworks.insert {
                it[title] = "ZigZag Conversion"
            }
            Homeworks.insert {
                it[title] = "Reverse Integer"
            }
            Homeworks.insert {
                it[title] = "String to Integer (atoi)"
            }
            Homeworks.insert {
                it[title] = "Palindrome Number"
            }
            Homeworks.insert {
                it[title] = "Regular Expression Matching"
            }
            Records.insert {
                it[sid] = 1
                it[hid] = 1
                it[status] = 1
            }
            Records.insert {
                it[sid] = 1
                it[hid] = 3
                it[status] = 2
            }
            Records.insert {
                it[sid] = 1
                it[hid] = 5
                it[status] = 1
            }
            Records.insert {
                it[sid] = 2
                it[hid] = 3
                it[status] = 1
            }
            Records.insert {
                it[sid] = 1
                it[hid] = 4
                it[status] = 0
            }
            Records.insert {
                it[sid] = 1
                it[hid] = 5
                it[status] = 1
            }
            Records.insert {
                it[sid] = 1
                it[hid] = 6
                it[status] = 2
            }
            Records.insert {
                it[sid] = 10
                it[hid] = 1
                it[status] = 2
            }
            Records.insert {
                it[sid] = 10
                it[hid] = 2
                it[status] = 2
            }
            Records.insert {
                it[sid] = 10
                it[hid] = 3
                it[status] = 1
            }
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(
        block: () -> T
    ): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }
}