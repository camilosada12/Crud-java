package com.sena.school_library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.school_library.DTO.requestRegisterUser;
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

    public Optional<User>findByIdUser(int id){
        return UserData.findById(id);
    }

    public void save(requestRegisterUser user){
        UserData.save(converRegisterToUser(user));
    }

    public User converRegisterToUser(requestRegisterUser user){
        return new User(
            0,
            user.getName(),
            user.getLastName(),
            user.getUser(),
            user.getPassword(),
            user.getMail(),
            user.getRol()
        );
    }

    public void update(int id, User UserUpdate){
        var User = findByIdUser(id);
        if(User.isPresent()){
            User.get().setName(UserUpdate.getName());
            User.get().setLastName(UserUpdate.getLastName());
            User.get().setUser(UserUpdate.getUser());
            User.get().setPassword(UserUpdate.getPassword());
            User.get().setMail(UserUpdate.getMail());
            User.get().setRol(UserUpdate.getRol());
        }
    }

    public void delete(int id){
        var User = findByIdUser(id);
        if(User.isPresent()){
            UserData.delete(User.get());
        }
    }
}
