package com.sena.school_library.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.school_library.model.Subjects;

/*
 * JpaRepository<entidad, tipo de dato de PK>
 */

@Repository
public interface ISubjects extends JpaRepository<Subjects, Integer> {
    /*
     * JpaRepository incluye
     * SELECT
     * UPDATE
     * INSERT
     * DELETE
     * por defecto
     */

      @Query ("SELECT s FROM Subjects s WHERE s.SubjectsClasses LIKE %?1% ")
     List<Subjects> filterSubjects(String filter);
}
