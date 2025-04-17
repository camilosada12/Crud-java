package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    public Result(int id_Result, double note) {
        Id_Result = id_Result;
        Note = note;
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
}
