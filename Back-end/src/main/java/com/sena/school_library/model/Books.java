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

@Entity(name = "Books")
public class Books {
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_Books", length = 10)
    private int Id_Books ;

    @ManyToOne
    @JoinColumn(name = "user_id") 
    private User user;

    @ManyToOne
    @JoinColumn(name = "Id_Subjects") 
    private Subjects subjects;

    @OneToMany(mappedBy = "books") 
    private List<BookAvaliable> bookAvaliable;

    @Column(name= "Book", length = 100)
    private int Book;

    @Column(name= "Content", length = 100)
    private int Content;

    @Column(name= "pages", length = 50)
    private int pages;

    @Column(name= "author")
    private String author;

    public Books() {
    }

    public Books(int id_Books, User user, Subjects subjects, List<BookAvaliable> bookAvaliable, int book, int content,
            int pages, String author) {
        Id_Books = id_Books;
        this.user = user;
        this.subjects = subjects;
        this.bookAvaliable = bookAvaliable;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    public List<BookAvaliable> getBookAvaliable() {
        return bookAvaliable;
    }

    public void setBookAvaliable(List<BookAvaliable> bookAvaliable) {
        this.bookAvaliable = bookAvaliable;
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
