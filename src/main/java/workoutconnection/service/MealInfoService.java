package workoutconnection.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workoutconnection.dao.IMealInfoDAO;
import workoutconnection.dto.ProductDto;
import workoutconnection.entities.Meal;
import workoutconnection.entities.MealsList;
import workoutconnection.entities.Product;


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
	public List<Map<String,Object>> getMealByDate(int userId, LocalDate mealDate) {
		List<Meal> mealList = mealInfoDAO.getMealByDate(userId, mealDate);

		List<Map<String,Object>> mealsLinksMapList = new ArrayList<>();


		mealList.stream().forEach( meal -> {
			StringBuilder apiLinkToMeal = new StringBuilder();
			apiLinkToMeal.append("/api/meal/");
			apiLinkToMeal.append(userId);
			apiLinkToMeal.append("/id/");
			apiLinkToMeal.append(meal.getId());

			Map<String, Object> mealsLinksMap = new HashMap<>();
			mealsLinksMap.put("id", meal.getId());
			mealsLinksMap.put("name", meal.getName());
			mealsLinksMap.put("link", apiLinkToMeal.toString());

			mealsLinksMapList.add(mealsLinksMap);

		});

		return mealsLinksMapList;

	}

	@Override
	public List<ProductDto> getMealProducts(int id, int userId) {
		Meal meal = mealInfoDAO.getMeal(id,userId);
		if(meal != null){
			List<ProductDto> mealProductsList = mealInfoDAO.
					getMealProducts(id)
					.stream().map(x -> x.convertToDto()).collect(Collectors.toList());

			return mealProductsList;
		}
		return null;
	}
	@Override
	public Meal getMeal(int id, int userId) {
		return mealInfoDAO.getMeal(id, userId);
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
