package com.sena.school_library.DTO;

public class requestRegisterBookAvaliable {
    private int id_book_avaliable;
    private String peroid;
    private Boolean state;
    private int id_books;
    private String books_name;
    
    public requestRegisterBookAvaliable() {
    }

    public requestRegisterBookAvaliable(int id_book_avaliable, String peroid, Boolean state, int id_books,
            String books_name) {
        this.id_book_avaliable = id_book_avaliable;
        this.peroid = peroid;
        this.state = state;
        this.id_books = id_books;
        this.books_name = books_name;
    }

    public int getId_book_avaliable() {
        return id_book_avaliable;
    }

    public void setId_book_avaliable(int id_book_avaliable) {
        this.id_book_avaliable = id_book_avaliable;
    }

    public String getPeroid() {
        return peroid;
    }

    public void setPeroid(String peroid) {
        this.peroid = peroid;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public int getId_books() {
        return id_books;
    }

    public void setId_books(int id_books) {
        this.id_books = id_books;
    }

    public String getBooks_name() {
        return books_name;
    }

    public void setBooks_name(String books_name) {
        this.books_name = books_name;
    }

    
}
