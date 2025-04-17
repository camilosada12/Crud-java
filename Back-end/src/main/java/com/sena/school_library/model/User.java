package com.sena.school_library.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/*
 * anotacion bean, indica que es una entidad
 */

@Entity(name = "user_person")
public class User {

    //Id= PK
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_User", length = 10)
    private int Id_User;

    @Column(name= "Name")
    private String Name;

    @Column(name= "LastName")
    private String LastName;

    @Column(name= "User")
    private String User;

    @Column(name= "password")
    private String password;

    @Column(name= "Mail")
    private String Mail;

    @Column(name= "Rol")
    private String Rol;

    

   public User() {
    }

    // Constructor que no incluye colecciones
    public User(int id_User, String name, String lastName, String user, String password, String mail, String rol) {
        this(id_User, name, lastName, user, password, mail, rol, null, null);  
    }

// Constructor completo si lo necesitas
    public User(int id_User, String name, String lastName, String user, String password, String mail, String rol, 
                List<Books> books, List<UserRole> userRoles) {
        Id_User = id_User;
        Name = name;
        LastName = lastName;
        User = user;
        this.password = password;
        Mail = mail;
        Rol = rol;
        this.books = books;
        this.userRoles = userRoles;
    }

    @OneToMany(mappedBy = "user") 
    private List<Books> books;

    // Por esto:
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int id_User) {
        Id_User = id_User;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

   
}
