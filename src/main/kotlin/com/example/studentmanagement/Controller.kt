package com.example.studentmanagement

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class Controller {
    @GetMapping("/get-students")
    fun list(): ResponseEntity<List<Student>> {
        return ResponseEntity.ok(Students().getStudents())
    }
}