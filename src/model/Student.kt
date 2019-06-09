package tony.studenthomework.server.model

import org.jetbrains.exposed.sql.Table

object Students : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val number = varchar("number", 50)
    val name = varchar("name", 50)
}

data class Student(
    val id: Int,
    val number: String,
    val name: String
)