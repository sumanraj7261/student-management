package com.example.studentmanagement

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class StudentServiceTest{

    @Test
    fun `should return a list of student's data`() {
        val mockedStudentRepository = mockk<StudentRepository>()
        val studentService = StudentService(mockedStudentRepository)

        val expected = Student("name", 21, "BBA")

        every {
            mockedStudentRepository.findAll()
        } returns Flux.just(expected)

        StepVerifier
                .create(studentService.getStudents())
                .expectNext(expected)
                .expectComplete()
                .verify()
    }

    @Test
    fun `should add one entry of student`() {
        val mockedStudentRepository = mockk<StudentRepository>()
        val studentService = StudentService(mockedStudentRepository)

        val student = Student("name", 23, "BSC")

        every {
            mockedStudentRepository.save(student)
        } returns  Mono.just(student)

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
        } returns Mono.just(student)

        StepVerifier
                .create(studentService.findByName("name"))
                .expectNext(student)
                .expectComplete()
                .verify()
    }
}