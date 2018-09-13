package workoutconnection.service;

import java.util.List;

import workoutconnection.entities.User;


public interface IUserSerivce{

    User save(User user);
    List<User> findAll();
    void delete(int id);

}
