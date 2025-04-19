package com.sena.school_library.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity(name = "BookAvaliable")
public class BookAvaliable {
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_BookAvaliable", length = 10)
    private int Id_BookAvaliable ;

    @Column(name= "period", length = 100)
    private String period ;

    @Column(name= "Statu")
    private boolean Statu ;

    @ManyToOne
    @JoinColumn(name= "Id_Books")
    private Books books ; 

    @OneToMany(mappedBy = "bookAvaliable")
    private List<Question> question;    

    public BookAvaliable() {
    }

    public BookAvaliable(int id_BookAvaliable, String period, boolean statu, Books books, List<Question> question) {
        Id_BookAvaliable = id_BookAvaliable;
        this.period = period;
        Statu = statu;
        this.books = books;
        this.question = question;
    }

    public int getId_BookAvaliable() {
        return Id_BookAvaliable;
    }

    public void setId_BookAvaliable(int id_BookAvaliable) {
        Id_BookAvaliable = id_BookAvaliable;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public boolean getStatu() {
        return Statu;
    }

    public void setStatu(boolean statu) {
        Statu = statu;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

   
}
