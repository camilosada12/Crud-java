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

import com.sena.school_library.DTO.requestRegisterQuestions;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.service.QuestionsServices;

@RestController
@RequestMapping("api/v1/question")
public class QuestionController {
    /*
     * Get: Obtener datos o constutar
     * Post: Crar un registro
     * Put: Actualizacion total
     * Patch : Actualizacion parcial
     * Delete : Eliminar
     */

     @Autowired
    private QuestionsServices questionServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAllBooks() {
        var ListQuestion = questionServices.findAllQuestions();
        return new ResponseEntity<Object>(ListQuestion, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdBooks(@PathVariable int id) {
        var question = questionServices.findByIdQuestion(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable int id) {
        responseDTO response = questionServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/")
    public String postMethodName(@RequestBody requestRegisterQuestions questions) {
        questionServices.save(questions);
        return "Registro de un question exitoso";
    }

    @PutMapping("/")
    public String update(@RequestBody requestRegisterQuestions questions) {
        questionServices.update(questions);
        return "actualizo de un questions  exitoso ";
    }
}
