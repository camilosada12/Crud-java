package com.sena.school_library.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

     @Query ("SELECT u FROM user_person u WHERE u.LastName LIKE ?1% ")
     List<User> filterFormName(String filter);
}

