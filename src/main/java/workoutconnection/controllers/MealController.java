package workoutconnection.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import workoutconnection.dao.UserInfoDAO;
import workoutconnection.entities.*;
import workoutconnection.service.MealInfoService;

/**
 *
 *
 * @author Michał Maciocha
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MealController {

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

	@GetMapping(value = "/api/meal/{userId}/list")
	public ResponseEntity getMealByDate(@RequestParam("date") String mealDate, @PathVariable int userId){
        User user =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(user.getId().compareTo(userId) != 0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
		List<Map<String,Object>> meals = mealInfoService.getMealByDate(userId,LocalDate.parse(mealDate));
		return ResponseEntity.ok(meals);

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
