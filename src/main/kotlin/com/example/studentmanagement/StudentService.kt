package com.example.studentmanagement

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class StudentService(
        @Autowired(required=true) private val studentRepository: StudentRepository
) {

    fun addStudent(name: String, age: Int, course: String): Mono<Student> {
        val student = Student(name, age, course)
        return studentRepository.save(student)
    }

    fun getStudents(): Flux<Student> {
        return studentRepository.findAll()
    }

    fun findByName(name: String): Mono<Student> {
        return studentRepository.findByName(name)
    }
}