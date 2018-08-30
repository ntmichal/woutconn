package workoutconnection;
import java.util.ArrayList;
import java.util.List;

import workoutconnection.entities.Meal;
import workoutconnection.entities.Product;
import workoutconnection.entities.User;

public class MealInfoObject {

	public Meal meal;
	public int userid;
	public List<Product> products; 
	
	public MealInfoObject() {
		products = new ArrayList<Product>();
	}
	
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	} 
	
	public void addProduct(Product product) {
		this.products.add(product);
	}


	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "MealInfoObject [meal=" + meal + ", products=" + products + "]";
	}
	
}
