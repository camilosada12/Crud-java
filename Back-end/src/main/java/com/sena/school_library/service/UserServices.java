package com.sena.school_library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.school_library.DTO.requestRegisterUser;
import com.sena.school_library.DTO.responseDTO;
import com.sena.school_library.interfaces.IUser;
import com.sena.school_library.model.User;

@Service
public class UserServices {
    /*
     * @Autowired = Incluye la conexion de la interface
     */
    
    @Autowired
    private IUser UserData;

    public List<User> findAllUsers(){
        return UserData.findAll();
    }

    public List<User> filterFormName(String filter){
        return UserData.filterFormName(filter);
    }

    public Optional<User>findByIdUser(int id){
        return UserData.findById(id);
    }

    public void save(requestRegisterUser user){
        UserData.save(converRegisterToUser(user));
    }

    public User converRegisterToUser(requestRegisterUser user){
            return new User(  // Correcto: User con U mayúscula
        0,
        user.getName(),
        user.getLastname(),
        user.getUser(),
        user.getPassword(),
        user.getMail(),
        user.getRol()
    );
    }

    public void update(requestRegisterUser UserUpdate){
        var User = findByIdUser(UserUpdate.getId_user());
        if(User.isPresent()){
            User.get().setName(UserUpdate.getName());
            User.get().setLastName(UserUpdate.getLastname());
            User.get().setUser(UserUpdate.getUser());
            User.get().setPassword(UserUpdate.getPassword());
            User.get().setMail(UserUpdate.getMail());
            User.get().setRol(UserUpdate.getRol());
            UserData.save(User.get());
        }
    }

    public responseDTO delete(int id){
        var User = findByIdUser(id);
        responseDTO response = new responseDTO();
        if(User.isPresent()){
            UserData.delete(User.get());
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
