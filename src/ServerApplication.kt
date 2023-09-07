package tony.studenthomework.server

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import tony.studenthomework.server.model.Homework
import tony.studenthomework.server.model.Record
import tony.studenthomework.server.model.Student
import tony.studenthomework.server.repository.HomeworkRepository
import tony.studenthomework.server.repository.RecordRepository
import tony.studenthomework.server.repository.StudentRepository
import tony.studenthomework.server.utils.DatabaseFactory


fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(StatusPages)
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }

    DatabaseFactory.init()

    routing {
        route("/student") {
            get {
                call.respond(StudentRepository.getStudents())
            }
            put {
                val student = call.receive<Student>()
                val updated = StudentRepository.updateStudent(student)
                if (updated == null) call.respond(HttpStatusCode.NotFound)
                else call.respond(HttpStatusCode.OK, updated)
            }
            route("/{id}") {
                get {
                    val id = call.parameters["id"]
                    if (id != null) {
                        val student = StudentRepository.getStudent(id.toInt())
                        if (student == null) {
                            call.respond(HttpStatusCode.NotFound)
                        } else {
                            call.respond(student)
                        }
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Invalid request!")
                    }
                }
                get("/detail") {
                    val id = call.parameters["id"]
                    if (id != null) {
                        val student = StudentRepository.getStudentDetail(id.toInt())
                        if (student == null) {
                            call.respond(HttpStatusCode.NotFound)
                        } else {
                            call.respond(student)
                        }
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Invalid request!")
                    }
                }
            }
        }
        route("/homework") {
            get("/") {
                call.respond(HomeworkRepository.getHomeworks())
            }
            put("/") {
                val json = call.receive<String>()
                val mapper = jacksonObjectMapper()
                val homeworks = mapper.readValue<List<Homework>>(json)
                val updated = HomeworkRepository.updateHomeworks(homeworks)
                call.respond(HttpStatusCode.OK, updated)
            }
            get("/{id}") {
                val id = call.parameters["id"]
                if (id != null) {
                    val homework = HomeworkRepository.getHomework(id.toInt())
                    if (homework == null) {
                        call.respond(HttpStatusCode.NotFound)
                    } else {
                        call.respond(homework)
                    }
                } else {
                    call.respond(HttpStatusCode.NotFound, "Invalid request!")
                }
            }
            put("/{id}") {
                val homework = call.receive<Homework>()
                val updated = HomeworkRepository.updateHomework(homework)
                if (updated == null) call.respond(HttpStatusCode.NotFound)
                else call.respond(HttpStatusCode.OK, updated)
            }
        }
        route("/record") {
            get("/") {
                call.respond(RecordRepository.getRecords())
            }
            put("/") {
                val json = call.receive<String>()
                val mapper = jacksonObjectMapper()
                val records = mapper.readValue<List<Record>>(json)
                val updated = RecordRepository.updateRecords(records)
                call.respond(HttpStatusCode.OK, updated)
            }
            get("/{id}") {
                val id = call.parameters["id"]
                if (id != null) {
                    val record = RecordRepository.getRecord(id.toInt())
                    if (record == null) {
                        call.respond(HttpStatusCode.NotFound)
                    } else {
                        call.respond(record)
                    }
                } else {
                    call.respond(HttpStatusCode.NotFound, "Invalid request!")
                }
            }
            put("/{id}") {
                val record = call.receive<Record>()
                val updated = RecordRepository.updateRecord(record)
                if (updated == null) call.respond(HttpStatusCode.NotFound)
                else call.respond(HttpStatusCode.OK, updated)
            }
        }
    }
}

