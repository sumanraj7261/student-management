package com.example.studentmanagement

import org.springframework.data.mongodb.core.mapping.Document

@Document("student")
data class Student(val name: String, val age: Int, val course: String)