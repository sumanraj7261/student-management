package com.example.studentmanagement

import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.kafka.core.KafkaTemplate
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import java.util.concurrent.CompletableFuture

class StudentControllerTest {
    @Test
    fun `should return list of students`() {
        val mockStudentService = mockk<StudentService>()
        val mockKafkaTemplate = mockk<KafkaTemplate<String, String>>()
        val mockKafkaRepository = mockk<KafkaRepository>()
        val controller = StudentController(mockStudentService, mockKafkaTemplate,"test", mockKafkaRepository)

        val expected = listOf(Student("myname", 10, "BCA")).toFlux()

        every{
            mockStudentService.getStudents()
        } returns expected

        val result = controller.list();

        assertEquals(expected, result)
    }

    fun `should return a message saying that the current student data has been saved`() {
        val mockKafkaTemplate = mockk<KafkaTemplate<String, String>>()
        val mockKafkaRepository = mockk<KafkaRepository>()
        val mockStudentService = mockk<StudentService>()
        val controller = StudentController(mockStudentService, mockKafkaTemplate,"test", mockKafkaRepository)

        val student = Mono.just(Student("bhawna", 22, "BCA"))

        every {
            mockStudentService.addStudent("bhawna", 22, "BCA")
        } returns student

        val result = controller.addStudent("bhawna", 22, "BCA")

        assertEquals(student, result)
    }
}