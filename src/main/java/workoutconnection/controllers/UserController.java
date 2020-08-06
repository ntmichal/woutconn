package workoutconnection.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;


import io.jsonwebtoken.Claims;
import workoutconnection.entities.Authority;
import workoutconnection.entities.Measurement;
import workoutconnection.entities.User;
import workoutconnection.entities.UserGoals;
import workoutconnection.models.UserLogin;
import workoutconnection.service.IUserInfo;
import workoutconnection.service.UserServiceImpl;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserServiceImpl userServiceImpl;

    @Autowired
    private IUserInfo userInfo;


	@RequestMapping(value="/api/signup", method = RequestMethod.POST)
	public ResponseEntity<Object> singUpUser(@RequestBody UserLogin userLogin){

		Map<String,String> errorMessages = new HashMap<>();
		
		if(userLogin.getUsername() != null && userLogin.getUsername().length() != 0) {
			if(userServiceImpl.isUserExist(userLogin.getUsername())) {
				errorMessages.put("user","user alredy exist!");
			}
		}else {
			errorMessages.put("user","login is required");
		}
		
		if(userLogin.getEmail()!= null && userLogin.getEmail().length() != 0) {
			if(userServiceImpl.isEmailExist(userLogin.getEmail()) ) {
				errorMessages.put("email","email already used");
			}
		}else {
			errorMessages.put("email","email is required");
		}
		


		if(userLogin.getPassword() != null && userLogin.getPassword().length() != 0) {
			final String passwordPattern = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
			Matcher matcher = Pattern.compile(passwordPattern).matcher(userLogin.getPassword());
			if(!matcher.matches()) {
				errorMessages.put("password","password is too weak");
			}
	
			
		}else {
			errorMessages.put("password","password is required");
		}
		
		if(!errorMessages.isEmpty()) {
			return new ResponseEntity<>(errorMessages, HttpStatus.NOT_ACCEPTABLE);
		}
		
		User user = new User();
			user.setUsername(userLogin.getUsername());
			user.setEmail(userLogin.getEmail());
			user.setPassword(passwordEncoder.encode(userLogin.getPassword()));
			user.setEnabled(true);

		Authority authority = new Authority();
		authority.setRole("ROLE_USER");
		List<Authority> authorityList = new ArrayList<>();
		authorityList.add(authority);
		user.setAuthorityList(authorityList);



		User result = userServiceImpl.save(user);

		URI location = URI.create("/api/user/"+result.getId());

		ObjectMapper om = new ObjectMapper();
		try {
			om.writeValue(new File("workoutstore/workoutuserid"+result.getId()+".json"),new ArrayList<>());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity
					.created(location)
					.body(HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/api/userinfojson", method=RequestMethod.GET)
	public Object returnUserJson() throws IOException {
		User user =
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userInfo.getUserInfo(user.getId());
	}


	@PostMapping(value="/api/usergoals")
	public void insertGoals(@RequestBody UserGoals userGoals) {
		User user =
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userInfo.insertGoals(userGoals, user.getId());
	}

	@PutMapping(value="/api/usergoals")
	public void updateGoals(@RequestBody UserGoals userGoals) {
		userInfo.updateGoals(userGoals);
	}

	@DeleteMapping(value="/api/usergoals")
	public void deleteGoals(@RequestBody UserGoals userGoals) {
		userInfo.deleteGoals(userGoals);
	}




}
