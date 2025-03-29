package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "responseStudent")
public class responseStudent {
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_responseStudent", length = 10)
    private int Id_answerd ;

    @Column(name= "answerdQuestionStuden", length = 100)
    private String answerdQuestionStuden ;

    public responseStudent(int id_answerd, String answerdQuestionStuden) {
        Id_answerd = id_answerd;
        this.answerdQuestionStuden = answerdQuestionStuden;
    }

    public int getId_answerd() {
        return Id_answerd;
    }

    public void setId_answerd(int id_answerd) {
        Id_answerd = id_answerd;
    }

    public String getAnswerdQuestionStuden() {
        return answerdQuestionStuden;
    }

    public void setAnswerdQuestionStuden(String answerdQuestionStuden) {
        this.answerdQuestionStuden = answerdQuestionStuden;
    }
}
