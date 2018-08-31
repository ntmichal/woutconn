package workoutconnection.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import workoutconnection.entities.User;


public interface IUserSerivce{

    User save(User user);
    List<User> findAll();
    void delete(int id);

}
