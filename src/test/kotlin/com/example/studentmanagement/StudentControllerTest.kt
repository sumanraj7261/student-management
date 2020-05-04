package com.example.studentmanagement

import io.mockk.*
import org.junit.jupiter.api.Assertions
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

        Assertions.assertEquals(expected, result)
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