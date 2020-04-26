package com.example.studentmanagement

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StudentsTest{
    var students = Students().getStudents();

    @Test
    fun shouldReturnAllStudent() {
        assertEquals(4, students.size)
    }

    @Test
    fun shouldReturnCorrectDataOfFirstStudent() {
        val student = students.get(0)

        assertEquals(student.name, "first-name")
    }
}