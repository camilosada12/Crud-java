package com.sena.school_library.DTO;

public class requestRegisterAnswerd {

    private int id_answerd;
    private String answerd_questions;
    private int id_question;
    
    public requestRegisterAnswerd() {
    }

    public requestRegisterAnswerd(int id_answerd, String answerd_questions, int id_question) {
        this.id_answerd = id_answerd;
        this.answerd_questions = answerd_questions;
        this.id_question = id_question;
    }

    public int getId_answerd() {
        return id_answerd;
    }

    public void setId_answerd(int id_answerd) {
        this.id_answerd = id_answerd;
    }

    public String getAnswerd_questions() {
        return answerd_questions;
    }

    public void setAnswerd_questions(String answerd_questions) {
        this.answerd_questions = answerd_questions;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    

    

}
