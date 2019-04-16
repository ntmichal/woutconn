package workoutconnection.dao;

import java.util.List;

import workoutconnection.entities.Product;

public interface IProductDAO {
	/**
	 *
	 * @return return list of all products in database
	 */
	List<Product> getAllProducts();

	/**
	 *
	 * @param id of single record in database
	 * @return	return single object Product
	 */
	Product getProduct(int id);

	Product save(Product product);

	void deleteById(int id);

	void update(Product product);
}
