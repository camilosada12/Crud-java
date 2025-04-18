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

import com.sena.school_library.DTO.requestRegisterBooks;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.service.BooksServices;

@RestController
@RequestMapping("api/v1/books")
public class BooksController {
    /*
     * Get: Obtener datos o constutar
     * Post: Crar un registro
     * Put: Actualizacion total
     * Patch : Actualizacion parcial
     * Delete : Eliminar
     */

    @Autowired
    private BooksServices booksServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAllBooks() {
        var ListBooks = booksServices.findAllBooks();
        return new ResponseEntity<Object>(ListBooks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdBooks(@PathVariable int id) {
        var books = booksServices.findByIdBooks(id);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBooks(@PathVariable int id) {
        responseDTO response = booksServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/")
    public String postMethodName(@RequestBody requestRegisterBooks books) {
        booksServices.save(books);
        return "Registro de un Book exitoso";
    }

    @PutMapping("/")
    public String update(@RequestBody requestRegisterBooks books) {
        booksServices.update(books);
        return "actualizo de un books  exitoso ";
    }
}
