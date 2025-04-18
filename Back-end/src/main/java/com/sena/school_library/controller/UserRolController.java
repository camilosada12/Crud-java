package com.sena.school_library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.school_library.DTO.requestRegisterUserRol;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.service.UserRolServices;

@RestController
@RequestMapping("api/v1/userRol")
public class UserRolController {
    /*
     * Get: Obtener datos o constutar
     * Post: Crar un registro
     * Put: Actualizacion total
     * Patch : Actualizacion parcial
     * Delete : Eliminar
     */

    @Autowired
    private UserRolServices userRolServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAllRol() {
        var ListuserRol = userRolServices.findAllUserRol();
        return new ResponseEntity<Object>(ListuserRol, HttpStatus.OK);
    }

    // @GetMapping("/filter/{name}")
    // public ResponseEntity<Object> filterTypeRol(@PathVariable String name){
    // var ListRol = rolServices.filterType(name);
    // return new ResponseEntity<Object>(ListRol,HttpStatus.OK);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdRol(@PathVariable int id) {
        var UserRol = userRolServices.findByIdUserRol(id);
        return new ResponseEntity<>(UserRol, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRol(@PathVariable int id) {
        responseDTO response = userRolServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/")
    public String postMethodName(@RequestBody requestRegisterUserRol UserRol) {
        userRolServices.save(UserRol);
        return "Registro de UserRol exitoso";
    }

    @PutMapping("/")
    public String update(@RequestBody requestRegisterUserRol UserRol) {
        userRolServices.update(UserRol);
        return "actualizo de UserRol exitoso ";
    }
}
