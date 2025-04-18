package com.sena.school_library.DTO;

public class requestRegisterUserRol {
    private int id_user_rol;
    private int id_user;
    private String user_name;
    private int id_rol;
    private String rol_name;
    
    public requestRegisterUserRol() {
    }

    public requestRegisterUserRol(int id_user_rol, int id_user, String user_name, int id_rol, String rol_name) {
        this.id_user_rol = id_user_rol;
        this.id_user = id_user;
        this.user_name = user_name;
        this.id_rol = id_rol;
        this.rol_name = rol_name;
    }

    public int getId_user_rol() {
        return id_user_rol;
    }

    public void setId_user_rol(int id_user_rol) {
        this.id_user_rol = id_user_rol;
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

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getRol_name() {
        return rol_name;
    }

    public void setRol_name(String rol_name) {
        this.rol_name = rol_name;
    }

   
}


