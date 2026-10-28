package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.exception.StudentNotFoundException;


@Service
public class StudentService {

    private int nextStudentId = 101;

    private List<Student> students = new ArrayList<>();

    public Student registerStudent(Student request) {

        Student student = new Student(
                nextStudentId++,
                request.getStudentName().trim(),
                request.getCourseName().trim(),
                request.getEmail().trim(),
                request.getAge()
        );

        students.add(student);

        return student;
    }


    public List<Student> getAllStudents() {
        return students;
    }

    
    public Student getStudentById(Integer id) {

        for (Student student : students) {

            if (student.getStudentId().equals(id)) {
                return student;
            }
        }

        throw new StudentNotFoundException("Student not found with ID: " + id);
    }


    public void deleteStudent(Integer id) {

        boolean removed = students.removeIf(
                student -> student.getStudentId().equals(id));

        if (!removed) {
            throw new StudentNotFoundException("Student not found with ID: " + id);
        }

    }
    
    
    public Student updateStudent(Student request) {

        for (Student student : students) {

            if (student.getStudentId().equals(request.getStudentId())) {

                student.setStudentName(request.getStudentName().trim());
                student.setCourseName(request.getCourseName().trim());
                student.setEmail(request.getEmail().trim());
                student.setAge(request.getAge());

                return student;
            }
        }

        throw new StudentNotFoundException("Student not found with ID: " + request.getStudentId());
    }
    
    
    public Student patchStudent(Integer id, Student request) {

        for (Student student : students) {

            if (student.getStudentId().equals(id)) {

                if (request.getStudentName() != null) {
                    student.setStudentName(request.getStudentName().trim());
                }

                if (request.getCourseName() != null) {
                    student.setCourseName(request.getCourseName().trim());
                }

                if (request.getEmail() != null) {
                    student.setEmail(request.getEmail().trim());
                }

                if (request.getAge() != null) {
                    student.setAge(request.getAge());
                }

                return student;
            }
        }

        throw new StudentNotFoundException("Student not found with ID: " + id);
    }
    
    
    public List<Student> getStudentsByCourse(String courseName) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (student.getCourseName().equalsIgnoreCase(courseName)) {
                result.add(student);
            }
        }

        if (result.isEmpty()) {
            throw new StudentNotFoundException("No students found for course: " + courseName);
        }

        return result;
    }
    
    public Map<String, Integer> getCourseStudentCount() {

        Map<String, Integer> result = new HashMap<>();

        for (Student s : students) {

            String courseName = s.getCourseName();

            if (result.containsKey(courseName)) {
                result.put(courseName, result.get(courseName) + 1);
            } else {
                result.put(courseName, 1);
            }
        }

        return result;
    }
}
