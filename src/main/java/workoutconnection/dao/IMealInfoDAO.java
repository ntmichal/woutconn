package workoutconnection.dao;



import java.util.List;

import workoutconnection.MealInfoObject;
import workoutconnection.entities.MealInfo;
import workoutconnection.entities.Product;


public interface IMealInfoDAO {

	List<MealInfoObject> getAllMeals(int userid);
	MealInfoObject getMeal(int id);
	void deleteMeal(int id);
	void insertMeal(MealInfoObject meal);
	void updateMeal(MealInfoObject meal);
	List<Product> getShoppingList();
}
