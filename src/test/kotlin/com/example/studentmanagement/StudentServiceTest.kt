package com.example.studentmanagement

import com.mongodb.MongoClient
import com.mongodb.async.client.FindIterable
import io.mockk.*
import org.bson.Document
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StudentServiceTest{

    @Test
    fun shouldReturnAllStudent() {
        val mockedMongoClient = mockk<MongoClient>()
        val studentService = StudentService(mockedMongoClient)

        every {
            mockedMongoClient.getDatabase("student").getCollection("list").find(org.bson.Document(mapOf()))
        } returns FindIterable<Document!>(Document(mapOf("name" to " first-name ")))

        assertEquals(1, studentService.getStudents().size)
    }
}