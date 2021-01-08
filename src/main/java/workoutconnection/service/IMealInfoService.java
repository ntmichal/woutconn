package workoutconnection.service;



import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import workoutconnection.entities.Meal;


public interface IMealInfoService {

	List<Meal> getAllMeals(int userId);
	Meal getMeal(int id);
	List<Map<String,Object>> getMealByDate(int userId, LocalDate mealDate);
	void deleteMeal(int id);
	void insertMeal(Meal meal, int userId);
	void updateMeal(Meal meal);
}
