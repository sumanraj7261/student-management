package com.example.studentmanagement

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class StudentController(
        @Autowired
        private val studentService : StudentService
) {

    @GetMapping("/students")
    fun list(): Flux<Student> {
        return studentService.getStudents()
    }

     @GetMapping("/")
    fun greet(): String {
        return "Welcome in student's world" 
    }

    @PostMapping("/student")
    fun addStudent(@RequestParam name: String, @RequestParam age: Int, @RequestParam course: String): Mono<Student>{
       return studentService.addStudent(name, age, course)
    }

}