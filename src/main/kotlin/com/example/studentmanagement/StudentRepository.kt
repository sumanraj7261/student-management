package com.example.studentmanagement

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface StudentRepository: ReactiveCrudRepository<Student, String> {
    fun findByName(name: String): Mono<Student>
}