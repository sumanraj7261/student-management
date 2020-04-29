package com.example.studentmanagement

import io.mockk.*
import org.junit.jupiter.api.Assertions

import org.junit.jupiter.api.Test

class StudentControllerTest {
    @Test
    fun `should return list of students`() {
        val mockStudentService = mockk<StudentService>()
        val controller = StudentController(mockStudentService)

        every{
            mockStudentService.getStudents()
        } returns listOf(mapOf("name" to "someone"))

        val result = controller.list();

        Assertions.assertEquals(1, result.size)
    }
}