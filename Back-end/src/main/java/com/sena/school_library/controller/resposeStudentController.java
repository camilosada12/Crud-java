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

import com.sena.school_library.DTO.requestRegisterresponseStudent;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.service.responseStundetServices;

@RestController
@RequestMapping("api/v1/resposeStudent")
public class resposeStudentController {
    /*
     * Get: Obtener datos o constutar
     * Post: Crar un registro
     * Put: Actualizacion total
     * Patch : Actualizacion parcial
     * Delete : Eliminar
     */

    @Autowired
    private responseStundetServices responseStundetServicesServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAllRol() {
        var ListResposetStudent = responseStundetServicesServices.findAllResponseStudent();
        return new ResponseEntity<Object>(ListResposetStudent, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdRol(@PathVariable int id) {
        var resultStudent = responseStundetServicesServices.findByIdResponseStudent(id);
        return new ResponseEntity<>(resultStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRol(@PathVariable int id) {
        responseDTO response = responseStundetServicesServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/")
    public String postMethodName(@RequestBody requestRegisterresponseStudent resposetStudent) {
        responseStundetServicesServices.save(resposetStudent);
        return "Registro de resposetStudent exitoso";
    }

     @PutMapping("/")
    public String update(@RequestBody requestRegisterresponseStudent responseStudent) {
        responseStundetServicesServices.update(responseStudent);
        return "actualizo de responseStudent exitoso ";
    }
}
