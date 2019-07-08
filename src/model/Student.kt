package tony.studenthomework.server.model

import org.jetbrains.exposed.sql.Table

object Students : Table() {
    val id = integer("id").primaryKey(0).autoIncrement()
    val number = varchar("number", 50).primaryKey(1)
    val name = varchar("name", 50)
}

data class Student(
    val id: Int,
    val number: String,
    val name: String
)

data class StudentDetail(
    val id: Int,
    val number: String,
    val name: String,
    val recordedHomework: List<RecordedHomework>
)

data class RecordedHomework(
    val homework: Homework,
    val status: RecordStatus
)