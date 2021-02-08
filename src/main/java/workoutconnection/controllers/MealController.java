package workoutconnection.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import workoutconnection.dto.MealDto;
import workoutconnection.dto.MealInsertDto;
import workoutconnection.dto.ProductDto;
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



	@GetMapping(value = "/api/meal/{userId}/list")
	public ResponseEntity getMealByDate(@RequestParam("date") String mealDate,
										@PathVariable int userId){
        User user =
                (User) SecurityContextHolder
						.getContext()
						.getAuthentication()
						.getPrincipal();

        if(user.getId().compareTo(userId) != 0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
		List<Map<String,Object>> meals =
				mealInfoService.getMealByDate(userId,LocalDate.parse(mealDate));

		return ResponseEntity.ok(meals);

	}

	@GetMapping(value = "/api/meal/{userId}/id/{mealId}/product/list")
	public ResponseEntity getMealProductList(@PathVariable int userId, @PathVariable int mealId){
		User user =
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(user.getId().compareTo(userId) != 0){
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		List<String> mealsList;
		try{
			mealsList = mealInfoService.getMealProducts(mealId,userId);
			return ResponseEntity.ok(mealsList);
		}catch (Exception e){
			return ResponseEntity.notFound().build();
		}
	}


	@RequestMapping(value = "/api/meal", method = RequestMethod.POST)
	public ResponseEntity<MealDto> createMeal(@RequestBody MealInsertDto meal){

		User user =
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		MealDto mealDto = mealInfoService.insertMeal(meal,user.getId());

		return ResponseEntity
				.created(URI.create(Integer.toString(mealDto.getId())))
				.build();


	}

	@PutMapping(value = "/api/meal/{mealId}")
	public ResponseEntity updateMeal(@PathVariable int mealId, @RequestBody Meal meal){

		if(mealId != meal.getId())
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		User user =
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(mealInfoService.getMeal(mealId,user.getId()) != null){
//			todo impl update
			return ResponseEntity.ok().build();
		}else{
			return ResponseEntity.notFound().build();
		}

	}

	@RequestMapping(value = "/api/meal/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteMeal(@PathVariable int id) {
		User user =
				(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try{
			mealInfoService.getMeal(id, user.getId());
			mealInfoService.deleteMeal(id);
		}catch (Exception e){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

}
