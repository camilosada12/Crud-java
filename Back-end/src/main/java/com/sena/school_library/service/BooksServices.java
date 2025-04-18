package com.sena.school_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.school_library.DTO.requestRegisterBooks;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.interfaces.IBooks;
import com.sena.school_library.model.Books;
import com.sena.school_library.model.Subjects;
import com.sena.school_library.model.User;

@Service
public class BooksServices {
    /*
     * @Autowired = Incluye la conexion de la interface
     */

    @Autowired
    private IBooks booksData;

    public List<requestRegisterBooks> findAllBooks() {
        List<Books> entities = booksData.findAll();
        List<requestRegisterBooks> dtos = new ArrayList<>();
    
        for (Books books : entities) {
            requestRegisterBooks dto = new requestRegisterBooks();
            dto.setId_books(books.getId_Books());
            dto.setBook(books.getBook());             // ← Agregado
            dto.setContent(books.getContent());       // ← Agregado
            dto.setPages(books.getPages());           // ← Agregado
            dto.setAuthor(books.getAuthor());         // ← Agregado
            dto.setId_user(books.getUser().getId_User());
            dto.setUser_name(books.getUser().getName());
            dto.setId_subject(books.getSubjects().getId_Subjects());
            dto.setSubject_name(books.getSubjects().getSubjectsClasses());
    
            dtos.add(dto);
        }
        return dtos;
    }
    

    public Optional<requestRegisterBooks> findByIdBooks(int id) {
        Optional<Books> optionalEntity = booksData.findById(id);

        if (optionalEntity.isPresent()) {
            Books books = optionalEntity.get();
            requestRegisterBooks dto = new requestRegisterBooks();
            dto.setId_books(books.getId_Books());
            dto.setBook(books.getBook());             // ← Agregado
            dto.setContent(books.getContent());       // ← Agregado
            dto.setPages(books.getPages());           // ← Agregado
            dto.setAuthor(books.getAuthor());         // ← Agregado
            dto.setId_user(books.getUser().getId_User());
            dto.setUser_name(books.getUser().getName());
            dto.setId_subject(books.getSubjects().getId_Subjects());
            dto.setSubject_name(books.getSubjects().getSubjectsClasses());

            return Optional.of(dto);
        }

        return Optional.empty();
    }

    public void save(requestRegisterBooks books) {
        booksData.save(convertRegisterBooks(books));
    }

    public Books convertRegisterBooks(requestRegisterBooks books) {
        User user = new User();
        user.setId_User(books.getId_user());
        user.setName(books.getUser_name());
        Subjects subject = new Subjects();
        subject.setId_Subjects(books.getId_subject());
        subject.setSubjectsClasses(books.getSubject_name());
        return new Books(
            0,
            user,
            subject,
            null,
            books.getBook(),       
            books.getContent(), 
            books.getPages(),     
            books.getAuthor()   
        );
    }

    public void update(requestRegisterBooks BooksUpdate) {
        Optional<Books> booksOptional = booksData.findById(BooksUpdate.getId_books());
    
        if (booksOptional.isPresent()) {
            Books books = booksOptional.get();
    
            User user = new User();
            user.setId_User(BooksUpdate.getId_user());
            user.setName(BooksUpdate.getUser_name());
    
            Subjects subjects = new Subjects();
            subjects.setId_Subjects(BooksUpdate.getId_subject());
            subjects.setSubjectsClasses(BooksUpdate.getSubject_name());
    
            // Setear los nuevos objetos en el Books
            books.setUser(user);
            books.setSubjects(subjects);
            

            books.setBook(BooksUpdate.getBook());
            books.setContent(BooksUpdate.getContent());
            books.setPages(BooksUpdate.getPages());
            books.setAuthor(BooksUpdate.getAuthor());
    
            // Guardar los cambios
            booksData.save(books);
        }
    }

    public responseDTO delete(int id) {
        Optional<Books> booksEntity = booksData.findById(id);
        responseDTO response = new responseDTO();

        if (booksEntity.isPresent()) {
            booksData.delete(booksEntity.get());
            response.setStatus(HttpStatus.OK);
            response.setMessage("se elimino correctamente");
            return response;
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("El registro no existe");
            return response;
        }
    }

}
