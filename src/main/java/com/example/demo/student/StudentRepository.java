package com.example.demo.student;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends Neo4jRepository<Student, Long> {
    @Query("MATCH (s:Student) RETURN s")
    List<Student> getAllUsers();

    @Query("CREATE (s:Student {name: $name, email: $email, dob: $dob, sku: $sku}) RETURN s")
    Student createUser(String name, String email, LocalDate dob, String sku);
}