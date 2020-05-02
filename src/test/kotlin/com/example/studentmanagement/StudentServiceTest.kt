package com.example.studentmanagement

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import io.mockk.*
import org.bson.Document
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StudentServiceTest{
    @Test
    fun `should add one entry of student`() {
        val mongoClient = mockk<MongoClient>()
        val mongoCollection = mockk<MongoCollection<Document>>()
        val studentService = StudentService(mongoClient)

        val documentToSave = Document(mapOf("name" to "dummyName", "age" to 23, "course" to "BSC"))

        every {
            mongoClient.getDatabase("student").getCollection("list")
        } returns mongoCollection

        every {
            mongoCollection.insertOne(documentToSave)
        } returns Unit

        assertEquals(Unit, studentService.addStudent("dummyName", 23, "BSC"))
    }
}