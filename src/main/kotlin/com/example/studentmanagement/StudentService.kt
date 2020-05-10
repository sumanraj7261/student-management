package com.example.studentmanagement

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono

@Service
class StudentService(
        @Autowired private val studentRepository: StudentRepository
) {

    fun addStudent(name: String, age: Int, course: String): Mono<Student> {
        val student = Student(name, age, course)
        return Mono.just(studentRepository.save(student))
    }

    fun getStudents(): Flux<Student> {
        return studentRepository.findAll().toFlux()
    }

    fun findByName(name: String): Mono<Student> {
        return studentRepository.findByName(name).toMono()
    }
}