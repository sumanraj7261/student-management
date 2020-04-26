package com.example.studentmanagement

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
	private var restTemplate: TestRestTemplate? = null

	@Test
	fun greetingShouldReturnDefaultMessage() {
		restTemplate = TestRestTemplate()
		val response = restTemplate?.getForObject("http://localhost:"+port+"/get-students", String::class.java)
		assertEquals("this should pass ", "[{\"name\":\"first-name\",\"age\":20,\"course\":\"BCA\"},{\"name\":\"second-name\",\"age\":21,\"course\":\"BSC\"},{\"name\":\"third-name\",\"age\":22,\"course\":\"MSC\"},{\"name\":\"fourth-name\",\"age\":23,\"course\":\"BCA\"}]", response)
	}

}
