package workoutconnection.service;

import java.util.List;

import workoutconnection.dto.ProductDto;
import workoutconnection.entities.Product;

public interface IProductService {

	List<ProductDto> getAllProducts();

	ProductDto getProduct(int id);

	ProductDto insertProduct(ProductDto product);

	void deleteById(int id);

	void update(Product product);

	List<ProductDto> findProductByName(String productName);
}
