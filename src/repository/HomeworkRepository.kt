package tony.studenthomework.server.repository

import org.jetbrains.exposed.sql.*
import tony.studenthomework.server.model.Homework
import tony.studenthomework.server.model.Homeworks
import tony.studenthomework.server.utils.DatabaseFactory.dbQuery


object HomeworkRepository {

    suspend fun getHomeworks(): List<Homework> = dbQuery {
        Homeworks.selectAll().map {
            toHomework(it)
        }
    }

    suspend fun getHomework(id: Int): Homework? = dbQuery {
        Homeworks.select {
            (Homeworks.id eq id)
        }.mapNotNull {
            toHomework(it)
        }.singleOrNull()
    }

    suspend fun updateHomework(homework: Homework): Homework? {
        val existingHomework = dbQuery {
            Homeworks.select {
                // query existing entity by id
                // or query existing entity by title prevent duplicated title
                (Homeworks.id eq homework.id) or (Homeworks.title eq homework.title)
            }.mapNotNull {
                toHomework(it)
            }.singleOrNull()
        }
        return if (existingHomework == null) {
            addHomework(homework)
        } else {
            if (existingHomework.title == homework.title) {
                existingHomework
            } else {
                getHomework(dbQuery {
                    Homeworks.update({ (Homeworks.id eq homework.id) }) {
                        it[title] = homework.title
                    }
                })
            }
        }
    }

    private suspend fun addHomework(homework: Homework): Homework? =
        getHomework(dbQuery {
            Homeworks.insert {
                it[title] = homework.title
            } get Homeworks.id
        })

    private fun toHomework(row: ResultRow): Homework =
        Homework(
            id = row[Homeworks.id],
            title = row[Homeworks.title]
        )
}