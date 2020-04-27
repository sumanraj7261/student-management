package com.example.studentmanagement

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class StudentController(
        @Autowired
        private val studentService : StudentService
) {

    @GetMapping("/students")
    fun list(): ResponseEntity<List<Student>> {
        return ResponseEntity.ok(studentService.getStudents())
    }

    @PostMapping("/add")
    fun addStudent(@RequestParam name: String, @RequestParam age: Int, @RequestParam course: String): String{
        studentService.addStudent(name, age, course)
        return "Entry is successful with name $name age $age and course $course"
    }
}