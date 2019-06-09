package tony.studenthomework.server.model

import org.jetbrains.exposed.sql.Table

object Homeworks : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val title = varchar("title", 150)
}

data class Homework(
    val id: Int,
    val title: String
)