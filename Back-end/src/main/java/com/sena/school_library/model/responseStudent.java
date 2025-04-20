package com.sena.school_library.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "responseStudent")
public class responseStudent {
    @Id
    // El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @colum indica que el valor es una columna en la base de datos
    @Column(name = "Id_responseStudent", length = 10)
    private int Id_responseStudent;

    @Column(name = "student_answer")
    private String studentAnswer;

    @OneToOne
    @JoinColumn(name = "Id_Question")
    private Question question;

    @OneToMany(mappedBy = "responseStudent")
    @JsonManagedReference
    private List<Resultado> results;

    public responseStudent() {
    }

    public responseStudent(int id_responseStudent, String studentAnswer, Question question, List<Resultado> results) {
        Id_responseStudent = id_responseStudent;
        this.studentAnswer = studentAnswer;
        this.question = question;
        this.results = results;
    }

    public int getId_responseStudent() {
        return Id_responseStudent;
    }

    public void setId_responseStudent(int id_responseStudent) {
        Id_responseStudent = id_responseStudent;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Resultado> getResults() {
        return results;
    }

    public void setResults(List<Resultado> results) {
        this.results = results;
    }

   
    
}
