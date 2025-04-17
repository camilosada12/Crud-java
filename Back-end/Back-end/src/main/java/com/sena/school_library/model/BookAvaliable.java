package com.sena.school_library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity(name = "BookAvaliable")
public class BookAvaliable {
    @Id
    //El valor sea autoGenerado e autoIncrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@colum indica que el valor es una columna en la base de datos 
    @Column(name= "Id_BookAvaliable", length = 10)
    private int Id_BookAvaliable ;

    @Column(name= "period", length = 10)
    private int period ;

    @Column(name= "Statu")
    private boolean Statu ;

    public BookAvaliable(int id_BookAvaliable, int period, boolean statu) {
        Id_BookAvaliable = id_BookAvaliable;
        this.period = period;
        Statu = statu;
    }

    public int getId_BookAvaliable() {
        return Id_BookAvaliable;
    }

    public void setId_BookAvaliable(int id_BookAvaliable) {
        Id_BookAvaliable = id_BookAvaliable;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public boolean isStatu() {
        return Statu;
    }

    public void setStatu(boolean statu) {
        Statu = statu;
    }

    
}
