package com.kafka.service;


import com.kafka.exception.DataAlreadyExistException;
import com.kafka.exception.ResourceNotFoundException;
import com.kafka.entity.Exam;
import com.kafka.entity.Student;
import com.kafka.entity.Subject;
import com.kafka.producer.KafkaProducer;
import com.kafka.repository.ExamRepository;
import com.kafka.repository.StudentRepository;
import com.kafka.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ExamRepository examRepository;
    private final KafkaProducer kafkaProducer;


    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              SubjectRepository subjectRepository,
                              ExamRepository examRepository,
                              KafkaProducer kafkaProducer) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.examRepository = examRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public Student createStudent(Student student) {
        Student createdStudent = studentRepository.save(student);
        kafkaProducer.sendMessage("student-topic", createdStudent);
        return createdStudent;
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
    public Student enrollStudentInSubject(Long registrationId, Long subjectId) {
        Student student = getStudentById(registrationId);

        boolean subjectFound = student.getEnrolledSubjects().stream()
                .anyMatch(subject -> subjectId.equals(subject.getSubjectId()));

        if (subjectFound) {
            throw new DataAlreadyExistException("Subject with ID " + subjectId + " is already enrolled.");
        }

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));

        student.getEnrolledSubjects().add(subject);
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student registerStudentForExam(Long registrationId, Long examId) {
        Student student = getStudentById(registrationId);

        boolean examFound = student.getRegisteredExams().stream()
                .anyMatch(exam -> examId.equals(exam.getExamId()));

        if (examFound) {
            throw new DataAlreadyExistException("Exam with ID " + examId + " is already registered.");
        }

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id: " + examId));

        if (!student.getEnrolledSubjects().contains(exam.getSubject())) {
            throw new IllegalStateException("Student must be enrolled in the subject before registering for the exam");
        }

        student.getRegisteredExams().add(exam);
        return studentRepository.save(student);
    }
}