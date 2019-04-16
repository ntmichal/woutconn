package workoutconnection.service;



import java.util.List;

import workoutconnection.entities.Meal;


public interface IMealInfoService {

	List<Meal> getAllMeals(int userId);
	Meal getMeal(int id);
	void deleteMeal(int id);
	void insertMeal(Meal meal, int userId);
	void updateMeal(Meal meal);
}
