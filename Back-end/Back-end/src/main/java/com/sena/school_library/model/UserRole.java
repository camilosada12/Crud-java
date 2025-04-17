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
    @JoinColumn(name= "User", referencedColumnName = "Id_User")
    private User User;

    @ManyToOne
    @JoinColumn(name= "Role",referencedColumnName = "Id_Rol")
    private Rol Rol;

    public UserRole(int id_UserRole, com.sena.school_library.model.User user, com.sena.school_library.model.Rol rol) {
        Id_UserRole = id_UserRole;
        User = user;
        Rol = rol;
    }

    public int getId_UserRole() {
        return Id_UserRole;
    }

    public void setId_UserRole(int id_UserRole) {
        Id_UserRole = id_UserRole;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    public Rol getRol() {
        return Rol;
    }

    public void setRol(Rol rol) {
        Rol = rol;
    }


    
}
