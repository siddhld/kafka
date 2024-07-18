package com.kafka.service;


import com.kafka.entity.Student;
import com.kafka.entity.Subject;
import com.kafka.entity.Exam;
import com.kafka.repository.StudentRepository;
import com.kafka.repository.SubjectRepository;
import com.kafka.repository.ExamRepository;
import com.kafka.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ExamRepository examRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              SubjectRepository subjectRepository,
                              ExamRepository examRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.examRepository = examRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);
        student.setName(studentDetails.getName());
        student.setRegistrationId(studentDetails.getRegistrationId());
        return studentRepository.save(student);
    }

    @Override
    public String deleteStudent(Long id) {
        Student student = getStudentById(id);
        try {
            studentRepository.delete(student);
        }catch (Exception ex){
            throw new ResourceNotFoundException("Subject not found with id: " + id);
        }
        return "Student deleted successfully";
    }

    @Override
    @Transactional
    public Student enrollStudentInSubject(Long studentId, Long subjectId) {
        Student student = getStudentById(studentId);
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));

        student.getEnrolledSubjects().add(subject);
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student registerStudentForExam(Long studentId, Long examId) {
        Student student = getStudentById(studentId);
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id: " + examId));

        if (!student.getEnrolledSubjects().contains(exam.getSubject())) {
            throw new IllegalStateException("Student must be enrolled in the subject before registering for the exam");
        }

        student.getRegisteredExams().add(exam);
        return studentRepository.save(student);
    }
}