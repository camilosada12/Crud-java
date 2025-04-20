package com.sena.school_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.school_library.DTO.requestRegisterAnswerd;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.interfaces.IAnswerd;
import com.sena.school_library.model.Question;
import com.sena.school_library.model.answerd;

@Service
public class AnswerdServices {
     /*
     * @Autowired = Incluye la conexion de la interface
     */
    
    @Autowired
    private IAnswerd answerdData;

    public List<requestRegisterAnswerd> findAllAnswerds(){
    List<answerd> entities = answerdData.findAll();
    List<requestRegisterAnswerd> dtos = new ArrayList<>();

    for (answerd Answerd : entities) {
        requestRegisterAnswerd dto = new requestRegisterAnswerd();
        dto.setId_answerd(Answerd.getId_answerd());
        dto.setAnswerd_questions(Answerd.getAnswerdQuestion());
        dto.setId_question(Answerd.getQuestion().getId_Question());
        
        dtos.add(dto);
    }
    
    return dtos;
    }

    public Optional<requestRegisterAnswerd> findByIdUserRol(int id){
        Optional<answerd> optionalEntity = answerdData.findById(id);
        
        if (optionalEntity.isPresent()) {
            answerd Answerd = optionalEntity.get();
            requestRegisterAnswerd dto = new requestRegisterAnswerd();
            dto.setId_answerd(Answerd.getId_answerd());
            dto.setAnswerd_questions(Answerd.getAnswerdQuestion());
            dto.setId_question(Answerd.getQuestion().getId_Question());
            
            return Optional.of(dto);
        }
        
        return Optional.empty();
    }

    public void save(requestRegisterAnswerd answerd){
        answerdData.save(convertRegisterAnswerd(answerd));
    }

    public answerd convertRegisterAnswerd(requestRegisterAnswerd Answerd) {
        Question question = new Question();
        question.setId_Question(Answerd.getId_question());
        return new answerd(
            0,
            Answerd.getAnswerd_questions(),  // Id_UserRole autogenerado
            question
        );
    }

    public void update(requestRegisterAnswerd AnswerdUpdate) {
        Optional<answerd> AnswerdOptional = answerdData.findById(AnswerdUpdate.getId_answerd());
        
        if (AnswerdOptional.isPresent()) {
            answerd Answerd = AnswerdOptional.get();
            
            // Actualizar el campo answerdQuestion si hay un valor nuevo
            if (AnswerdUpdate.getAnswerd_questions() != null) {
                Answerd.setAnswerdQuestion(AnswerdUpdate.getAnswerd_questions());
            }
            
            // Solo actualiza la Question si realmente ha cambiado el ID
            if (Answerd.getQuestion() == null || Answerd.getQuestion().getId_Question() != AnswerdUpdate.getId_question()) {
                // Crear una referencia a la Question existente
                Question question = new Question();
                question.setId_Question(AnswerdUpdate.getId_question());
                
                // Actualizar la referencia en answerd
                Answerd.setQuestion(question);
            }
            
            // Guardar los cambios
            answerdData.save(Answerd);
        } else {
            throw new RuntimeException("No se encontró la respuesta con ID: " + AnswerdUpdate.getId_answerd());
        }
    }

    public responseDTO delete(int id) {
        responseDTO response = new responseDTO();
        
        if (answerdData.existsById(id)) {
            // Obtener la entidad answerd
            Optional<answerd> answerdOptional = answerdData.findById(id);
            if (answerdOptional.isPresent()) {
                answerd answerToDelete = answerdOptional.get();
                
                // Obtener la Question relacionada y desreferenciar answerd
                Question question = answerToDelete.getQuestion();
                if (question != null) {
                    question.setAnswerds(null);
                    // Guardar la Question actualizada
                    // Necesitarías una referencia a tu repositorio de Question
                    // questionRepository.save(question);
                }
                
                // Ahora elimina answerd
                answerdData.deleteById(id);
                response.setStatus(HttpStatus.OK);
                response.setMessage("Se eliminó correctamente el ID " + id);
            } else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("El registro con ID " + id + " no existe");
            }
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("El registro con ID " + id + " no existe");
        }
        
        return response;
    }
    
    
}
