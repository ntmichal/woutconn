package workoutconnection.dao;

import java.util.List;

import workoutconnection.entities.Product;

public interface IProductDAO {
	/**
	 *
	 * @return return list of all products in database
	 */
	List<Product> getAllProducts();

	List<Product> findProductByName(String productName);
	/**
	 *
	 * @param id of single record in database
	 * @return	return single object Product
	 */
	Product getProduct(Long id);

	Product save(Product product);

	void deleteById(Long id);

	void update(Product product);

}
