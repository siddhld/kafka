package com.kafka.controller;

import com.kafka.entity.Student;
import com.kafka.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        String response = studentService.deleteStudent(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{registrationId}/enroll/{subjectId}")
    public ResponseEntity<Student> enrollStudentInSubject(@PathVariable Long registrationId, @PathVariable Long subjectId) {
        Student enrolledStudent = studentService.enrollStudentInSubject(registrationId, subjectId);
        return ResponseEntity.ok(enrolledStudent);
    }

    @PostMapping("/{registrationId}/register/{examId}")
    public ResponseEntity<Student> registerStudentForExam(@PathVariable Long registrationId, @PathVariable Long examId) {
        Student registeredStudent = studentService.registerStudentForExam(registrationId, examId);
        return ResponseEntity.ok(registeredStudent);
    }
}