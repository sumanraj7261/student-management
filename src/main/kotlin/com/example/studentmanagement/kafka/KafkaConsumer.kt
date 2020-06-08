package com.example.studentmanagement.kafka

import com.example.studentmanagement.KafkaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaService(
        @Autowired(required = true)
        private val kafkaRepository: KafkaRepository
) {

    @KafkaListener(topics = ["\${topic}"], groupId = "foo")
    fun listen(message: String) {
        kafkaRepository.save(Kafka(message)).block()
        println("Received Messasge in group foo: $message")
    }
}


data class Kafka(val message: String)