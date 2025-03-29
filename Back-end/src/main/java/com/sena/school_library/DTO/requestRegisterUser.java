package com.sena.school_library.DTO;

public class requestRegisterUser {

    private int Id_User;
    private String Name;
    private String LastName;
    private String User;
    private String Mail;
    private String password;
    private String Rol;
    public requestRegisterUser(){
    }

    public requestRegisterUser(int id_User, String name, String lastName, String user, String mail, String password,
            String rol) {
        Id_User = id_User;
        Name = name;
        LastName = lastName;
        User = user;
        Mail = mail;
        this.password = password;
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
    public String getMail() {
        return Mail;
    }
    public void setMail(String mail) {
        Mail = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRol() {
        return Rol;
    }
    public void setRol(String rol) {
        Rol = rol;
    }
   
    
}
