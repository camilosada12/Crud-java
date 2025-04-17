package com.sena.school_library.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.school_library.model.User;
/*
 * JpaRepository<entidad, tipo de dato de PK>
 */
@Repository
public interface IUser extends JpaRepository<User,Integer>{
    /*
     * JpaRepository incluye
     * SELECT
     * UPDATE
     * INSERT
     * DELETE
     * por defecto
     */
}

