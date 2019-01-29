package workoutconnection.dao;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import workoutconnection.entities.Measurement;
import workoutconnection.entities.User;
import workoutconnection.entities.UserGoals;
import workoutconnection.models.Exercise;

@Transactional
@Repository
public class UserInfoDAO implements IUserInfoDAO {

	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ObjectMapper objectMapper;
	@SuppressWarnings("unchecked")
	@Override
	public List<UserGoals> getAllGoals(int userid) {
		String HQL = "From UserGoals WHERE user_id = "+ userid;
		Query q = (Query)entityManager.createQuery(HQL);
		q.setParameter("user_id", userid);
		return (List<UserGoals>)q.getResultList();

	}
	
	
	@Override
	public void saveWorkouts(Map<String, List<Exercise>> workouts, int userid) 
			throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File("workoutstore/workoutuserid"+userid+".json"), workouts);
			
	}

	@Override
	public Object getWorkouts(int userid) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(new File("workoutstore/workoutuserid"+userid+".json"), 
				new TypeReference<Map<String,List<Exercise>>>(){});
	}
	
	@Override 
	public void deleteWorkout(List<Exercise> workout, int userid) 
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, List<Exercise>> workouts =  objectMapper.readValue(new File("workoutstore/workoutuserid"+userid+".json"), 
				new TypeReference<Map<String,List<Exercise>>>(){});
		Iterator<Entry<String, List<Exercise>>> iterator = workouts.entrySet().iterator();
		while(iterator.hasNext()) {
			
			Map.Entry<String, List<Exercise>> pair
				= (Entry<String, List<Exercise>>) iterator.next();
			if(pair.getValue().equals(workout)) {
				workouts.remove(pair.getKey());
				break;
			}
			iterator.remove();
		}
		this.saveWorkouts(workouts, userid);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Measurement> getMeasurement(int userid) {
		String HQL = "From Measurement WHERE user_id = :user_id";
		Query query = (Query)entityManager.createQuery(HQL);
		query.setParameter("user_id", userid);
		List<Measurement> userMeasurement = (List<Measurement>)query.getResultList();
	
		return userMeasurement;
	}


	@Override
	public void insertMeasurement(Measurement measurement, int userid) {
		String HQL = "From User Where id = :id";
		Query query = (Query)entityManager.createQuery(HQL);
		query.setParameter("id", userid);
		User usr = (User)query.getSingleResult();
		measurement.setUser(usr);
		entityManager.persist(measurement);
			
	}


	@Override
	public void updateMeasurement(Measurement measurement) {
		Measurement mr = entityManager.find(Measurement.class, measurement.getId());
		measurement.setUser(mr.getUser());
		entityManager.merge(measurement);
		
	}


	@Override
	public boolean deleteMeasurement(Measurement measurement) {
		try {
			Measurement mr = entityManager.find(Measurement.class, measurement.getId());
			String HQL = "Delete From Measurement Where user_id = :user_id AND id = :id";

			Query query = (Query)entityManager.createQuery(HQL);
			query.setParameter("user_id", mr.getUser().getId());
			query.setParameter("id", mr.getId());
			query.executeUpdate();
			return true;
		}catch(Exception o){
			return false;
		}

		
	}


	@Override
	public void insertGoals(UserGoals userGoals, int userid) {
		String HQL = "From User Where id = :id";
		Query query = (Query)entityManager.createQuery(HQL);
		query.setParameter("id", userid);
		User usr = (User)query.getSingleResult();
		userGoals.setUser(usr);
		entityManager.persist(userGoals);
		
	}


	@Override
	public void updateGoals(UserGoals userGoals) {
		UserGoals ug = entityManager.find(UserGoals.class, userGoals.getId());
		userGoals.setUser(ug.getUser());
		entityManager.merge(userGoals);
		
	}


	@Override
	public void deleteGoals(UserGoals userGoals) {
		UserGoals usrGoals = entityManager.find(UserGoals.class, userGoals.getId());
		String HQL = "Delete From UserGoals Where user_id = :user_id AND id = :id";

		Query query = (Query)entityManager.createQuery(HQL);
		query.setParameter("user_id", usrGoals.getUser().getId());
		query.setParameter("id", usrGoals.getId());
		query.executeUpdate();
		
	}


}
