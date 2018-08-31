package workoutconnection.service;



import java.util.List;

import workoutconnection.MealInfoObject;
import workoutconnection.entities.MealInfo;
import workoutconnection.entities.Product;


public interface IMealInfoService {
	
	List<MealInfoObject> getAllMeals(int userid);
	MealInfoObject getMeal(int id);
	void deleteMeal(int id);
	void insertMeal(MealInfoObject meal);
	void updateMeal(MealInfoObject meal);
	List<Product> getShoppingList();
}
