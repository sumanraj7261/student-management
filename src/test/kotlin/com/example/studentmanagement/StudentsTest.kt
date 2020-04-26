package com.example.studentmanagement

import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StudentsTest{
    val mockedStudents = mockk<Students>()
    val mockedStudent = mockk<Student>()

    @Test
    fun shouldReturnAllStudent() {
        every{ mockedStudents.getStudents() } returns listOf(mockedStudent)

        assertEquals(1, mockedStudents.getStudents().size)
    }
}