package com.sena.school_library.DTO;

public class requestRegisterRol {

    private int id_rol;
    private String role_type;
    
    public requestRegisterRol() {
    }

    public requestRegisterRol(int id_rol, String role_type) {
        this.id_rol = id_rol;
        this.role_type = role_type;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getRole_type() {
        return role_type;
    }

    public void setRole_type(String role_type) {
        this.role_type = role_type;
    }
}
