package com.sena.school_library.DTO;

public class requestRegisterResult {
    
    private int id_result;
    private double nota;
    private int responseStudentId;     
    
    
    public requestRegisterResult() {
    }


    public requestRegisterResult(int id_result, double nota, int responseStudentId) {
        this.id_result = id_result;
        this.nota = nota;
        this.responseStudentId = responseStudentId;
    }


    public int getId_result() {
        return id_result;
    }


    public void setId_result(int id_result) {
        this.id_result = id_result;
    }


    public double getNota() {
        return nota;
    }


    public void setNota(double nota) {
        this.nota = nota;
    }


    public int getResponseStudentId() {
        return responseStudentId;
    }


    public void setResponseStudentId(int responseStudentId) {
        this.responseStudentId = responseStudentId;
    }

   
}
