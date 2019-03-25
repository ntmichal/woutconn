package workoutconnection.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workoutconnection.dao.IMealInfoDAO;
import workoutconnection.entities.Product;
import workoutconnection.models.MealInfoObject;


@Service
public class MealInfoService implements IMealInfoService {

	@Autowired
	private IMealInfoDAO mealInfoDAO;
	@Override
	public List<MealInfoObject> getAllMeals(int userid) {
		return mealInfoDAO.getAllMeals(userid);
	}

	@Override
	public MealInfoObject getMeal(int id) {
		return mealInfoDAO.getMeal(id);
	}

	@Override
	public void deleteMeal(int id) {
		mealInfoDAO.deleteMeal(id);

	}

	@Override
	public void updateMeal(MealInfoObject meal) {
		mealInfoDAO.updateMeal(meal);

	}

	@Override
	public void insertMeal(MealInfoObject meal) {
		mealInfoDAO.insertMeal(meal);
		
	}


}
