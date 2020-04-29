package com.example.studentmanagement


import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentService(
        @Autowired private val mongoClient: MongoClient
) {

    fun getMongoCollection(): MongoCollection<Document>{
        return  mongoClient.getDatabase("student").getCollection("list")
    }

    fun addStudent(name: String, age: Int, course: String) {
        val collection = getMongoCollection()
        collection.insertOne(org.bson.Document(mapOf("name" to name, "age" to age, "course" to course)))
    }

    fun getStudents(): List<Map<String, Any>> {
        val collection = getMongoCollection()
        val list =  collection.find(org.bson.Document(mapOf()))
        return list.toList().map { it -> it.minus("_id") }
    }

}