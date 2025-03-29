package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Subjects")
public class Subjects {

    //Id= PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_Subjects", length = 10)
    private int Id_Subjects ;

    @Column(name= "SubjectsClasses")
    private String SubjectsClasses;

    public Subjects(int id_Subjects, String subjectsClasses) {
        Id_Subjects = id_Subjects;
        SubjectsClasses = subjectsClasses;
    }

    public int getId_Subjects() {
        return Id_Subjects;
    }

    public void setId_Subjects(int id_Subjects) {
        Id_Subjects = id_Subjects;
    }

    public String getSubjectsClasses() {
        return SubjectsClasses;
    }

    public void setSubjectsClasses(String subjectsClasses) {
        SubjectsClasses = subjectsClasses;
    }
}
