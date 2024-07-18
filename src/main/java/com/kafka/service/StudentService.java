package com.kafka.service;

import com.kafka.entity.Student;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    Student updateStudent(Long id, Student studentDetails);
    String deleteStudent(Long id);
    Student enrollStudentInSubject(Long studentId, Long subjectId);
    Student registerStudentForExam(Long studentId, Long examId);
}