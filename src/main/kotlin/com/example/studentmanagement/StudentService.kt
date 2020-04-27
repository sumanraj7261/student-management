package com.example.studentmanagement


import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.springframework.stereotype.Service

@Service
class StudentService {
    private val student1 = Student("first-name", 20, "BCA");
    private val student2 = Student("second-name", 21, "BSC");
    private val student3 = Student("third-name", 22, "MSC");
    private val student4 = Student("fourth-name", 23, "BCA");
    private val students:List<Student> = listOf(student1, student2, student3, student4)

    fun getMongoCollection(): MongoCollection<Document>{
        val mongoClient = MongoClient("localhost", 27017)
        return  mongoClient.getDatabase("student").getCollection("list")
    }

    fun addStudent(name: String, age: Int, course: String) {
        val collection = getMongoCollection()
        collection.insertOne(org.bson.Document(mapOf("name" to name, "age" to age, "course" to course)))
    }

    fun getStudents(): List<Student> {
        return students
    }
}