package workoutconnection.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
//import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.jsonwebtoken.Claims;
import workoutconnection.config.TokenProvider;
import workoutconnection.dao.UserGoalsDAO;
import workoutconnection.entities.Product;
import workoutconnection.entities.UserGoals;
import workoutconnection.models.MealInfoObject;
import workoutconnection.service.MealInfoService;
import workoutconnection.service.ProductService;


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
	private TokenProvider tokenProvider;

	@Autowired
	private ProductService productService;
	@Autowired
	private MealInfoService mealInfoService;

	@Autowired
	private UserGoalsDAO userDAO;
	

//	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/api/product/list", method = RequestMethod.GET)
	public List<Product> productList(){

		return productService.getAllProducts();
	}
		

	@RequestMapping(value = "/api/product/", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product){
		Product savedProduct = productService.insertProduct(product);
	
		//response path
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedProduct.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	//delete product only from admin panel, function for admins/moderators
	@RequestMapping(value = "/api/product/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable int id) {
		productService.deleteById(id);
	}
	
	//update product only from admin panel, function for admins/morderators
	@RequestMapping(value = "/api/product/{id}", method = RequestMethod.PUT)
	public void updateProduct(@RequestBody Product product, @PathVariable int id){
		product.setId(id);
		productService.update(product);
	}

	
	/**
	 * 
	 * @return list of meals
	 */
	@RequestMapping(value = "/api/meal", method = RequestMethod.GET)
	public List<MealInfoObject> mealsList(@RequestHeader("Authorization") String bearerToken){
		String token = bearerToken.replace("Bearer ", "");
		Claims userToken = tokenProvider.getAllClaimsFromToken(token);
		int idFromToken = userToken.get("id", Integer.class);
		
		return mealInfoService.getAllMeals(idFromToken);
	
	}

	
	//get 1 
	@RequestMapping(value = "/api/meal/{userid}/{id}", method = RequestMethod.GET)
	public MealInfoObject getMeal(@PathVariable int userid, @PathVariable int id) {
		
		
		return mealInfoService.getMeal(id);
	}

	
	//insert meal
	@RequestMapping(value = "/api/meal", method = RequestMethod.POST)
	public ResponseEntity<Object> createMeal(@RequestBody MealInfoObject meal){
		
//		//final OAuth2AuthenticationDetails oAuthDetails = (OAuth2AuthenticationDetails)auth.getDetails(); 
//		
//		//String accessToken = oAuthDetails.getTokenValue();
//		User currentUser = (User)SecurityContextHolder.getContext()
//													.getAuthentication()
//													.getPrincipal();
//
//		meal.setUserid(currentUser.getId());
//		mealInfoService.insertMeal(meal);
//		
		return null;
	}
	


	//update meal 
	@RequestMapping(value = "/api/meal/{id}", method = RequestMethod.PUT)
	public void updateMeal(@RequestBody MealInfoObject meal, @PathVariable int id) {
		mealInfoService.updateMeal(meal);
	}
	
	//delete meal
	@RequestMapping(value = "/api/meal/{id}", method = RequestMethod.DELETE)
	public void deleteMeal(@PathVariable int id) {
		
		mealInfoService.deleteMeal(id);
	}

	//ShoppingList
	@RequestMapping(value = "/api/lists/shoppingList", method = RequestMethod.GET)
	public List<Product> getShoppingList(){
		
		return 	mealInfoService.getShoppingList();

	}
	
	@RequestMapping(value = "/api/usergoals/{userid}", method = RequestMethod.GET)
	public List<UserGoals> getGoals(@PathVariable int userid){
		return userDAO.getAllGoals(userid);
	}
	
	//statistics
	
	
	
	
	
}
