package com.example.studentmanagement

import com.example.studentmanagement.kafka.Kafka
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface StudentRepository: ReactiveCrudRepository<Student, String> {
    fun findByName(name: String): Mono<Student>
}

@Repository
interface KafkaRepository: ReactiveCrudRepository<Kafka, String> {}