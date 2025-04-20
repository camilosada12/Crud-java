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

import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.DTO.requestRegisterResult;
import com.sena.school_library.service.ResultServices;

@RestController
@RequestMapping("api/v1/resultado")
public class ResultController {
    /*
     * Get: Obtener datos o constutar
     * Post: Crar un registro
     * Put: Actualizacion total
     * Patch : Actualizacion parcial
     * Delete : Eliminar
     */

    @Autowired
    private ResultServices resultService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllUser() {
        var ListResult = resultService.findAllResult();
        return new ResponseEntity<Object>(ListResult, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdEntity(@PathVariable int id) {
        var result = resultService.findByIdResult(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        responseDTO response = resultService.delete(id);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PostMapping("/")
    public String postMethodName(@RequestBody requestRegisterResult result){
        resultService.save(result);
       return "Register Ok";
    }

     @PutMapping("/")
    public String update (@RequestBody requestRegisterResult result){
        resultService.update(result);
        return "actualizo ok";
    }
}
