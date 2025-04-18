package com.sena.school_library.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "answerd")
public class answerd {
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_answerd", length = 10)
    private int Id_answerd ;

    @Column(name= "answerdQuestion", length = 10)
    private String answerdQuestion ;

    @OneToOne
    @JoinColumn(name = "Id_Question")
    private Question question;

    @OneToMany(mappedBy = "answerd") 
    private List<Result> results;

    public answerd() {
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

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

   
}
