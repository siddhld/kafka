package com.kafka.consumer;

import com.kafka.entity.Exam;
import com.kafka.entity.Student;
import com.kafka.entity.Subject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "student-topic", groupId = "weekly-sentiment-group")
    public void consumeStudent(Student student) {
        System.out.println("Received Student: " + student.toString());
        // Process the student data as needed
    }

    @KafkaListener(topics = "exam-topic", groupId = "weekly-sentiment-group")
    public void consumeExam(Exam exam) {
        System.out.println("Received Exam: " + exam.toString());
        // Process the exam data as needed
    }

    @KafkaListener(topics = "subject-topic", groupId = "weekly-sentiment-group")
    public void consumeSubject(Subject subject) {
        System.out.println("Received Subject: " + subject.toString());
        // Process the subject data as needed
    }
}