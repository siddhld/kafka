package com.kafka.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "student_subject_enrollment",
            joinColumns = @JoinColumn(name = "registration_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    @ToString.Exclude
    private List<Subject> enrolledSubjects;

    @ManyToMany
    @JoinTable(
            name = "student_exam_registration",
            joinColumns = @JoinColumn(name = "registration_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    @ToString.Exclude
    private List<Exam> registeredExams;
}
