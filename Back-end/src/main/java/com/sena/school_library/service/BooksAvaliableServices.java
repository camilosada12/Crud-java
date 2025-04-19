package com.sena.school_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.school_library.DTO.requestRegisterBookAvaliable;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.interfaces.IBookAvaliable;
import com.sena.school_library.model.BookAvaliable;
import com.sena.school_library.model.Books;

@Service
public class BooksAvaliableServices {
    /*
     * @Autowired = Incluye la conexion de la interface
     */

    @Autowired
    private IBookAvaliable booksAvaliableData;

    public List<requestRegisterBookAvaliable> findAllBooksAvaliable() {
        List<BookAvaliable> entities = booksAvaliableData.findAll();
        List<requestRegisterBookAvaliable> dtos = new ArrayList<>();

        for (BookAvaliable booksAvaliable : entities) {
            requestRegisterBookAvaliable dto = new requestRegisterBookAvaliable();
            dto.setId_book_avaliable(booksAvaliable.getId_BookAvaliable());
            dto.setPeroid(booksAvaliable.getPeriod());
            dto.setState(booksAvaliable.getStatu());
            dto.setId_books(booksAvaliable.getBooks().getId_Books());
            dto.setBooks_name(booksAvaliable.getBooks().getBook()); // Asegúrate de que getBook() existe

            dtos.add(dto);
        }
        return dtos;
    }

    // public List<BookAvaliable> filterBookAvaliables(String filter){
    //     return booksAvaliableData.filterBookAvaliables(filter);
    // }

    public Optional<requestRegisterBookAvaliable> findByIdBooksAvaliable(int id) {
        Optional<BookAvaliable> optionalEntity = booksAvaliableData.findById(id);

        if (optionalEntity.isPresent()) {
            BookAvaliable bookAvaliable = optionalEntity.get();
            requestRegisterBookAvaliable dto = new requestRegisterBookAvaliable();
            dto.setId_book_avaliable(bookAvaliable.getId_BookAvaliable());
            dto.setPeroid(bookAvaliable.getPeriod());
            dto.setState(bookAvaliable.getStatu());
            dto.setId_books(bookAvaliable.getBooks().getId_Books());
            dto.setBooks_name(bookAvaliable.getBooks().getBook());

            return Optional.of(dto);
        }

        return Optional.empty();
    }

    public void save(requestRegisterBookAvaliable booksAvaliable) {
        booksAvaliableData.save(convertRegisterBooksAvaliable(booksAvaliable));
    }

    public BookAvaliable convertRegisterBooksAvaliable(requestRegisterBookAvaliable BookAvaliable) {
        Books booksAvaliable = new Books();
        booksAvaliable.setId_Books(BookAvaliable.getId_books());
        booksAvaliable.setBook(BookAvaliable.getBooks_name());
        return new BookAvaliable(
            0,
            BookAvaliable.getPeroid(),
            BookAvaliable.getState(),  
            booksAvaliable, 
            null // Lista de preguntas (vacía o null por ahora)
        );
    }

    public void update(requestRegisterBookAvaliable BooksAvaliableUpdate) {
        Optional<BookAvaliable> booksAvaliableOptional = booksAvaliableData.findById(BooksAvaliableUpdate.getId_book_avaliable());
    
        if (booksAvaliableOptional.isPresent()) {
            BookAvaliable books_Avaliable = booksAvaliableOptional.get();
    
            // Crear y setear el objeto Books
            Books books = new Books();
            books.setId_Books(BooksAvaliableUpdate.getId_books());
            books.setBook(BooksAvaliableUpdate.getBooks_name());
    
            // Asociar el libro al BookAvaliable
            books_Avaliable.setBooks(books);
    
            // Actualizar otros campos
            books_Avaliable.setPeriod(BooksAvaliableUpdate.getPeroid());
            books_Avaliable.setStatu(BooksAvaliableUpdate.getState());
    
            // Guardar los cambios
            booksAvaliableData.save(books_Avaliable);
        }
    }

    public responseDTO delete(int id) {
        Optional<BookAvaliable> booksAvaliableEntity = booksAvaliableData.findById(id);
        responseDTO response = new responseDTO();

        if (booksAvaliableEntity.isPresent()) {
            booksAvaliableData.delete(booksAvaliableEntity.get());
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
