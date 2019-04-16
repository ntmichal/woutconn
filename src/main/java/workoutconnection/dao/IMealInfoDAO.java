package workoutconnection.dao;



import workoutconnection.entities.Meal;

import java.util.List;


public interface IMealInfoDAO {

	List<Meal> getAllMeals(int userid);
	Meal getMeal(int id);
	void deleteMeal(int id);
	void insertMeal(Meal meal);
	void insertMeal(Meal meal,int id);
	void updateMeal(Meal meal);
}
