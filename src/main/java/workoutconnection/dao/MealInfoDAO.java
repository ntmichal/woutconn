package workoutconnection.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import workoutconnection.entities.Meal;
import workoutconnection.entities.User;



@Repository
@Transactional
public class MealInfoDAO implements IMealInfoDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public void insertMeal(Meal meal) {
		entityManager.merge(meal);
	}
	@Override
	public void insertMeal(Meal meal, int id) {
		meal.setUser(entityManager.find(User.class,id));
 		entityManager.merge(meal);
	}



    @Override
	public List<Meal> getAllMeals(int userId){
	    String HQL = "From Meal WHERE user_id = :user_id";
	    List<Meal> mealsList = (List<Meal>)entityManager
                .createQuery(HQL)
                .setParameter("user_id",userId)
                .getResultList();

        return mealsList;
	}



	@Override
	public Meal getMeal(int id) {
	    return (Meal)entityManager
                .createQuery("From Meal WHERE id = :id")
                .setParameter("id",id)
				.getSingleResult();
	}

	@Override
	public void deleteMeal(int id) {
		Meal meal = entityManager.find(Meal.class,id);

		entityManager.remove(meal);
	}

	@Override
	public void updateMeal(Meal meal) {
		entityManager.merge(meal);
	}


}
