package com.example.studentmanagement

import com.example.studentmanagement.kafka.Kafka
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class StudentController(
        @Autowired
        private val studentService : StudentService,
        @Autowired
        private val kafkaTemplate: KafkaTemplate<String, String>,
        @Value("\${topic}")
        private val topic: String,
        @Autowired
        private val kafkaRepository: KafkaRepository
) {

    @GetMapping("/students")
    fun list(): Flux<Student> {
        return studentService.getStudents()
    }

    @GetMapping("/msgs")
    fun list1(): Flux<Kafka> {
       return kafkaRepository.findAll()
    }

     @GetMapping("/")
    fun greet(): String {
        return "Welcome in student's world" 
    }

    @PostMapping("/student")
    fun addStudent(@RequestParam name: String, @RequestParam age: Int, @RequestParam course: String): Mono<Student>{
        val msg = "New Entry $name $age $course"
        kafkaTemplate.send(topic, msg)
        return studentService.addStudent(name, age, course)
    }

}