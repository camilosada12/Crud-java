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

import com.sena.school_library.DTO.requestRegisterSubjects;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.service.subjectsServices;

@RestController
@RequestMapping("api/v1/subjects")
public class subjectsController {
    /*
     * Get: Obtener datos o constutar
     * Post: Crar un registro
     * Put: Actualizacion total
     * Patch : Actualizacion parcial
     * Delete : Eliminar
     */

    @Autowired
    private subjectsServices  SubjectsServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAllUser(){
        var ListSubject = SubjectsServices.findAllSubjects();
        return new ResponseEntity<Object>(ListSubject,HttpStatus.OK);
    }

    @GetMapping("/filter/{name}")
    public ResponseEntity<Object> filterSubjects(@PathVariable String name){
        var ListSubject = SubjectsServices.filterSubject(name);
        return new ResponseEntity<Object>(ListSubject,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdEntity(@PathVariable int id){
        var subjects = SubjectsServices.findByIdSubject(id);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSubject(@PathVariable int id){
        responseDTO response = SubjectsServices.delete(id);
        return new ResponseEntity<>(response,response.getStatus());
    }

     @PostMapping("/")
    public String postMethodName(@RequestBody requestRegisterSubjects Subjects){
        SubjectsServices.save(Subjects);
       return "Register Ok";
    }

    @PutMapping("/")
    public String update (@RequestBody requestRegisterSubjects subjects){
        SubjectsServices.update(subjects);
        return "actualizo ok";
    }
}
