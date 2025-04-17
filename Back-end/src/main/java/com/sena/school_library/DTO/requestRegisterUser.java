package com.sena.school_library.DTO;

public class requestRegisterUser {

    private int id_user;
    private String name;
    private String lastname;
    private String user;
    private String mail;
    private String password;
    private String rol;

    public requestRegisterUser(){
    }

    public requestRegisterUser(int id_user, String name, String lastname, String user, String mail, String password,
            String rol) {
        this.id_user = id_user;
        this.name = name;
        this.lastname = lastname;
        this.user = user;
        this.mail = mail;
        this.password = password;
        this.rol = rol;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
    
}
