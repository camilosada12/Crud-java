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

import com.sena.school_library.DTO.requestRegisterAnswerd;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.service.AnswerdServices;

@RestController
@RequestMapping("api/v1/answerd")
public class answerdController {
     /*
     * Get: Obtener datos o constutar
     * Post: Crar un registro
     * Put: Actualizacion total
     * Patch : Actualizacion parcial
     * Delete : Eliminar
     */

     @Autowired
     private AnswerdServices answerdServices;

     @GetMapping("/")
    public ResponseEntity<Object> findAllAnswerd() {
        var ListAnswerd = answerdServices.findAllAnswerds();
        return new ResponseEntity<Object>(ListAnswerd, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdAnswerd(@PathVariable int id) {
        var answerd = answerdServices.findByIdUserRol(id);
        return new ResponseEntity<>(answerd, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRol(@PathVariable int id) {
        responseDTO response = answerdServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/")
    public String postMethodName(@RequestBody requestRegisterAnswerd answerd) {
        answerdServices.save(answerd);
        return "Registro de answerd exitoso";
    }

    @PutMapping("/")
    public String update(@RequestBody requestRegisterAnswerd answerd) {
        answerdServices.update(answerd);
        return "actualizo de answerd exitoso ";
    }

}
