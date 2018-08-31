package workoutconnection.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workoutconnection.dao.IProductDAO;
import workoutconnection.entities.Product;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductDAO productDAO;
	@Override
	public List<Product> getAllProducts() {

		return productDAO.getAllProducts();
	}
	@Override
	public Product getProduct(int id) {
		return productDAO.getProduct(id);
	}
	@Override
	public Product insertProduct(Product product) {
		productDAO.insertProduct(product);
		
		return product;
	}
	@Override
	public void deleteById(int id) {
		productDAO.deleteById(id);
		
	}
	@Override
	public void update(Product product) {
		productDAO.update(product);
		
	}

	
}
