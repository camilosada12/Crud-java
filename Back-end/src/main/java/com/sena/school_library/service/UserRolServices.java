package com.sena.school_library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.school_library.DTO.requestRegisterUserRol;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.interfaces.IUserRol;
import com.sena.school_library.model.Rol;
import com.sena.school_library.model.User;
import com.sena.school_library.model.UserRole;

@Service
public class UserRolServices {
    /*
     * @Autowired = Incluye la conexion de la interface
     */

    @Autowired
    private IUserRol UserRolData;

    public List<requestRegisterUserRol> findAllUserRol(){
    List<UserRole> entities = UserRolData.findAll();
    List<requestRegisterUserRol> dtos = new ArrayList<>();
    
    for (UserRole userRole : entities) {
        requestRegisterUserRol dto = new requestRegisterUserRol();
        dto.setId_user_rol(userRole.getId_UserRole());
        dto.setId_user(userRole.getUser().getId_User());
        dto.setUser_name(userRole.getUser().getName());
        dto.setId_rol(userRole.getRol().getId_Rol());
        dto.setRol_name(userRole.getRol().getRoleType());
        
        dtos.add(dto);
    }
    
    return dtos;
    }

    // public List<UserRole> filterType(String filter){
    //     return UserRolData.filterTypeUserRol(filter);
    // }

    public Optional<requestRegisterUserRol> findByIdUserRol(int id){
        Optional<UserRole> optionalEntity = UserRolData.findById(id);
        
        if (optionalEntity.isPresent()) {
            UserRole userRole = optionalEntity.get();
            requestRegisterUserRol dto = new requestRegisterUserRol();
            dto.setId_user_rol(userRole.getId_UserRole());
            dto.setId_user(userRole.getUser().getId_User());
            dto.setUser_name(userRole.getUser().getName());
            dto.setId_rol(userRole.getRol().getId_Rol());
            dto.setRol_name(userRole.getRol().getRoleType());
            
            return Optional.of(dto);
        }
        
        return Optional.empty();
    }

    public void save(requestRegisterUserRol userRol){
        UserRolData.save(convertRegisterRol(userRol));
    }

    public UserRole convertRegisterRol(requestRegisterUserRol userRol) {
        User user = new User();
        user.setId_User(userRol.getId_user());
        user.setName(userRol.getUser_name());
        Rol rol = new Rol();
        rol.setId_Rol(userRol.getId_rol());
        rol.setRoleType(userRol.getRol_name());
        return new UserRole(
            0,  // Id_UserRole autogenerado
            user,
            rol
        );
    }

    public void update(requestRegisterUserRol userRolUpdate) {
        Optional<UserRole> userRoleOptional = UserRolData.findById(userRolUpdate.getId_user_rol());
        
        if (userRoleOptional.isPresent()) {
            UserRole userRole = userRoleOptional.get();
    
            User user = new User();
            user.setId_User(userRolUpdate.getId_user());
            user.setName(userRolUpdate.getUser_name());
    
            Rol rol = new Rol();
            rol.setId_Rol(userRolUpdate.getId_rol());
            rol.setRoleType(userRolUpdate.getRol_name());
    
            // Setear los nuevos objetos en el UserRole
            userRole.setUser(user);
            userRole.setRol(rol);
    
            // Guardar los cambios
            UserRolData.save(userRole);
        }
    }

    public responseDTO delete(int id){
        Optional<UserRole> userRoleEntity = UserRolData.findById(id);
        responseDTO response = new responseDTO();
        
        if(userRoleEntity.isPresent()){
            UserRolData.delete(userRoleEntity.get());
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
