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

@Entity(name = "responseStudent")
public class responseStudent {
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_responseStudent", length = 10)
    private int Id_answerd ;

    @Column(name= "answerdQuestionStuden", length = 100)
    private String answerdQuestionStuden ;

    @OneToOne
    @JoinColumn(name = "Id_Question")
    private Question question;

    @OneToMany(mappedBy = "ResponseStudent")
    private List<Result> result;

    public responseStudent() {
    }

    public responseStudent(int id_answerd, String answerdQuestionStuden, Question question, List<Result> result) {
        Id_answerd = id_answerd;
        this.answerdQuestionStuden = answerdQuestionStuden;
        this.question = question;
        this.result = result;
    }

    public int getId_answerd() {
        return Id_answerd;
    }

    public void setId_answerd(int id_answerd) {
        Id_answerd = id_answerd;
    }

    public String getAnswerdQuestionStuden() {
        return answerdQuestionStuden;
    }

    public void setAnswerdQuestionStuden(String answerdQuestionStuden) {
        this.answerdQuestionStuden = answerdQuestionStuden;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

   
}
