package com.sena.school_library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.school_library.DTO.requestRegisterRol;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.interfaces.IRol;
import com.sena.school_library.model.Rol;

@Service
public class RolServices {

    /*
     * @Autowired = Incluye la conexion de la interface
     */

     @Autowired
     private IRol RolData;
     
    public List<Rol> findAllRol(){
        return RolData.findAll();
    }

    public List<Rol> filterType(String filter){
        return RolData.filterTypeRol(filter);
    }

    public Optional<Rol>findByIdRol(int id){
        return RolData.findById(id);
    }

    public void save(requestRegisterRol rol){
        RolData.save(converRegisterRol(rol));
    }

    public Rol converRegisterRol(requestRegisterRol rol){
        return new Rol(
            0,
            rol.getRole_type(),
            null
        );
    }

    public void update(requestRegisterRol rolUpdate){
        var rol = findByIdRol(rolUpdate.getId_rol());
        if(rol.isPresent()){
            rol.get().setRoleType(rolUpdate.getRole_type());
            RolData.save(rol.get());
        }
    }

    public responseDTO delete(int id){
        var role = findByIdRol(id);
        responseDTO response = new responseDTO();
        if(role.isPresent()){
            RolData.delete(role.get());
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
