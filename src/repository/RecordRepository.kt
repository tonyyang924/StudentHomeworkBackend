package tony.studenthomework.server.repository

import org.jetbrains.exposed.sql.*
import tony.studenthomework.server.model.RecordStatusEnum
import tony.studenthomework.server.model.Record
import tony.studenthomework.server.model.Records
import tony.studenthomework.server.utils.DatabaseFactory.dbQuery


object RecordRepository {

    suspend fun getRecords(): List<Record> = dbQuery {
        Records.selectAll().map {
            toRecord(it)
        }
    }

    suspend fun getRecord(id: Int): Record? = dbQuery {
        Records.select {
            (Records.id eq id)
        }.mapNotNull {
            toRecord(it)
        }.singleOrNull()
    }

    suspend fun updateRecord(record: Record): Record? {
        val existingRecord = dbQuery {
            Records.select {
                (Records.sid eq record.sid) and (Records.hid eq record.hid)
            }.mapNotNull {
                toRecord(it)
            }.singleOrNull()
        }
        return if (existingRecord == null) {
            addRecord(record)
        } else {
            try {
                @Suppress("ReplaceSingleLineLet")
                RecordStatusEnum.values()[record.status].let {
                    getRecord(dbQuery {
                        Records.update({ (Records.sid eq existingRecord.sid) and (Records.hid eq existingRecord.hid) }) {
                            it[status] = record.status
                        }
                    })
                }
            } catch (ex: ArrayIndexOutOfBoundsException) {
                println("Status value is out of bounds")
                existingRecord
            }
        }
    }

    private suspend fun addRecord(record: Record): Record? {
        var statusValue: Int
        try {
            RecordStatusEnum.values()[record.status].let {
                statusValue = it.status
            }
        } catch (ex: ArrayIndexOutOfBoundsException) {
            println("Status value is out of bounds")
            statusValue = RecordStatusEnum.values()[0].status
        }
        return getRecord(dbQuery {
            Records.insert {
                it[sid] = record.sid
                it[hid] = record.hid
                it[status] = statusValue
            } get Records.id
        })
    }

    private fun toRecord(row: ResultRow): Record =
        Record(
            id = row[Records.id],
            sid = row[Records.sid],
            hid = row[Records.hid],
            status = row[Records.status]
        )
}