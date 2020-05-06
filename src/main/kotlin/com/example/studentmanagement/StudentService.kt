package com.example.studentmanagement

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentService(
        @Autowired private val studentRepository: StudentRepository
) {

    fun addStudent(name: String, age: Int, course: String): Student{
        val student = Student(name, age, course)
        return studentRepository.save(student)
    }

    fun getStudents(): List<Student> {
        return studentRepository.findAll().toList()
    }

    fun findByName(name: String): Student {
        return studentRepository.findByName(name)
    }
}