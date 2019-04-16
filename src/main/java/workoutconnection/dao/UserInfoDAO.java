package workoutconnection.dao;

import java.io.File;
import java.io.IOException;
import java.util.*;

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


@Repository
@Transactional
public class UserInfoDAO implements IUserInfoDAO {


	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ObjectMapper objectMapper;
	@SuppressWarnings("unchecked")
	@Override
	public List<UserGoals> getAllGoals(int userId) {
		String HQL = "From UserGoals WHERE user_id = :user_id";
		Query q = entityManager.createQuery(HQL);
		q.setParameter("user_id", userId);


		return (List<UserGoals>)q.getResultList();

	}


	@Override
	public void saveWorkouts(List<Map<String,Object>> workouts, int userId)
			throws JsonGenerationException, JsonMappingException, IOException{

		if(workouts.size() <= 5){
			objectMapper.writeValue(new File("workoutstore/workoutuserid"+userId+".json"), workouts);
		}
	}

	@Override
	public Object getWorkouts(int userId) throws JsonParseException, JsonMappingException, IOException {
	    return objectMapper.readValue(new File("workoutstore/workoutuserid"+userId+".json"),
				new TypeReference<List<Map<String,Object>>>(){});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Measurement> getMeasurement(int userId) {
		String HQL = "From Measurement WHERE user_id = :user_id";
		Query query = (Query)entityManager.createQuery(HQL);
		query.setParameter("user_id", userId);
		List<Measurement> userMeasurement = (List<Measurement>)query.getResultList();

		return userMeasurement;
	}


	@Override
	public void insertMeasurement(Measurement measurement, int userId) {
		User usr = entityManager.find(User.class, userId);
		measurement.setUser(usr);
		entityManager.persist(measurement);
	}


	@Override
	public void updateMeasurement(Measurement measurement) {
		Measurement mr = entityManager.find(Measurement.class, measurement.getId());
		measurement.setUser(mr.getUser());
		entityManager.merge(measurement);

	}

//TODO fix this
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


	public void insertGoals(UserGoals userGoals) {
		entityManager.persist(userGoals);
	}

	@Override
	public void insertGoals(UserGoals userGoals, int userId) {
		userGoals.setUser(entityManager.find(User.class,userId));
		entityManager.persist(userGoals);

	}

	//TODO fix this
	@Override
	public void updateGoals(UserGoals userGoals) {
		UserGoals ug = entityManager.find(UserGoals.class, userGoals.getId());
		userGoals.setUser(ug.getUser());
		entityManager.merge(userGoals);

	}

	//TODO fix this
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
