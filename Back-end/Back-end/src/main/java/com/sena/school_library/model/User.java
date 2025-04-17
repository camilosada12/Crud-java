package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

/*
 * anotacion bean, indica que es una entidad
 */

@Entity(name = "User")
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

    @OneToMany
    @JoinColumn(name= "Books", referencedColumnName = "Id_Books")
    private Books Books;

    @OneToMany
    @JoinColumn(name= "UserRoles", referencedColumnName = "Id_UserRole")
    private UserRole UserRoles;

    public User(int id_User, String name, String lastName, String user, String password, String mail, String rol) {
        Id_User = id_User;
        Name = name;
        LastName = lastName;
        User = user;
        this.password = password;
        Mail = mail;
        Rol = rol;
    }

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
}
