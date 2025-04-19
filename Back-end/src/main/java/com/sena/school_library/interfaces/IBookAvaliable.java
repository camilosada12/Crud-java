package com.sena.school_library.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.school_library.model.BookAvaliable;


public interface IBookAvaliable extends JpaRepository<BookAvaliable, Integer> {
     /*
     * JpaRepository incluye
     * SELECT
     * UPDATE
     * INSERT
     * DELETE
     * por defecto
     */

    //  @Query("SELECT b FROM BookAvaliable  b WHERE b.period  LIKE %?1%")
    //  List<BookAvaliable> filterBookAvaliables(String filter);
}
