package com.sena.school_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.DTO.requestRegisterResult;
import com.sena.school_library.interfaces.IResponseStudent;
import com.sena.school_library.interfaces.IResult;
import com.sena.school_library.model.Resultado;
import com.sena.school_library.model.responseStudent;

@Service
public class ResultServices {

    @Autowired
    private IResult resultData;

    @Autowired
    private IResponseStudent responseStudentData;

    public List<requestRegisterResult> findAllResult() {
        List<Resultado> entities = resultData.findAll();
        List<requestRegisterResult> dtos = new ArrayList<>();

        for (Resultado result : entities) {
            requestRegisterResult dto = new requestRegisterResult();
            dto.setId_result(result.getId_Result());
            dto.setNota(result.getNote());
            if (result.getResponseStudent() != null) {
                dto.setResponseStudentId(result.getResponseStudent().getId_responseStudent());
            }
            dtos.add(dto);
        }

        return dtos;
    }

    public Optional<requestRegisterResult> findByIdResult(int id) {
        Optional<Resultado> optionalEntity = resultData.findById(id);

        if (optionalEntity.isPresent()) {
            Resultado result = optionalEntity.get();
            requestRegisterResult dto = new requestRegisterResult();
            dto.setId_result(result.getId_Result());
            dto.setNota(result.getNote());
            if (result.getResponseStudent() != null) {
                dto.setResponseStudentId(result.getResponseStudent().getId_responseStudent());
            }

            return Optional.of(dto);
        }

        return Optional.empty();
    }

    public void save(requestRegisterResult result) {
        resultData.save(convertRegisterResult(result));
    }

    public Resultado convertRegisterResult(requestRegisterResult result) {
        Resultado resultado = new Resultado();
        resultado.setNote(result.getNota());
        
        // Siempre requiere un responseStudent válido
        Optional<responseStudent> responseStudentOptional = responseStudentData.findById(result.getResponseStudentId());
        if (responseStudentOptional.isPresent()) {
            resultado.setResponseStudent(responseStudentOptional.get());
        } else {
            throw new RuntimeException("El responseStudent con ID " + result.getResponseStudentId() + " no existe");
        }
        
        return resultado;
    }

    public void update(requestRegisterResult resultUpdate) {
        Optional<Resultado> resultOptional = resultData.findById(resultUpdate.getId_result());
        if (resultOptional.isPresent()) {
            Resultado resultado = resultOptional.get();
            resultado.setNote(resultUpdate.getNota());
            
            if (resultUpdate.getResponseStudentId() != 0) {
                Optional<responseStudent> responseStudentOptional = responseStudentData.findById(resultUpdate.getResponseStudentId());
                if (responseStudentOptional.isPresent()) {
                    resultado.setResponseStudent(responseStudentOptional.get());
                } else {
                    throw new RuntimeException("El responseStudent con ID " + resultUpdate.getResponseStudentId() + " no existe");
                }
            }
            resultData.save(resultado);
        }
    }

    public responseDTO delete(int id) {
        Optional<Resultado> resultEntity = resultData.findById(id);
        responseDTO response = new responseDTO();

        if (resultEntity.isPresent()) {
            resultData.delete(resultEntity.get());
            response.setStatus(HttpStatus.OK);
            response.setMessage("Resultado eliminado correctamente.");
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("El registro no existe.");
        }

        return response;
    }
}
