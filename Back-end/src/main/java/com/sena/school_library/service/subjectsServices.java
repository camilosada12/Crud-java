package com.sena.school_library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.school_library.DTO.requestRegisterSubjects;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.interfaces.ISubjects;
import com.sena.school_library.model.Subjects;

@Service
public class subjectsServices {
    /*
     * @Autowired = Incluye la conexion de la interface
     */

    @Autowired
    private ISubjects SubjectsData;

    public List<Subjects> findAllSubjects() {
        return SubjectsData.findAll(); 
    }
    

    public List<Subjects> filterSubject(String filter){
    return SubjectsData.filterSubjects(filter);
    }

    public Optional<Subjects>findByIdSubject(int id){
        return SubjectsData.findById(id);
    }

    public void save(requestRegisterSubjects subjects){
        SubjectsData.save(convertRegisterToSubject(subjects));
    }

    public Subjects convertRegisterToSubject(requestRegisterSubjects subjects) {
        return new Subjects(
            0, 
            subjects.getSubjectClasses(),
            null
        );
    }

    public void update(requestRegisterSubjects subjectsUpdate){
        var subjects = findByIdSubject(subjectsUpdate.getId_subject());
        if(subjects.isPresent()){
            subjects.get().setSubjectsClasses(subjectsUpdate.getSubjectClasses());
            SubjectsData.save(subjects.get());
        }
    }

    public responseDTO delete(int id){
        var subjects = findByIdSubject(id);
        responseDTO response = new responseDTO();
        if(subjects.isPresent()){
            SubjectsData.delete(subjects.get());
            response.setStatus(HttpStatus.OK);
            response.setMessage("se elimino correctamente");
            return response;
        }else{
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("El registro no existe");
            return response ;
        }
    }
    
}