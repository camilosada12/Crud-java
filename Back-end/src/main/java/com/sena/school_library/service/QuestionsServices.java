package com.sena.school_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.school_library.DTO.requestRegisterQuestions;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.interfaces.IQuestions;
import com.sena.school_library.model.BookAvaliable;
import com.sena.school_library.model.Question;
import com.sena.school_library.model.answerd;

@Service
public class QuestionsServices {
    /*
     * @Autowired = Incluye la conexion de la interface
     */

    @Autowired
    private IQuestions questionsData;

    public List<requestRegisterQuestions> findAllQuestions() {
        List<Question> entities = questionsData.findAll();
        List<requestRegisterQuestions> dtos = new ArrayList<>();
    
        for (Question question : entities) {
            requestRegisterQuestions dto = new requestRegisterQuestions();
            dto.setId_questions(question.getId_Question());
            dto.setQuestions_book(question.getQuestionBooks());
            dto.setAnswered(question.getAnswered());
            dto.setId_book_avaliable(question.getBookAvaliable().getId_BookAvaliable());
    
            dtos.add(dto);
        }
        return dtos;
    }

    public Optional<requestRegisterQuestions> findByIdQuestion(int id) {
        Optional<Question> optionalEntity = questionsData.findById(id);
    
        if (optionalEntity.isPresent()) {
            Question question = optionalEntity.get();
            requestRegisterQuestions dto = new requestRegisterQuestions();
            dto.setId_questions(question.getId_Question());
            dto.setQuestions_book(question.getQuestionBooks());
            dto.setAnswered(question.getAnswered());
            dto.setId_book_avaliable(question.getBookAvaliable().getId_BookAvaliable());
    
            return Optional.of(dto);
        }
    
        return Optional.empty();
    }

    public void save(requestRegisterQuestions Question) {
        questionsData.save(convertRegisterQuestions(Question));
    }

    // Este método ya no se usa directamente pero lo dejo por compatibilidad
    public Question convertRegisterQuestions(requestRegisterQuestions Question) {
        BookAvaliable bookAvaliable = new BookAvaliable();
        Question question = new Question();
        question.setQuestionBooks(Question.getQuestions_book());
        question.setAnswered(Question.getAnswered());
        
        bookAvaliable.setId_BookAvaliable(Question.getId_book_avaliable());
        question.setBookAvaliable(bookAvaliable);
        bookAvaliable.setId_BookAvaliable(Question.getId_book_avaliable());
    
        return new Question(
                0,
                Question.getQuestions_book(),
                Question.getAnswered(),
                bookAvaliable,
                null,
                null
        );
    }

    public void update(requestRegisterQuestions QuestionUpdate) {
        Optional<Question> QuestionOptional = questionsData.findById(QuestionUpdate.getId_questions());

        if (QuestionOptional.isPresent()) {
            Question question = QuestionOptional.get();
            question.setQuestionBooks(QuestionUpdate.getQuestions_book());

            BookAvaliable bookAvaliable = new BookAvaliable();
            bookAvaliable.setId_BookAvaliable(QuestionUpdate.getId_book_avaliable());
            question.setBookAvaliable(bookAvaliable);

            // Actualizar o crear la respuesta
            if (QuestionUpdate.getAnswered() != null && !QuestionUpdate.getAnswered().isEmpty()) {
                answerd answer;

                // Si ya existe una respuesta, la actualizamos, si no, creamos una nueva
                if (question.getAnswerds() != null) {
                    answer = question.getAnswerds();
                } else {
                    answer = new answerd();
                    answer.setQuestion(question);
                }

                // Limitamos el tamaño de la respuesta si es necesario
                String answerText = QuestionUpdate.getAnswered();

                answer.setAnswerdQuestion(answerText);
                question.setAnswerds(answer);
            }

            // Guardar cambios
            questionsData.save(question);
        }
    }

    public responseDTO delete(int id) {
        Optional<Question> questionEntity = questionsData.findById(id);
        responseDTO response = new responseDTO();

        if (questionEntity.isPresent()) {
            questionsData.delete(questionEntity.get());
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