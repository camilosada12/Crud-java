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

import com.sena.school_library.DTO.requestRegisterRol;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.service.RolServices;

@RestController
@RequestMapping("api/v1/rol")
public class RolController {
    /*
     * Get: Obtener datos o constutar
     * Post: Crar un registro
     * Put: Actualizacion total
     * Patch : Actualizacion parcial
     * Delete : Eliminar
     */

     @Autowired
     private RolServices rolServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAllRol(){
        var ListRol = rolServices.findAllRol();
        return new ResponseEntity<Object>(ListRol,HttpStatus.OK);
    }

    @GetMapping("/filter/{name}")
    public ResponseEntity<Object> filterTypeRol(@PathVariable String name){
        var ListRol = rolServices.filterType(name);
        return new ResponseEntity<Object>(ListRol,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdRol(@PathVariable int id){
        var rol = rolServices.findByIdRol(id);
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRol(@PathVariable int id){
        responseDTO response = rolServices.delete(id);
        return new ResponseEntity<>(response,response.getStatus());
    }
     
    @PostMapping("/")
    public String postMethodName(@RequestBody requestRegisterRol rol){
        rolServices.save(rol);
       return "Registro de rol exitoso";
    }

    @PutMapping("/")
    public String update (@RequestBody requestRegisterRol rol){
        rolServices.update(rol);
        return "actualizo de rol exitoso ";
    }

}
