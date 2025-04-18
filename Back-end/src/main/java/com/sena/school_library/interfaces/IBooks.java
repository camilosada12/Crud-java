package com.sena.school_library.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.school_library.model.Books;

/*
 * JpaRepository<entidad, tipo de dato de PK>
 */

@Repository
public interface IBooks extends JpaRepository<Books, Integer> {
    /*
     * JpaRepository incluye
     * SELECT
     * UPDATE
     * INSERT
     * DELETE
     * por defecto
     */
}
