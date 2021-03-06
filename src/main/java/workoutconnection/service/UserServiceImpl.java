package workoutconnection.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import workoutconnection.dao.IUserDAO;
import workoutconnection.entities.User;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, IUserService {
	
	@Autowired
	private IUserDAO userDao;

	public UserDetails loadUserByUsername(String userId){
		User user = userDao.findByUsername(userId);
//		if(user == null){
//
//			throw new UsernameNotFoundException("Invalid username or password.");
//		}
		//return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
		return user;
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
	
		return list;
	}

	@Override
	public void delete(int id) {
		userDao.deleteById(id);
	}

	@Override
    public User save(User user) {
        return userDao.save(user);
    }

	@Override
	public boolean isUserExist(String username) {
		return userDao.isUserExist(username);
	}

	@Override
	public boolean isEmailExist(String email) {
		return userDao.isEmailExist(email);
	}
	

	
}