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

    @Test
    fun `should return a message saying that the current student data has been saved`() {
        val mockStudentService = mockk<StudentService>()
        val controller = StudentController(mockStudentService)

        every {
            mockStudentService.addStudent("bhawna", 22, "BCA")
        } returns Unit

        val result = controller.addStudent("bhawna", 22, "BCA")

        Assertions.assertEquals("Entry is successful with name bhawna age 22 and course BCA", result)
    }
}