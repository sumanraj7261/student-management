package com.example.studentmanagement

import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StudentControllerTest {
    @Test
    fun `should return list of students`() {
        val mockStudentService = mockk<StudentService>()
        val controller = StudentController(mockStudentService)

        val expected = listOf(Student("myname", 10, "BCA"))

        every{
            mockStudentService.getStudents()
        } returns expected

        val result = controller.list();

        assertEquals(expected, result)
    }

    @Test
    fun `should return a message saying that the current student data has been saved`() {
        val mockStudentService = mockk<StudentService>()
        val controller = StudentController(mockStudentService)

        val student = Student("bhawna", 22, "BCA")

        every {
            mockStudentService.addStudent("bhawna", 22, "BCA")
        } returns student

        val result = controller.addStudent("bhawna", 22, "BCA")

        assertEquals(student, result)
    }
}