package com.sena.school_library.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.school_library.model.answerd;

/*
 * JpaRepository<entidad, tipo de dato de PK>
 */
@Repository
public interface IAnswerd  extends JpaRepository<answerd, Integer>{
     /*
     * JpaRepository incluye
     * SELECT
     * UPDATE
     * INSERT
     * DELETE
     * por defecto
     */
}
