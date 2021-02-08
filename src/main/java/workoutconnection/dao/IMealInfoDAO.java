package workoutconnection.dao;



import workoutconnection.dto.MealDto;
import workoutconnection.entities.Meal;
import workoutconnection.entities.Product;

import java.time.LocalDate;
import java.util.List;


public interface IMealInfoDAO {

	List<Meal> getAllMeals(int userId);
	Meal getMeal(int id, int userId);
	List<Product> getMealProducts(int id);
	List<Meal> getMealByDate(int userId, LocalDate localDate);
	void deleteMeal(int id);
	void insertMeal(Meal meal);
	Meal insertMeal(Meal meal, int id);
	Meal insertProductToMeal(Meal meal, int productId, int productVolume);
	void updateMeal(Meal meal);
}
