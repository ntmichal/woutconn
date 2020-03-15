package workoutconnection.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
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
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Meal> mealCriteriaQuery = cb.createQuery(Meal.class);
		Root<Meal> meal = mealCriteriaQuery.from(Meal.class);

		Subquery<User> subquery = mealCriteriaQuery.subquery(User.class);
		Root<User> user = subquery.from(User.class);
		subquery.select(user).where(cb.equal(user.get("id"),userId));

		mealCriteriaQuery.select(meal).where(cb.in(meal.get("user")).value(subquery));

		return (List<Meal>)entityManager.createQuery(mealCriteriaQuery).getResultList();

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
