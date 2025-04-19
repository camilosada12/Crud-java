package com.sena.school_library.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.school_library.model.Question;

/*
 * JpaRepository<entidad, tipo de dato de PK>
 */

 @Repository
public interface IQuestions extends JpaRepository<Question, Integer> {
     /*
     * JpaRepository incluye
     * SELECT
     * UPDATE
     * INSERT
     * DELETE
     * por defecto
     */

}
