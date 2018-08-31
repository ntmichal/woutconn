package workoutconnection.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import workoutconnection.entities.UserGoals;

@Transactional
@Repository
public class UserGoalsDAO implements IUserGoalsDAO {

	
	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserGoals> getAllGoals(int userid) {
		String HQL = "From UserGoals WHERE user_id = "+ userid;
		
		return (List<UserGoals>)entityManager.createQuery(HQL).getResultList();

	}



}
