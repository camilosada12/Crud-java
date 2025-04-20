package com.sena.school_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.school_library.DTO.requestRegisterresponseStudent;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.interfaces.IResponseStudent;
import com.sena.school_library.model.Question;
import com.sena.school_library.model.Resultado;
import com.sena.school_library.model.responseStudent;

@Service
public class responseStundetServices {
    /*
     * @Autowired = Incluye la conexion de la interface
     */

    @Autowired
    private IResponseStudent responseStudentData;

    public List<requestRegisterresponseStudent> findAllResponseStudent() {
        List<responseStudent> entities = responseStudentData.findAll();
        List<requestRegisterresponseStudent> dtos = new ArrayList<>();

        for (responseStudent responseStudent : entities) {
            requestRegisterresponseStudent dto = new requestRegisterresponseStudent();
            dto.setId_response_student(responseStudent.getId_responseStudent());
            dto.setId_question(responseStudent.getQuestion().getId_Question());
            dto.setId_response_student(responseStudent.getId_responseStudent());
            dto.setStudent_answer(responseStudent.getStudentAnswer());

            dtos.add(dto);
        }

        return dtos;
    }

    public Optional<requestRegisterresponseStudent> findByIdResponseStudent(int id) {
        Optional<responseStudent> optionalEntity = responseStudentData.findById(id);

        if (optionalEntity.isPresent()) {
            responseStudent ResponseStudent = optionalEntity.get();
            requestRegisterresponseStudent dto = new requestRegisterresponseStudent();
            dto.setId_response_student(ResponseStudent.getId_responseStudent());
            dto.setId_question(ResponseStudent.getQuestion().getId_Question());
            dto.setId_response_student(ResponseStudent.getId_responseStudent());
            dto.setStudent_answer(ResponseStudent.getStudentAnswer());

            return Optional.of(dto);
        }

        return Optional.empty();
    }

    public void save(requestRegisterresponseStudent responseStudent) {
        responseStudentData.save(convertRegisterResponseStudent(responseStudent));
    }

    public responseStudent convertRegisterResponseStudent(requestRegisterresponseStudent resposeStudent) {
        Question question = new Question();
        question.setId_Question(resposeStudent.getId_question());
        return new responseStudent(
                0,
                resposeStudent.getStudent_answer(), // Id_UserRole autogenerado
                question,
                null);
    }

    public void update(requestRegisterresponseStudent resposeStudentUpdate) {
        Optional<responseStudent> resposeStudentOptional = responseStudentData
                .findById(resposeStudentUpdate.getId_response_student());

        if (resposeStudentOptional.isPresent()) {
            responseStudent ResponseStudent = resposeStudentOptional.get();

            // Actualizar el campo studentAnswer si hay un valor nuevo
            if (resposeStudentUpdate.getStudent_answer() != null) {
                ResponseStudent.setStudentAnswer(resposeStudentUpdate.getStudent_answer());
            }

            // Solo actualizar la referencia a Question si no es la misma
            if (ResponseStudent.getQuestion() == null ||
                    ResponseStudent.getQuestion().getId_Question() != resposeStudentUpdate.getId_question()) {

                // Crear una referencia a la Question existente
                Question question = new Question();
                question.setId_Question(resposeStudentUpdate.getId_question());

                // Si ya tenía una Question asignada, necesitamos manejar la relación
                // bidireccional
                if (ResponseStudent.getQuestion() != null &&
                        ResponseStudent.getQuestion().getResponseStudent() == ResponseStudent) {
                    ResponseStudent.getQuestion().setResponseStudent(null);
                }

                // Establecer la nueva relación
                ResponseStudent.setQuestion(question);
            }

            try {
                // Guardar los cambios
                responseStudentData.save(ResponseStudent);
            } catch (Exception e) {
                // Capturar la excepción específica de duplicado
                if (e.getMessage() != null && e.getMessage().contains("Duplicate entry")) {
                    throw new RuntimeException("Ya existe una respuesta para la pregunta con ID " +
                            resposeStudentUpdate.getId_question()
                            + ". Una pregunta solo puede tener una respuesta asociada.");
                } else {
                    throw e; // Re-lanzar cualquier otra excepción
                }
            }
        } else {
            throw new RuntimeException("No se encontró la respuesta de estudiante con ID: " +
                    resposeStudentUpdate.getId_response_student());
        }
    }

    public responseDTO delete(int id) {
        responseDTO response = new responseDTO();

        if (responseStudentData.existsById(id)) {
            // Obtener la entidad responseStudent
            Optional<responseStudent> responseStudentOptional = responseStudentData.findById(id);
            if (responseStudentOptional.isPresent()) {
                responseStudent responseToDelete = responseStudentOptional.get();

                // 1. Desreferenciar la pregunta asociada
                Question question = responseToDelete.getQuestion();
                if (question != null && question.getResponseStudent() == responseToDelete) {
                    question.setResponseStudent(null);
                    // Si tienes un repositorio para Question, deberías guardar los cambios
                    // questionRepository.save(question);
                }

                // 2. Limpiar la lista de resultados (ya que tienen cascade=ALL, se eliminarán
                // con el parent)
                // No es necesario eliminarlos explícitamente gracias a orphanRemoval=true
                if (responseToDelete.getResults() != null) {
                    // Para evitar problemas con referencias, limpiamos la relación en ambas
                    // direcciones
                    for (Resultado resultado : responseToDelete.getResults()) {
                        resultado.setResponseStudent(null);
                    }
                    responseToDelete.getResults().clear();
                }

                // 3. Ahora sí eliminamos responseStudent
                responseStudentData.deleteById(id);
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
