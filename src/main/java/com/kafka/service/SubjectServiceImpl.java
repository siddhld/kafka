package com.kafka.service;

import com.kafka.entity.Subject;
import com.kafka.producer.KafkaProducer;
import com.kafka.repository.SubjectRepository;
import com.kafka.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final KafkaProducer kafkaProducer;


    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, KafkaProducer kafkaProducer) {
        this.subjectRepository = subjectRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public Subject createSubject(Subject subject) {
        Subject createdSubject = subjectRepository.save(subject);
        kafkaProducer.sendMessage("subject-topic", createdSubject);
        return createdSubject;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
    }
}