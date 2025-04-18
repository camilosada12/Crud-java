package com.sena.school_library.DTO;

public class requestRegisterBooks {

    private int id_books;
    private String book;
    private String content;
    private int pages;
    private String author;
    private int id_user;
    private String user_name;
    private int id_subject;
    private String subject_name;
    
    public requestRegisterBooks() {
    }

    public requestRegisterBooks(int id_books, String book, String content, int pages, String author, int id_user,
            String user_name, int id_subject, String subject_name) {
        this.id_books = id_books;
        this.book = book;
        this.content = content;
        this.pages = pages;
        this.author = author;
        this.id_user = id_user;
        this.user_name = user_name;
        this.id_subject = id_subject;
        this.subject_name = subject_name;
    }

    public int getId_books() {
        return id_books;
    }

    public void setId_books(int id_books) {
        this.id_books = id_books;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    
}
