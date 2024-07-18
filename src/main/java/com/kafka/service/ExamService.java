package com.kafka.service;

import com.kafka.entity.Exam;
import java.util.List;

public interface ExamService {
    Exam createExam(Exam exam);
    List<Exam> getAllExams();
    Exam getExamById(Long id);
}