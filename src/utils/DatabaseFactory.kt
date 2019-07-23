package tony.studenthomework.server.utils

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ajbrown.namemachine.NameGenerator
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import tony.studenthomework.server.model.Homeworks
import tony.studenthomework.server.model.Records
import tony.studenthomework.server.model.Students
import com.github.kkuegler.PermutationBasedHumanReadableIdGenerator
import org.slf4j.LoggerFactory


object DatabaseFactory {

    private val logger = LoggerFactory.getLogger(DatabaseFactory::class.java)

    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(Students, Homeworks, Records)
            val titles = arrayListOf(
                "Two Sum",
                "Add Two Numbers",
                "Longest Substring Without Repeating Characters",
                "Median of Two Sorted Arrays",
                "Longest Palindromic Substring",
                "ZigZag Conversion",
                "Reverse Integer",
                "String to Integer (atoi)",
                "Palindrome Number",
                "Regular Expression Matching"
            )

            titles.forEach { title ->
                Homeworks.insert {
                    it[Homeworks.title] = title
                }
            }

            val idGen = PermutationBasedHumanReadableIdGenerator()
            val generator = NameGenerator()
            val names = generator.generateNames(100)
            logger.info("Generate %d names".format(names.size))

            names.forEach { name ->
                Students.insert {
                    it[Students.name] = "%s %s".format(name.firstName, name.lastName)
                    it[number] = "%s".format(idGen.generate())
                }
            }

            val titlesLen = titles.size
            var increaseId = 0
            names.forEach { name ->
                increaseId++
                val randomRecordCnt = (0..titlesLen).shuffled().first()
                logger.info("[%s %s] randomRecordCnt: %d".format(name.firstName, name.lastName, randomRecordCnt))
                RandomNumberGenerator.reset()
                RandomNumberGenerator.generateNoDuplicatedNumberList(randomRecordCnt, 1, titlesLen + 1).forEach { hid ->
                    Records.insert {
                        it[sid] = increaseId
                        it[Records.hid] = hid
                        it[status] = (0..2).shuffled().first()
                    }
                }
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