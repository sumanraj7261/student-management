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
            studentService.getStudents()
        } returns listOf(mapOf("name" to 1))

        println("student " + studentService.getStudents())

        assertEquals(1, studentService.getStudents().size)
    }
}