package com.example.studentmanagement

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StudentServiceTest{

    @Test
    fun shouldReturnAllStudent() {
        val studentService = StudentService()
        assertEquals(4, studentService.getStudents().size)
    }
}