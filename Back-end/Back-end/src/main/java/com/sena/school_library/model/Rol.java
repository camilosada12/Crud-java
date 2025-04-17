package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 * anotacion bean, indica que es una entidad
 */

@Entity(name = "Rol")
public class Rol {

    //Id= PK
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_Rol", length = 10)
    private int Id_Rol;

    @Column(name= "RoleType")
    private String RoleType;

    public Rol(int id_Rol, String roleType) {
        Id_Rol = id_Rol;
        RoleType = roleType;
    }

    public int getId_Rol() {
        return Id_Rol;
    }

    public void setId_Rol(int id_Rol) {
        Id_Rol = id_Rol;
    }

    public String getRoleType() {
        return RoleType;
    }

    public void setRoleType(String roleType) {
        RoleType = roleType;
    }
}
