package workoutconnection.service;

import java.util.List;

import workoutconnection.dto.ProductDto;
import workoutconnection.entities.Product;

public interface IProductService {

	List<ProductDto> getAllProducts();

	ProductDto getProduct(Long id);

	ProductDto insertProduct(ProductDto product);

	void deleteById(Long id);

	void update(ProductDto product);

	List<ProductDto> findProductByName(String productName);
}
