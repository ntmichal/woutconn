package workoutconnection.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import workoutconnection.dao.IMealInfoDAO;
import workoutconnection.dto.MealDto;
import workoutconnection.dto.MealInsertDto;
import workoutconnection.dto.ProductDto;
import workoutconnection.entities.Meal;

import javax.transaction.Transactional;


@Service
public class MealInfoService implements IMealInfoService {

	private IMealInfoDAO mealInfoDAO;

	public MealInfoService(IMealInfoDAO mealInfoDAO){
		this.mealInfoDAO = mealInfoDAO;
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
	@Transactional
	public MealDto insertMeal(MealInsertDto mealInsertDto, int userId) {

		Meal meal = Meal.builder()
				.setName(mealInsertDto.getName())
				.setMealDate(mealInsertDto.getMealDate())
				.build();

		Meal finalMeal = mealInfoDAO.insertMeal(meal, userId);

		mealInsertDto.getProductPaths().forEach(productPath -> {
					mealInfoDAO.insertProductToMeal(finalMeal,
							productPath.getId(),
							productPath.getVolume());
				}
		);

		return finalMeal.convertToDto();

	}

	@Override
	public void updateMeal(Meal meal) {
		mealInfoDAO.updateMeal(meal);

	}

	@Override
	public void deleteMeal(int id) {
		mealInfoDAO.deleteMeal(id);

	}


}
