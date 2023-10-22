package com.example.demo.student;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.Period;

@Node("Student")
public class Student {
    @Id
    @GeneratedValue private Long id;

    //private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private transient Integer age;
    private String sku;

    public Student() {

    }
    public Student(
            Long id,
            String name,
            String email,
            LocalDate dob,
            String sku) {
     this.id = id;
     this.name = name;
     this.email = email;
     this.dob = dob;
     this.sku = sku;
    }

    public Student(String name, String email, LocalDate dob, String sku) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.sku = sku;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", sku=" + sku +
                ", age=" + age +
                '}';
    }
}
