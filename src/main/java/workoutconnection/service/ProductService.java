package workoutconnection.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workoutconnection.dao.IProductDAO;
import workoutconnection.dto.ProductDto;
import workoutconnection.entities.Product;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductDAO productDAO;


	@Override
	public List<ProductDto> getAllProducts() {

		return productDAO.getAllProducts()
				.stream()
				.map(Product::convertToDto)
				.collect(Collectors.toList());
	}
	@Override
	public ProductDto getProduct(int id) {
		return productDAO.getProduct(id).convertToDto();
	}
	@Override
	public ProductDto insertProduct(ProductDto product) {
		Product productToSave = product.convertToEntity();
		productToSave.setId(null);
		productDAO.save(productToSave);

		return product;
	}
	@Override
	public void deleteById(int id) {
		productDAO.deleteById(id);

	}
	@Override
	public void update(ProductDto productDto) {
		Product product = productDto.convertToEntity();
		productDAO.update(product);

	}

	@Override
	public List<ProductDto> findProductByName(String productName) {

		List<Product> products = productDAO.findProductByName(productName);
		List<ProductDto> productDtoList = products
				.stream()
				.map(Product::convertToDto)
				.collect(Collectors.toList());

		return productDtoList;
	}

}
