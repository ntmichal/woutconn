package workoutconnection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workoutconnection.dao.UserGoalsDAO;
import workoutconnection.entities.UserGoals;

@Service
public class UserGoalsService implements IUserGoals {

	@Autowired
	private UserGoalsDAO userGoalsDAO;
	
	@Override
	public List<UserGoals> getAllGoals(int userid) {
		return userGoalsDAO.getAllGoals(userid);
	}

}
