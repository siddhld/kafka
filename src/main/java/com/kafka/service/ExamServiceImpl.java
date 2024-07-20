package com.kafka.service;

import com.kafka.entity.Exam;
import com.kafka.producer.KafkaProducer;
import com.kafka.repository.ExamRepository;
import com.kafka.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final KafkaProducer kafkaProducer;


    @Autowired
    public ExamServiceImpl(ExamRepository examRepository, KafkaProducer kafkaProducer) {
        this.examRepository = examRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public Exam createExam(Exam exam) {
        Exam createdExam = examRepository.save(exam);
        kafkaProducer.sendMessage("exam-topic", createdExam);
        return createdExam;
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id: " + id));
    }
}