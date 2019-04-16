package workoutconnection.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workoutconnection.dao.IMealInfoDAO;
import workoutconnection.entities.Meal;



@Service
public class MealInfoService implements IMealInfoService {

	@Autowired
	private IMealInfoDAO mealInfoDAO;

	@Override
	public void insertMeal(Meal meal,int userId) {
		mealInfoDAO.insertMeal(meal,userId);
	}

	@Override
	public List<Meal> getAllMeals(int userId) {
		return mealInfoDAO.getAllMeals(userId);
	}

	@Override
	public Meal getMeal(int id) {
		return mealInfoDAO.getMeal(id);
	}

	@Override
	public void deleteMeal(int id) {
		mealInfoDAO.deleteMeal(id);

	}

	@Override
	public void updateMeal(Meal meal) {
		mealInfoDAO.updateMeal(meal);

	}

}
