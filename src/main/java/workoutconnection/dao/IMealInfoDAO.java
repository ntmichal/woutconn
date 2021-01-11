package workoutconnection.dao;



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
	void insertMeal(Meal meal,int id);
	void updateMeal(Meal meal);
}
