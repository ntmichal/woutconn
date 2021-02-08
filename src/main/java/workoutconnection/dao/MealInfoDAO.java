package workoutconnection.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import workoutconnection.entities.Meal;
import workoutconnection.entities.Product;
import workoutconnection.entities.User;



@Repository
@Transactional
public class MealInfoDAO implements IMealInfoDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public void insertMeal(Meal meal) {
		entityManager.merge(meal);
	}
	@Override
	public Meal insertMeal(Meal meal, int id) {
		meal.setUser(entityManager.find(User.class,id));
 		return entityManager.merge(meal);
	}

	@Override
	public Meal insertProductToMeal(Meal meal, int productId, int productVolume){
		meal.addProduct(
				entityManager.find(Product.class, productId),
				productVolume
		);
		return meal;
	}

    @Override
	public List<Meal> getAllMeals(int userId){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Meal> mealCriteriaQuery = cb.createQuery(Meal.class);
		Root<Meal> meal = mealCriteriaQuery.from(Meal.class);

		Subquery<User> subquery = mealCriteriaQuery.subquery(User.class);
		Root<User> user = subquery.from(User.class);
		subquery.select(user).where(cb.equal(user.get("id"),userId));

		mealCriteriaQuery.select(meal).where(cb.in(meal.get("user")).value(subquery));

		return (List<Meal>)entityManager.createQuery(mealCriteriaQuery).getResultList();

	}


	@Override
	public List<Meal> getMealByDate(int userId, LocalDate localDate) {

		return (List<Meal>)entityManager.createQuery("From Meal " +
				"WHERE mealDate = :mealDate AND user.id = :userId")
				.setParameter("mealDate", localDate)
				.setParameter("userId",userId)
				.getResultList();


	}

	@Override
	public Meal getMeal(int id, int userId) {
	    return (Meal)entityManager
                .createQuery("From Meal WHERE id = :id AND user.id = :userId")
                .setParameter("id",id)
				.setParameter("userId", userId)
				.getSingleResult();
	}


	@Override
	public List<Product> getMealProducts(int id) {
		List<Product> object = (List<Product>) entityManager
				.createQuery("select new Product(product.id, " +
						"product.name, " +
						"product.barcode, " +
						"product.proteins, " +
						"product.carbs, " +
						"product.fats, " +
						"product.kcal, " +
						"product.volume) " +
						"From MealsList WHERE meal.id = :id ")
				.setParameter("id", id)
				.getResultList();
		return object;

	}
	@Override
	public void deleteMeal(int id) {
		Meal meal = entityManager.find(Meal.class,id);

		entityManager.remove(meal);
	}

	@Override
	public void updateMeal(Meal meal) {
		entityManager.merge(meal);
	}


}
