package workoutconnection.interfaces;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import workoutconnection.entities.Product;




@Transactional
@Repository
public class ProductDAO implements IProductDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts(){
		
		String hibernateQueryLangugageXD = "From Product";
		return (List<Product>)entityManager
				.createQuery(hibernateQueryLangugageXD)
				.getResultList();
	}

	@Override
	public Product getProduct(int id) {
		String hql ="From Product where id = "+id;
		return (Product)entityManager.createQuery(hql).getSingleResult();
	}

	@Override
	public Product insertProduct(Product product) {

		entityManager.persist(product);
		return product;
	}

	@Override
	public void deleteById(int id) {
		
		Product p = entityManager.find(Product.class, id);
		entityManager.remove(p);
		
	}

	@Override
	public void update(Product product) {
		//Product findOne = entityManager.find(Product.class, product.getId());
		entityManager.merge(product);
		
	}
	
}
