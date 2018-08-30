package workoutconnection.interfaces;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import workoutconnection.entities.User;

@Service
public class UserSerivce implements UserDetailsService{

	@Autowired
	private EntityManager entityManager;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return (User)entityManager.createQuery("From User WHERE USER_NAME = " + username);
	}

}
