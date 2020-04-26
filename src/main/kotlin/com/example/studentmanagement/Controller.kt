package com.example.studentmanagement

import org.springframework.web.bind.annotation.*

@RestController
class Controller {
    @GetMapping("/get-students")
    fun greet(): List<Student> {
        return Students().getStudents()
    }
}