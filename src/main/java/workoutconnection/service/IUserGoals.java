package workoutconnection.service;

import java.util.List;

import workoutconnection.entities.UserGoals;

public interface IUserGoals {
	
	public List<UserGoals> getAllGoals(int userid);
	
	
}
