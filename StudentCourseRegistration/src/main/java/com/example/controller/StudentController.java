package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.example.entity.Student;
import com.example.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/register")
    public Student registerStudent(@Valid @RequestBody Student request) {
        return studentService.registerStudent(request);
    }


    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "Student registration deleted successfully";
    }
    
    @PutMapping("/update")
    public String updateStudent(@Valid @RequestBody Student request) {
         studentService.updateStudent(request);
         
         return "Updated Successfully";
    }
    
    @PatchMapping("/{id}")
    public Student patchStudent(@PathVariable Integer id,@RequestBody Student request) {

        return studentService.patchStudent(id, request);
    }
    
    @GetMapping("/course/{courseName}")
    public List<Student> getStudentsByCourse(@PathVariable String courseName) {

        return studentService.getStudentsByCourse(courseName);
    }
    
    @GetMapping("/courseCount")
    public Map<String, Integer> getCourseStudentCount() {
        return studentService.getCourseStudentCount();
    }
}
