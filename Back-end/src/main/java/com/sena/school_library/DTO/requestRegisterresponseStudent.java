package com.sena.school_library.DTO;

public class requestRegisterresponseStudent {

    private int id_response_student;
    private int id_question;
    private String  student_answer;
    
    public requestRegisterresponseStudent() {
    }

    public requestRegisterresponseStudent(int id_response_student, int id_question, String student_answer) {
        this.id_response_student = id_response_student;
        this.id_question = id_question;
        this.student_answer = student_answer;
    }

    public int getId_response_student() {
        return id_response_student;
    }

    public void setId_response_student(int id_response_student) {
        this.id_response_student = id_response_student;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getStudent_answer() {
        return student_answer;
    }

    public void setStudent_answer(String student_answer) {
        this.student_answer = student_answer;
    }

   
}
