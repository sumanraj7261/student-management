package com.example.studentmanagement

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.util.AssertionErrors.assertEquals


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentManagementApplicationTests {
	@LocalServerPort
	private val port = 0

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Autowired
	lateinit var studentRepository: StudentRepository

	@BeforeEach
	fun initEach() {
		val student1 = Student("name1", 13, "BSC")
		val student2 = Student("name2", 19, "B.COM")
		studentRepository.saveAll(listOf(student1, student2))
	}

	@Test
	fun `should return a list of students`() {
		val response = restTemplate?.getForEntity("http://localhost:"+port+"/students", String::class.java)
		println("response \n"+response?.statusCode)
		assertEquals("this should pass ", "[{\"name\":\"name1\",\"age\":13,\"course\":\"BSC\"},{\"name\":\"name2\",\"age\":19,\"course\":\"B.COM\"}]", response?.body)
		assertEquals("Response code should be 200 ",200, response?.statusCodeValue)
	}
}
