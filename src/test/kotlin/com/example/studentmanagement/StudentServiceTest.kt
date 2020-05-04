package com.example.studentmanagement
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StudentServiceTest{

    @Test
    fun `should return a list of student's data`() {
        val mockedStudentRepository = mockk<StudentRepository>()
        val studentService = StudentService(mockedStudentRepository)

        every {
            mockedStudentRepository.findAll().iterator().hasNext()  // can I skip defining definition for iterator
        } returns false

        assertEquals(emptyList<Student>(), studentService.getStudents())
    }

    @Test
    fun `should add one entry of student`() {
        val mockedStudentRepository = mockk<StudentRepository>()
        val studentService = StudentService(mockedStudentRepository)

        val student = Student("name", 23, "BSC")

        every {
            mockedStudentRepository.save(student)
        } returns student

        assertEquals(Unit, studentService.addStudent("name", 23, "BSC"))
    }
}