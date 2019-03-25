package workoutconnection.models;
import java.util.ArrayList;
import java.util.List;

import workoutconnection.entities.Meal;
import workoutconnection.entities.Product;
import workoutconnection.entities.User;

public class MealInfoObject {

	public Meal meal;
	public int userid;
	public List<Product> products; 
	public List<Double> weightOfProducts;

	public MealInfoObject() {
		products = new ArrayList<Product>();
		weightOfProducts = new ArrayList<Double>();
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

	public List<Double> getWeightOfProducts() {
		return weightOfProducts;
	}

	public void setWeightOfProducts(List<Double> weightOfProducts) {
		this.weightOfProducts = weightOfProducts;
	}

	public void addWeigth(Double weigth){
		this.weightOfProducts.add(weigth);
	}
	@Override
	public String toString() {
		return "MealInfoObject [meal=" + meal + ", products=" + products + "]";
	}
	
}
