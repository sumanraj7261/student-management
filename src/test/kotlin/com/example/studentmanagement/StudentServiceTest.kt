package com.example.studentmanagement

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

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

        StepVerifier
                .create(studentService.addStudent("name", 23, "BSC"))
                .expectNext(student)
                .expectComplete()
                .verify()

    }

    @Test
    fun `should return a student whose name will be same as in query`() {
        val mockedStudentRepository = mockk<StudentRepository>()
        val studentService = StudentService(mockedStudentRepository)

        val student = Student("name", 23, "BSC")

        every {
            mockedStudentRepository.findByName("name")
        } returns student

        assertEquals(student, studentService.findByName("name"))
    }
}