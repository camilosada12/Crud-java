package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Question")
public class Question {
 //Id= PK
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_Question", length = 10)
    private int Id_Question;

    @Column(name= "questionBooks")
    private String questionBooks;

    public Question(int id_Question, String questionBooks) {
        Id_Question = id_Question;
        this.questionBooks = questionBooks;
    }

    public int getId_Question() {
        return Id_Question;
    }

    public void setId_Question(int id_Question) {
        Id_Question = id_Question;
    }

    public String getQuestionBooks() {
        return questionBooks;
    }

    public void setQuestionBooks(String questionBooks) {
        this.questionBooks = questionBooks;
    }
}
