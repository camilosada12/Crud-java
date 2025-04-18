package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

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

    @ManyToOne
    @JoinColumn(name = "Id_BookAvaliable")
    private BookAvaliable bookAvaliable;

    @OneToOne(mappedBy = "question")
    private responseStudent responseStudent;

    @OneToOne(mappedBy = "question")
    private answerd answerds;

    public Question() {
    }

    public Question(int id_Question, String questionBooks, BookAvaliable bookAvaliable,
            com.sena.school_library.model.responseStudent responseStudent, answerd answerds) {
        Id_Question = id_Question;
        this.questionBooks = questionBooks;
        this.bookAvaliable = bookAvaliable;
        this.responseStudent = responseStudent;
        this.answerds = answerds;
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

    public BookAvaliable getBookAvaliable() {
        return bookAvaliable;
    }

    public void setBookAvaliable(BookAvaliable bookAvaliable) {
        this.bookAvaliable = bookAvaliable;
    }

    public responseStudent getResponseStudent() {
        return responseStudent;
    }

    public void setResponseStudent(responseStudent responseStudent) {
        this.responseStudent = responseStudent;
    }

    public answerd getAnswerds() {
        return answerds;
    }

    public void setAnswerds(answerd answerds) {
        this.answerds = answerds;
    }

    
}
