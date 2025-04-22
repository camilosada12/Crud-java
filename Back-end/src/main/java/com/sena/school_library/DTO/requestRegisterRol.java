package com.sena.school_library.DTO;

public class requestRegisterRol {

    private int id_rol;
    private String roletype;
    
    public requestRegisterRol() {
    }

    public requestRegisterRol(int id_rol, String roletype) {
        this.id_rol = id_rol;
        this.roletype = roletype;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getRoletype() {
        return roletype;
    }

    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }

   
}
