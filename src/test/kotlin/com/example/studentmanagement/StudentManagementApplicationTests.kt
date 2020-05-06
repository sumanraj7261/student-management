package com.example.studentmanagement

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentManagementApplicationTests {
	@LocalServerPort
	private val port = 0

	@Autowired
	lateinit var client: WebTestClient

	@Autowired
	lateinit var studentRepository: StudentRepository

	@BeforeEach
	fun initEach() {
		studentRepository.deleteAll()
		val student1 = Student("name1", 13, "BSC")
		val student2 = Student("name2", 19, "B.COM")
		studentRepository.saveAll(listOf(student1, student2))
	}

	@Test
	fun `should return a list of students`() {
		val response = client.get()
				.uri("http://localhost:$port/students")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().is2xxSuccessful
				.expectBody(String::class.java)
				.returnResult().responseBody

		assertEquals("[{\"name\":\"name1\",\"age\":13,\"course\":\"BSC\"},{\"name\":\"name2\",\"age\":19,\"course\":\"B.COM\"}]", response)
	}

	@Test
	fun `should add a student`() {
		val response = client.post()
				.uri("http://localhost:$port/student?name=bhawna&age=23&course=bsc")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().is2xxSuccessful
				.expectBody(String::class.java)
				.returnResult().status

		assert(response.is2xxSuccessful)

		val result = studentRepository.findByName("bhawna")
		assertEquals(result.age, 23 )
		assertEquals( result.course, "bsc")
	}
}
