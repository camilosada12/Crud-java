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

import com.sena.school_library.DTO.requestRegisterBookAvaliable;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.service.BooksAvaliableServices;

@RestController
@RequestMapping("api/v1/booksAvaliable")
public class BooksAvaliableController {
    /*
     * Get: Obtener datos o constutar
     * Post: Crar un registro
     * Put: Actualizacion total
     * Patch : Actualizacion parcial
     * Delete : Eliminar
     */

    @Autowired
    private BooksAvaliableServices bookAvaliablServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAllBooks() {
        var ListBooksAvaliable = bookAvaliablServices.findAllBooksAvaliable();
        return new ResponseEntity<Object>(ListBooksAvaliable, HttpStatus.OK);
    }

    // @GetMapping("/filter/{name}")
    // public ResponseEntity<Object> filterBookAvaliables(@PathVariable String name){
    //     var ListBooksAvaliable = bookAvaliablServices.filterBookAvaliables(name);
    //     return new ResponseEntity<Object>(ListBooksAvaliable,HttpStatus.OK);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdBooks(@PathVariable int id) {
        var booksAvaliable = bookAvaliablServices.findByIdBooksAvaliable(id);
        return new ResponseEntity<>(booksAvaliable, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBooksAvaliable(@PathVariable int id) {
        responseDTO response = bookAvaliablServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/")
    public String postMethodName(@RequestBody requestRegisterBookAvaliable booksAvaliable) {
        bookAvaliablServices.save(booksAvaliable);
        return "Registro de un BookAvaliable exitoso";
    }

    @PutMapping("/")
    public String update(@RequestBody requestRegisterBookAvaliable booksAvaliable) {
        bookAvaliablServices.update(booksAvaliable);
        return "actualizo de un BookAvaliable  exitoso ";
    }
}
