package com.sena.school_library.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.school_library.DTO.requestRegisterUser;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.service.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    /*
     * Get: Obtener datos o constutar
     * Post: Crar un registro
     * Put: Actualizacion total
     * Patch : Actualizacion parcial
     * Delete : Eliminar
     */

    @Autowired
    private UserServices  UserService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllUser(){
        var ListUser = UserService.findAllUsers();
        return new ResponseEntity<Object>(ListUser,HttpStatus.OK);
    }

    @GetMapping("/filter/{name}")
    public ResponseEntity<Object> filterFormName(@PathVariable String name){
        var ListUser = UserService.filterFormName(name);
        return new ResponseEntity<Object>(ListUser,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdEntity(@PathVariable int id){
        var User = UserService.findByIdUser(id);
        return new ResponseEntity<>(User, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        responseDTO response = UserService.delete(id);
        return new ResponseEntity<>(response,response.getStatus());
    }
     
    @PostMapping("/")
    public String postMethodName(@RequestBody requestRegisterUser user){
        UserService.save(user);
       return "Register Ok";
    }

    @PutMapping("/")
    public String update (@RequestBody requestRegisterUser user){
        UserService.update(user);
        return "actualizo ok";
    }
}
