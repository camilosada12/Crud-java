package com.sena.school_library.DTO;

public class requestRegisterSubjects {
    
    private int id_subject;
    private String subjectClasses;
    
    public requestRegisterSubjects() {
    }

    public requestRegisterSubjects(int id_subject, String subjectClasses) {
        this.id_subject = id_subject;
        this.subjectClasses = subjectClasses;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getSubjectClasses() {
        return subjectClasses;
    }

    public void setSubjectClasses(String subjectClasses) {
        this.subjectClasses = subjectClasses;
    }

    
}
