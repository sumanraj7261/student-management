package com.example.studentmanagement


import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class StudentService(
        @Autowired private val mongoTemplate: MongoTemplate
) {

    fun getMongoCollection(): MongoCollection<Document>{
        return mongoTemplate.db.getCollection("list")
    }

    fun addStudent(name: String, age: Int, course: String) {
        val collection = getMongoCollection()
        val dataToSave = Document(mapOf("name" to name, "age" to age, "course" to course))
        collection.insertOne(dataToSave)
    }

    fun getStudents(): List<Map<String, Any>> {
        val collection = getMongoCollection()
        val query = Document(mapOf())
        val list =  collection.find(query)
        return list.toList().map { it -> it.minus("_id") }
    }

}