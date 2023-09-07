package tony.studenthomework.server.repository

import org.jetbrains.exposed.sql.*
import tony.studenthomework.server.model.Record
import tony.studenthomework.server.model.RecordStatusEnum
import tony.studenthomework.server.model.Records
import tony.studenthomework.server.model.Records.hid
import tony.studenthomework.server.model.Records.sid
import tony.studenthomework.server.model.Records.status
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

    suspend fun updateRecords(records: List<Record>): List<Record> {
        val list = arrayListOf<Record>()
        records.forEach {
            val updatedRecord = updateRecord(it)
            if (updatedRecord != null) {
                list.add(updatedRecord)
            }
        }
        return list
    }

    suspend fun updateRecord(record: Record): Record? {
        val existingRecord = dbQuery {
            Records.select {
                (sid eq record.sid) and (hid eq record.hid)
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
                        Records.update({ (sid eq existingRecord.sid) and (hid eq existingRecord.hid) }) {
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
            sid = row[sid],
            hid = row[hid],
            status = row[status]
        )
}