package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "answerd")
public class answerd {
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_answerd", length = 10)
    private int Id_answerd ;

    @Column(name= "answerdQuestion", length = 10)
    private String answerdQuestion ;

    public answerd(int id_answerd, String answerdQuestion) {
        Id_answerd = id_answerd;
        this.answerdQuestion = answerdQuestion;
    }

    public int getId_answerd() {
        return Id_answerd;
    }

    public void setId_answerd(int id_answerd) {
        Id_answerd = id_answerd;
    }

    public String getAnswerdQuestion() {
        return answerdQuestion;
    }

    public void setAnswerdQuestion(String answerdQuestion) {
        this.answerdQuestion = answerdQuestion;
    }


}
