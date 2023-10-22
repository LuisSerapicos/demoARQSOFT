package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;
    private final SKUGeneratorService skuGeneratorService;

    @Autowired
    public StudentController(StudentService studentService, SKUGeneratorService skuGeneratorService) {
        this.studentService = studentService;
        this.skuGeneratorService = skuGeneratorService;
    }

    @Value("${sku.generator.default}")
    private String methodGenerator;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/create")
    public Student createUser(@RequestBody Student student) {
        switch (methodGenerator) {
            case "skuGeneratorA":
                return studentService.createUser(student.getName(), student.getEmail(), student.getDob(), skuGeneratorService.generateSKUA());
            case "skuGeneratorB":
                return studentService.createUser(student.getName(), student.getEmail(), student.getDob(), skuGeneratorService.generateSkuB(student.getName()));
            case "skuGeneratorC":
                return studentService.createUser(student.getName(), student.getEmail(), student.getDob(), skuGeneratorService.generateSkuC(student.getName()));
                default:
                throw new IllegalArgumentException("Invalid SKU generator configuration");
        }
    }
}
