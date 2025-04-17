package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Books")
public class Books {
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_Books", length = 10)
    private int Id_Books ;

    @Column(name= "Book", length = 100)
    private int Book;

    @Column(name= "Content", length = 100)
    private int Content;

    @Column(name= "pages", length = 50)
    private int pages;

    @Column(name= "author")
    private String author;

    public Books(int id_Books, int book, int content, int pages, String author) {
        Id_Books = id_Books;
        Book = book;
        Content = content;
        this.pages = pages;
        this.author = author;
    }

    public int getId_Books() {
        return Id_Books;
    }

    public void setId_Books(int id_Books) {
        Id_Books = id_Books;
    }

    public int getBook() {
        return Book;
    }

    public void setBook(int book) {
        Book = book;
    }

    public int getContent() {
        return Content;
    }

    public void setContent(int content) {
        Content = content;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    
}
