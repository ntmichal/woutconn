package workoutconnection.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLogin {
	private String username;
	private String password;
	private String email;
	
	public UserLogin(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void elo() {

	
		
		List<Object[]> list = new ArrayList<Object[]>();
		Object[] elo = new Object[2];
		elo[0] = 1;
		elo[1] = new int[] {1,2,3,4,5,6};
		
		list.add(elo);
		elo = new Object[2];
		elo[0] = 5;
		elo[1] = new int[] {1,2,3,4,5,6};
		list.add(elo);
		
		elo = new Object[2];
		elo[0] = 7;
		elo[1] = new int[] {1,2,3,4,5,6};
		
		list.add(elo);
		elo = new Object[2];
		elo[0] = 0;
		elo[1] = new int[] {1,2,3,4,5,6};
		
		
	}
	
	
}


