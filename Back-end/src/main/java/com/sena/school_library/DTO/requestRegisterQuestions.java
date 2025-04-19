package com.sena.school_library.DTO;

public class requestRegisterQuestions {

    private int id_questions;
    private String questions_book;
    private String answered;
    private int id_book_avaliable;
    
    public requestRegisterQuestions() {
    }

    public requestRegisterQuestions(int id_questions, String questions_book, String answered, int id_book_avaliable) {
        this.id_questions = id_questions;
        this.questions_book = questions_book;
        this.answered = answered;
        this.id_book_avaliable = id_book_avaliable;
    }

    public int getId_questions() {
        return id_questions;
    }

    public void setId_questions(int id_questions) {
        this.id_questions = id_questions;
    }

    public String getQuestions_book() {
        return questions_book;
    }

    public void setQuestions_book(String questions_book) {
        this.questions_book = questions_book;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    public int getId_book_avaliable() {
        return id_book_avaliable;
    }

    public void setId_book_avaliable(int id_book_avaliable) {
        this.id_book_avaliable = id_book_avaliable;
    }
}
