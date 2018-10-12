package workoutconnection.service;

import java.util.List;

import workoutconnection.entities.User;

public interface IUserService {
    User save(User user);
    List<User> findAll();
    void delete(int id);
    boolean isUserExist(String username);
    boolean isEmailExist(String email);
    
}
