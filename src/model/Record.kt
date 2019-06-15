package tony.studenthomework.server.model

import org.jetbrains.exposed.sql.Table

object Records : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val sid = integer("sid")
    val hid = integer("hid")
    val status = integer("status")
}

data class Record(
    val id: Int,
    val sid: Int,
    val hid: Int,
    val status: Int
)

data class RecordStatus(
    val id: Int,
    val status: String
)

enum class RecordStatusEnum(val status: Int) {
    NOT_YET(0),
    PROCESSING(1),
    DONE(2)
}