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
	


	void saveWorkouts(List<Map<String, Object>> workouts, int userId)
			throws JsonGenerationException, JsonMappingException, IOException;

	Object getWorkouts(int userId)
			throws JsonParseException, JsonMappingException, IOException;

	Object getMeasurement(int userId);
	
	
	public void insertMeasurement(Measurement measurement, int userId);
	public void updateMeasurement(Measurement measurement);
	public boolean deleteMeasurement(Measurement measurement);
	
	public void insertGoals(UserGoals userGoals, int userId);
	public void updateGoals(UserGoals userGoals);
	public void deleteGoals(UserGoals userGoals);
	List<UserGoals> getAllGoals(int userId);
}
