package com.sena.school_library.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.school_library.model.UserRole;

/*
 * JpaRepository<entidad, tipo de dato de PK>
 */
@Repository
public interface IUserRol extends JpaRepository<UserRole, Integer> {

    /*
     * JpaRepository incluye
     * SELECT
     * UPDATE
     * INSERT
     * DELETE
     * por defecto
     */
}
