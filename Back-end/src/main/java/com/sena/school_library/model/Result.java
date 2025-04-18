package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Result")
public class Result {
      //Id= PK
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_Result", length = 10)
    private int Id_Result;

    @Column(name= "Note")
    private double Note;

    @ManyToOne
    @JoinColumn(name = "Id_responseStudent")
    private responseStudent ResponseStudent;

    @ManyToOne
    @JoinColumn(name = "Id_answerd")
    private answerd answerd;

    public Result() {
    }

    public Result(int id_Result, double note, responseStudent responseStudent,
            com.sena.school_library.model.answerd answerd) {
        Id_Result = id_Result;
        Note = note;
        ResponseStudent = responseStudent;
        this.answerd = answerd;
    }

    public int getId_Result() {
        return Id_Result;
    }

    public void setId_Result(int id_Result) {
        Id_Result = id_Result;
    }

    public double getNote() {
        return Note;
    }

    public void setNote(double note) {
        Note = note;
    }

    public responseStudent getResponseStudent() {
        return ResponseStudent;
    }

    public void setResponseStudent(responseStudent responseStudent) {
        ResponseStudent = responseStudent;
    }

    public answerd getAnswerd() {
        return answerd;
    }

    public void setAnswerd(answerd answerd) {
        this.answerd = answerd;
    }

   
}
