package workoutconnection.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import workoutconnection.config.TokenProvider;
import workoutconnection.entities.User;
import workoutconnection.entities.UserGoals;
import workoutconnection.models.Token;
import workoutconnection.models.UserLogin;
import workoutconnection.service.IUserGoals;
import workoutconnection.service.UserServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;
	
    @Autowired
    private IUserGoals userGoals;


	@RequestMapping(value="/api/signin", method = RequestMethod.POST)
	public ResponseEntity<Object> singInUser(@RequestBody UserLogin userLogin)  
			throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		userLogin.getUsername(),
                		userLogin.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User)userServiceImpl.loadUserByUsername(userLogin.getUsername());
        final String token = jwtTokenUtil.generateToken(authentication,user);
        return ResponseEntity.ok(new Token(token));
   
    }
	
	@RequestMapping(value="/api/signup", method = RequestMethod.POST)
	public ResponseEntity<Object> singUpUser(@RequestBody UserLogin userLogin){

		if(userServiceImpl.isUserExist(userLogin.getUsername())) {
	         return ResponseEntity
	        		 .created(URI.create("/api/signUp"))
	        		 .body(HttpStatus.BAD_REQUEST);
		}
		if(userServiceImpl.isEmailExist(userLogin.getEmail())) {
			return ResponseEntity
					.created(URI.create("/api/singUp"))
					.body(HttpStatus.BAD_REQUEST);
		}
		User user = new User();
			user.setUsername(userLogin.getUsername());
			user.setEmail(userLogin.getEmail());
			user.setPassword(passwordEncoder.encode(userLogin.getPassword()));
			
		//set ROLES
			
		
		User result = userServiceImpl.save(user);
		
		URI location = URI.create("/api/user/"+result.getId());
		
		return ResponseEntity
					.created(location)
					.body(HttpStatus.ACCEPTED);
		
	}
	
	@RequestMapping(value="/api/usergoals")
	public List<UserGoals> getUserGoals(@RequestHeader("Authorization") String bearerToken){
		String token = bearerToken.replace("Bearer ", "");
		Claims claim = jwtTokenUtil.getAllClaimsFromToken(token);
		int userid = claim.get("id",Integer.class);
		List<UserGoals> userGoalsList = userGoals.getAllGoals(userid);
		return userGoalsList;
		
	}
}
