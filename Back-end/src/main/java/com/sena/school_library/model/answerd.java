package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "answerd")
public class answerd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_answerd", length = 10)
    private int Id_answerd;

    @Column(name = "answerdQuestion", length = 255)
    private String answerdQuestion;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public answerd() {
    }
    

    public answerd(int id_answerd, String answerdQuestion, Question question) {
        Id_answerd = id_answerd;
        this.answerdQuestion = answerdQuestion;
        this.question = question;
    }


    public int getId_answerd() {
        return Id_answerd;
    }

    public void setId_answerd(int id_answerd) {
        Id_answerd = id_answerd;
    }

    public String getAnswerdQuestion() {
        return answerdQuestion;
    }

    public void setAnswerdQuestion(String answerdQuestion) {
        this.answerdQuestion = answerdQuestion;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
