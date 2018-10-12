package workoutconnection.dao;

import java.util.List;


import workoutconnection.entities.User;



public interface IUserDAO {
	
    User findByUsername(String username);
    User save(User user);
    List<User> findAll();
    void deleteById(int id);
    boolean isUserExist(String username);
    boolean isEmailExist(String email);
}
