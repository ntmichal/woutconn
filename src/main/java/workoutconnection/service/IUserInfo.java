package workoutconnection.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import workoutconnection.entities.Measurement;
import workoutconnection.entities.UserGoals;
import workoutconnection.models.Exercise;

public interface IUserInfo {
	
	public Object getUserInfo(int userid) 
			throws JsonParseException, JsonMappingException, IOException;
	
	
	void deleteWorkout(List<Exercise> workout, int userid) 
			throws JsonParseException, JsonMappingException, IOException;
	void saveWorkouts(Map<String, List<Exercise>> workouts, int userid) 
			throws JsonGenerationException, JsonMappingException, IOException;
	public Object getWorkouts(int userid)
			throws JsonParseException, JsonMappingException, IOException;
	

	
	public void insertMeasurement(Measurement measurement, int userid);
	public void updateMeasurement(Measurement measurement);
	public boolean deleteMeasurement(Measurement measurement);
	public Object getMeasurement(int userid);
	
	public void insertGoals(UserGoals userGoals, int userid);
	public void updateGoals(UserGoals userGoals);
	public void deleteGoals(UserGoals userGoals);
	public List<UserGoals> getAllGoals(int userid);
}

