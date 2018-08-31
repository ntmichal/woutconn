package workoutconnection;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import workoutconnection.dao.UserGoalsDAO;
import workoutconnection.entities.MealInfo;
import workoutconnection.entities.Product;
import workoutconnection.entities.UserGoals;
import workoutconnection.interfaces.MealInfoService;
import workoutconnection.interfaces.ProductService;


/**
 * 
 * 
 * @author Michał Maciocha
 * 
 */

@RestController
public class DietController {


	
	@Autowired
	private ProductService productService;
	@Autowired
	private MealInfoService mealInfoService;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private UserGoalsDAO userDAO;
	
	//first of all shuld be done list od products
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/api/product/list")
	//@RequestMapping(value = "/api/product/list", method = RequestMethod.GET)
	public List<Product> productList(){

		return productService.getAllProducts();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/api/xd")
	public List<MealInfo> mealinfolist(){
		return (List<MealInfo>)entityManager.createQuery("From MealInfo ").getResultList();
	}
	@RequestMapping(value = "/api/product/{id}", method = RequestMethod.GET)
	public Product findProduct(@PathVariable int id) {
		

		return productService.getProduct(id);
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
	 * @return list of products
	 */
	@RequestMapping(value = "/api/meal/{userid}", method = RequestMethod.GET)
	public List<MealInfoObject> mealsList(@PathVariable int userid){

		return mealInfoService.getAllMeals(userid);
	}

	
	//get 1 
	@RequestMapping(value = "/api/meal/{userid}/{id}", method = RequestMethod.GET)
	public MealInfoObject getMeal(@PathVariable int userid, @PathVariable int id) {
		
		
		return mealInfoService.getMeal(id);
	}

	////////////// jeszcze nie wiem
	
	//insert meal
	@RequestMapping(value = "/api/meal/", method = RequestMethod.POST)
	public ResponseEntity<Object> createMeal(@RequestBody MealInfoObject meal){
	
			mealInfoService.insertMeal(meal);
		
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
//statystyki mordooo!
//	@RequestMapping(value = "/api/lists/statistics", method = RequestMethod.GET)
//	public Map<String, Object> Statistics() {
//		List<MealInfoObject> mio = this.mealsList();
//		
//		int carbs = 0;
//		int proteins = 0;
//		int fats = 0;
//		
//
//		
//		for(int i = 0; i < mio.size() ; i++) {
//			if(mio.get(i) != null) {
//				for(Product lP : mio.get(i).getProducts()) {
//					carbs += lP.getCarbs();
//					proteins += lP.getProteins();
//					fats += lP.getFats();
//				}
//			}
//
//		}
//		//dzień
//
//		int dayCalories = 0;
//		
//		
//		int weekCalories = 0;
//		
//		//tydzień
//		String output = "carbs: " + carbs + "\n" +
//							"proteins: " + proteins + "\n" +
//							"fats:" + fats + "\n" +
//							"dayCalories:" + dayCalories + "\n"+
//							"weekCalories:" + weekCalories + "\n";
//	
//		
//
//		
//		Map<Object, Integer> getCarbs =
//				mio.stream()
//						.collect(
//								Collectors.groupingBy(p -> p.meal.getMealDate(),
//										Collectors.summingInt(p -> p.products.stream()
//												.collect(Collectors.summingInt(b -> b.getCarbs())))
//										));
//		Map<Object, Integer> getFats =
//				mio.stream()
//						.collect(
//								Collectors.groupingBy(p -> p.meal.getMealDate(),
//										Collectors.summingInt(p -> p.products.stream()
//												.collect(Collectors.summingInt(b -> b.getFats())))
//										));
//		Map<Object, Integer> getProteins =
//				mio.stream()
//				.collect(
//						Collectors.groupingBy(p -> p.meal.getMealDate(),
//								Collectors.summingInt(p -> p.products.stream()
//										.collect(Collectors.summingInt(b -> b.getProteins())))
//								));
//		
//		
//		
////		Map<Object, List<MealInfoObject>> groupingByDay =
////				mio.stream().collect(Collectors.groupingBy(p -> p.meal.getMealDate(),
////						Collectors.counting((a,b) -> new MealSum(a.getCarbs() + b.getCarbs() ,
////								a.getFats() + b.getFats(),
////								a.getProteins() + b.getProteins()
////							)))
////						);
//		
//
//		Map<Object, Long> gett =
//				mio.stream()
//				.collect(
//						Collectors.groupingBy(p -> p.meal.getMealDate(),
//								Collectors.counting())
//								);
//					
//		
//        
//                		
//                //		(f1,f2) -> new MealSum(f1.getCarbs() + f2.getCarbs() ,f1.getFats() + f2.getFats(),f1.getProteins() + f2.getProteins())
//
//   					
//										
////            		(f1,f2) -> new MealSum(f1.getCarbs() + f2.getCarbs() ,f1.getFats() + f2.getFats(),f1.getProteins() + f2.getProteins())
//        //))
//		
//		
//		DateTimeFormatter fmt = new DateTimeFormatterBuilder()
//			    // month-year
//			    .appendPattern("MM-yyyy")
//			    // default value for day
//			    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
//			    // create formatter
//			    .toFormatter();
//
//		@SuppressWarnings("static-access")
//		Map<Object, Integer> groupingByMonth =
//				mio.stream()
//						.collect(
//								Collectors.groupingBy(p -> {
//									return p.meal.getMealDate()
//											.parse(p.meal.getMealDate().format(fmt), 
//													fmt);
//								}  ,
//										Collectors.summingInt(p -> p.products.stream()
//												.collect(Collectors.summingInt(b -> b.getKcal())))
//										));
//
//		Map<Object, Integer> groupingByWeek =
//				mio.stream()
//						.collect(
//								Collectors.groupingBy(p -> p.meal.sortByWeek() ,
//										Collectors.summingInt(p -> p.products.stream()
//												.collect(Collectors.summingInt(b -> b.getKcal())))
//										));
//		
//		Map<String, Object> listOfObjects = new HashMap<String, Object>();
//		listOfObjects.put("Calories per day" , gett);
//		listOfObjects.put("Calories per week" , groupingByWeek);
//		listOfObjects.put("Calories per month" , groupingByMonth);
//		listOfObjects.put("Calories per montzxczxczxch" , gett);
//		listOfObjects.put("CARBS", getCarbs);
//		listOfObjects.put("FATS", getFats);
//		listOfObjects.put("PROTEINS", getProteins);
//		return listOfObjects;
//		
//	
//	}
	
	
	
	
	
}
