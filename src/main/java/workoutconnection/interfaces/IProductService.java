package workoutconnection.interfaces;

import java.util.List;

import workoutconnection.entities.Product;

public interface IProductService {
	
	List<Product> getAllProducts();
	
	Product getProduct(int id);
	
	Product insertProduct(Product product);
	
	void deleteById(int id);
	
	void update(Product product);
}
