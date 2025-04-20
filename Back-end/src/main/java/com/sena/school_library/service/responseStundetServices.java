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

    public void save(requestRegisterresponseStudent responseStudent){
        responseStudentData.save(convertRegisterResponseStudent(responseStudent));
    }

    public responseStudent convertRegisterResponseStudent(requestRegisterresponseStudent resposeStudent) {
        Question question = new Question();
        question.setId_Question(resposeStudent.getId_question());
        return new responseStudent(
            0,
            resposeStudent.getStudent_answer(),  // Id_UserRole autogenerado
            question,
            null
        );
    }

    public void update(requestRegisterresponseStudent resposeStudentUpdate) {
        Optional<responseStudent> resposeStudentOptional = responseStudentData.findById(resposeStudentUpdate.getId_response_student());
        
        if (resposeStudentOptional.isPresent()) {
            responseStudent ResponseStudent = resposeStudentOptional.get();
    
            Question question = new Question();
            question.setId_Question(resposeStudentUpdate.getId_question());
    
            // Setear los nuevos objetos en el UserRole
            ResponseStudent.setQuestion(question);

            // Guardar los cambios
            responseStudentData.save(ResponseStudent);
        }
    }

    public responseDTO delete(int id) {
        responseDTO response = new responseDTO();
        
        if (responseStudentData.existsById(id)) {
            responseStudentData.deleteById(id);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Se eliminó correctamente");
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("El registro no existe");
        }
        
        return response;
    }
}
