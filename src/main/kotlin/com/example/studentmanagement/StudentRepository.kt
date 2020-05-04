package com.example.studentmanagement

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository: CrudRepository<Student, String> {
    fun findByName(name: String): Student
}