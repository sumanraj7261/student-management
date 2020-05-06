package com.example.studentmanagement

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class StudentController(
        @Autowired
        private val studentService : StudentService
) {

    @GetMapping("/students")
    fun list(): List<Student> {
        return studentService.getStudents()
    }

    @PostMapping("/student")
    fun addStudent(@RequestParam name: String, @RequestParam age: Int, @RequestParam course: String): Student{
       return studentService.addStudent(name, age, course)
    }
}