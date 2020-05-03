package com.example.studentmanagement

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoCursor
import io.mockk.every
import io.mockk.mockk
import org.bson.Document
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.mongodb.core.MongoTemplate

class StudentServiceTest{

    @Test
    fun `should return a list of student's data`() {
        val mongoTemplate = mockk<MongoTemplate>()
        val mongoCollection = mockk<MongoCollection<Document>>()
        val iterable = mockk<FindIterable<Document>>()
        val cursor = mockk<MongoCursor<Document>>()

        val studentService = StudentService(mongoTemplate)

        every {
            mongoTemplate.db.getCollection("list")
        } returns mongoCollection

        every {
            mongoCollection.find(Document(mapOf()))
        } returns iterable

        every {
            iterable.iterator()
        } returns cursor

        every {
            cursor.hasNext()
        }  returns false

        val emptylist = listOf<Map<String, Any>>()
        assertEquals(emptylist, studentService.getStudents())
    }

    @Test
    fun `should add one entry of student`() {
        val mongoTemplate = mockk<MongoTemplate>()
        val mongoCollection = mockk<MongoCollection<Document>>()
        val studentService = StudentService(mongoTemplate)

        val documentToSave = Document(mapOf("name" to "dummyName", "age" to 23, "course" to "BSC"))

        every {
            mongoTemplate.db.getCollection("list")
        } returns mongoCollection

        every {
            mongoCollection.insertOne(documentToSave)
        } returns Unit

        assertEquals(Unit, studentService.addStudent("dummyName", 23, "BSC"))
    }
}