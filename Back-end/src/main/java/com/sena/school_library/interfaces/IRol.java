package com.sena.school_library.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.school_library.model.Rol;

/*
 * JpaRepository<entidad, tipo de dato de PK>
 */

@Repository
public interface IRol extends JpaRepository<Rol, Integer> {
    /*
     * JpaRepository incluye
     * SELECT
     * UPDATE
     * INSERT
     * DELETE
     * por defecto
     */
    
    @Query("SELECT r FROM Rol r WHERE r.RoleType LIKE %?1%")
     List<Rol> filterTypeRol(String filter);
}

