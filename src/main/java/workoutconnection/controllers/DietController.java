package workoutconnection.controllers;

import java.net.URI;
import java.util.List;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import workoutconnection.dao.UserInfoDAO;
import workoutconnection.entities.*;
import workoutconnection.service.MealInfoService;
import workoutconnection.service.ProductService;

import javax.persistence.EntityManager;

/**
 *
 *
 * @author Michał Maciocha
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DietController {

	@Autowired
	private MealInfoService mealInfoService;

	@Autowired
	private UserInfoDAO userDAO;



	/**
	 *
	 * @return list of meals
	 */
	@RequestMapping(value = "/api/meal", method = RequestMethod.GET)
	public List<Meal> mealsList(){

		User user =
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return mealInfoService.getAllMeals(user.getId());

	}

	//get 1
	@RequestMapping(value = "/api/meal/{userid}/{id}", method = RequestMethod.GET)
	public Meal getMeal(@PathVariable int userid, @PathVariable int id) {
		return mealInfoService.getMeal(id);
	}

	//insert meal
	@RequestMapping(value = "/api/meal", method = RequestMethod.POST)
	public ResponseEntity<Object> createMeal(@RequestBody Meal meal){

		User user =
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


		Meal mealTmp = Meal.builder()
				.setName(meal.getName())
				.setMealDate(meal.getMealDate())
				.build();

		meal.getMealsList().forEach( x-> {mealTmp.addProduct(x.getProduct(),x.getProductWeight());});

		mealInfoService.insertMeal(mealTmp,user.getId());

		return ResponseEntity.accepted().body(HttpStatus.CREATED);
	}

	//delete meal
	@RequestMapping(value = "/api/meal/{id}", method = RequestMethod.DELETE)
	public void deleteMeal(@PathVariable int id) {

		mealInfoService.deleteMeal(id);
	}


	@RequestMapping(value = "/api/usergoals/{userid}", method = RequestMethod.GET)
	public List<UserGoals> getGoals(@PathVariable int userid){
		return userDAO.getAllGoals(userid);
	}


}
