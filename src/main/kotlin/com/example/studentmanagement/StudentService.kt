package com.example.studentmanagement

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class StudentService(
        @Autowired private val studentRepository: StudentRepository
) {

    fun addStudent(name: String, age: Int, course: String): Mono<Student> {
        val student = Student(name, age, course)
        return Mono.just(studentRepository.save(student))
    }

    fun getStudents(): List<Student> {
        return studentRepository.findAll().toList()
    }

    fun findByName(name: String): Student {
        return studentRepository.findByName(name)
    }
}