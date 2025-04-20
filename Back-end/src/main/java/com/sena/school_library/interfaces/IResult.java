package com.sena.school_library.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.school_library.model.Resultado;

/*
 * JpaRepository<entidad, tipo de dato de PK>
 */
@Repository
public interface IResult extends JpaRepository<Resultado,Integer> {
     /*
     * JpaRepository incluye
     * SELECT
     * UPDATE
     * INSERT
     * DELETE
     * por defecto
     */
}
