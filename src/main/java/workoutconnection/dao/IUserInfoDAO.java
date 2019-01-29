package workoutconnection.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import workoutconnection.entities.Measurement;
import workoutconnection.entities.UserGoals;
import workoutconnection.models.Exercise;

public interface IUserInfoDAO {
	

	void saveWorkouts(Map<String, List<Exercise>> workouts, int userid) 
			throws JsonGenerationException, JsonMappingException, IOException;
	Object getWorkouts(int userid) 
			throws JsonParseException, JsonMappingException, IOException;
	
	void deleteWorkout(List<Exercise> wrokout, int userid) 
			throws JsonParseException, JsonMappingException, IOException;
	Object getMeasurement(int userid);
	
	
	public void insertMeasurement(Measurement measurement, int userid);
	public void updateMeasurement(Measurement measurement);
	public boolean deleteMeasurement(Measurement measurement);
	
	public void insertGoals(UserGoals userGoals, int userid);
	public void updateGoals(UserGoals userGoals);
	public void deleteGoals(UserGoals userGoals);
	List<UserGoals> getAllGoals(int userid);
}
