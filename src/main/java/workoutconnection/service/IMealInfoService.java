package workoutconnection.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import workoutconnection.dto.MealDto;
import workoutconnection.dto.MealInsertDto;
import workoutconnection.entities.Meal;



public interface IMealInfoService {

	List<Meal> getAllMeals(int userId);
	Meal getMeal(int id, int userId);
	List<String> getMealProducts(int id, int userId);
	List<Map<String,Object>> getMealByDate(int userId, LocalDate mealDate);
	void deleteMeal(int id);
	MealDto insertMeal(MealInsertDto meal, int userId);
	void updateMeal(Meal meal);
}
