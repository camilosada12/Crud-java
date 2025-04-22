package com.sena.school_library.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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

    @OneToMany(mappedBy= "subjects")
    private List<Books> books;

    public Subjects() {
    }

    public Subjects(int id_Subjects, String subjectsClasses, List<Books> books) {
        Id_Subjects = id_Subjects;
        SubjectsClasses = subjectsClasses;
        this.books = books;
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

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    } 
}