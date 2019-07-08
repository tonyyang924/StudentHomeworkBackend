package tony.studenthomework.server.repository

import org.jetbrains.exposed.sql.*
import tony.studenthomework.server.model.*
import tony.studenthomework.server.model.Students.name
import tony.studenthomework.server.model.Students.number
import tony.studenthomework.server.utils.DatabaseFactory.dbQuery


object StudentRepository {

    suspend fun getStudents(): List<Student> = dbQuery {
        Students.selectAll().map {
            toStudent(it)
        }
    }

    suspend fun getStudent(id: Int): Student? = dbQuery {
        Students.select {
            (Students.id eq id)
        }.mapNotNull {
            toStudent(it)
        }.singleOrNull()
    }

    suspend fun getStudentDetail(id: Int): StudentDetail? = dbQuery {
        Students.select {
            (Students.id eq id)
        }.mapNotNull {
            toStudentDetail(it, toRecordedHomeworkList(id))
        }.singleOrNull()
    }

    suspend fun updateStudent(student: Student): Student? {
        val existingStudent = dbQuery {
            Students.select {
                (Students.id eq student.id) or (number eq student.number)
            }.mapNotNull {
                toStudent(it)
            }.singleOrNull()
        }
        return if (existingStudent == null) {
            addStudent(student)
        } else {
            getStudent(dbQuery {
                Students.update({ (Students.id eq student.id) or (number eq student.number) }) {
                    it[number] = student.number
                    it[name] = student.name
                }
            })
        }
    }

    private suspend fun addStudent(student: Student): Student? =
        getStudent(dbQuery {
            Students.insert {
                it[number] = student.number
                it[name] = student.name
            } get Students.id
        })

    private fun toStudent(row: ResultRow): Student =
        Student(
            id = row[Students.id],
            number = row[number],
            name = row[name]
        )

    private fun toStudentDetail(row: ResultRow, recordedHomeworkList: List<RecordedHomework>): StudentDetail =
        StudentDetail(
            id = row[Students.id],
            number = row[number],
            name = row[name],
            recordedHomework = recordedHomeworkList
        )

    private fun toRecordedHomeworkList(sid: Int): List<RecordedHomework> =
        Records.join(Homeworks, JoinType.INNER, null, null) {
            (Records.hid eq Homeworks.id)
        }.select {
            (Records.sid eq sid)
        }.mapNotNull {
            toRecordedHomework(it)
        }

    private fun toRecordedHomework(row: ResultRow): RecordedHomework {
        val enum = RecordStatusEnum.values()[row[Records.status]]
        return RecordedHomework(
            homework = Homework(row[Homeworks.id], row[Homeworks.title]),
            status = RecordStatus(enum.ordinal, enum.name)
        )
    }
}