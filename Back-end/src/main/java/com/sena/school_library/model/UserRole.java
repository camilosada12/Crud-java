package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "UserRole")
public class UserRole {
    //Id= PK
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_UserRole", length = 10)
    private int Id_UserRole;
    
    //Referencia a otras clases
    @ManyToOne
    @JoinColumn(name= "Id_User")
    private User user;

    @ManyToOne
    @JoinColumn(name= "Id_Rol")
    private Rol rol;

    // Constructor vacío requerido por JPA
    public UserRole() {
    }

    public UserRole(int id_UserRole, User user, Rol rol) {
        Id_UserRole = id_UserRole;
        this.user = user;
        this.rol = rol;
    }

    public int getId_UserRole() {
        return Id_UserRole;
    }

    public void setId_UserRole(int id_UserRole) {
        Id_UserRole = id_UserRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }  
}