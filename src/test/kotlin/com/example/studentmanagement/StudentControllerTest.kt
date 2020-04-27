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
        } returns listOf(Student("first", 10, "B"), Student("second", 12, "C"))

        val result = controller.list();

        Assertions.assertEquals(2, result.body?.size)
    }
}