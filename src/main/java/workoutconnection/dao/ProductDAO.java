package workoutconnection.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import workoutconnection.entities.Product;

@Transactional
@Repository
public class ProductDAO implements IProductDAO{

	@Autowired
	private EntityManager entityManager;

    @Override
    public Product save(Product product) {
        entityManager.persist(product);
        return product;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts(){
		return (List<Product>)entityManager
				.createQuery("From Product")
				.getResultList();
	}

	@Override
	public Product getProduct(int id) {
		return (Product)entityManager.createQuery("From Product WHERE id = :id")
                .setParameter("id",id)
                .getSingleResult();
	}

	@Override
	public void deleteById(int id) {
		Product p = entityManager.find(Product.class, id);
		entityManager.remove(p);

	}

	@Override
	public void update(Product product) {
		entityManager.merge(product);
	}


	public List<Product> findProductByName(String productName) {
		List<Product> products = (List<Product>)entityManager
				.createQuery("FROM Product " +
						"WHERE lower(name) LIKE lower(:param)")
				.setParameter("param","%"+productName + "%")
				.getResultList();
		return products;


	}
}
