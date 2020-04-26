package com.example.studentmanagement

import org.springframework.stereotype.Component

@Component
class Students {
    private val student1 = Student("first-name", 20, "BCA");
    private val student2 = Student("second-name", 21, "BSC");
    private val student3 = Student("third-name", 22, "MSC");
    private val student4 = Student("fourth-name", 23, "BCA");
    private val students:List<Student> = listOf(student1, student2, student3, student4)

    fun getStudents(): List<Student> {
        return students
    }
}