package workoutconnection.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import workoutconnection.entities.Meal;
import workoutconnection.entities.MealInfo;
import workoutconnection.entities.Product;
import workoutconnection.entities.User;
import workoutconnection.models.MealInfoObject;


@Repository
@Transactional
public class MealInfoDAO implements IMealInfoDAO {

	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MealInfoObject> getAllMeals(int userId) {
		String HQL = "From MealInfo WHERE user_id = :user_id ORDER BY meal_id";
		List<MealInfo> mealInfo =  (List<MealInfo>)entityManager
				.createQuery(HQL)
				.setParameter("user_id", userId)
				.getResultList();

				
		List<MealInfoObject> mio2 = new ArrayList<MealInfoObject>();
	
		int indexer = 0;
		MealInfoObject mx = new MealInfoObject();
		for(MealInfo i : mealInfo) {
			
			mx.setMeal(i.getMeal());
			mx.setUserid(userId);
	
			
			if( (indexer + 1) < mealInfo.size() &&
					mealInfo.get(indexer).getMeal().getId() == mealInfo.get(indexer + 1).getMeal().getId()) {
				mx.addProduct(mealInfo.get(indexer).getProduct());
				mx.addWeigth(mealInfo.get(indexer).getProduct_weigth());
				indexer++;
			}else {
				mx.addProduct(mealInfo.get(indexer).getProduct());
				mx.addWeigth(mealInfo.get(indexer).getProduct_weigth());
				mio2.add(mx);
				mx = new MealInfoObject();
				mx.setMeal(i.getMeal());
				indexer++;
			}

				

		}
	
		return mio2;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public MealInfoObject getMeal(int id) {
		
	
		
		String HQL = "From MealInfo WHERE meal_id = " + id;
		List<MealInfo> mealInfo = (List<MealInfo>)entityManager
				.createQuery(HQL)
				.getResultList();
				
		MealInfoObject m = new MealInfoObject();
		for(MealInfo i : mealInfo) {	
			if(id == i.getMeal().getId()) {
				if(m.getMeal() == null) {
					m.setMeal(i.getMeal());
				}
				m.addProduct(i.getProduct());
				
			}

		}
		
		return m;
	}

	@Override
	public void deleteMeal(int id) {
		String HQL = "DELETE FROM MealInfo WHERE meal_id = " + id;
		entityManager.createQuery(HQL).executeUpdate();

	}

	@Override
	public void updateMeal(MealInfoObject meal) {
		String HQL = "DELETE FROM MealInfo WHERE meal_id = " + meal.getMeal().getId();

		entityManager.createQuery(HQL).executeUpdate();

		
		
		this.insertMeal(meal);
	}

	@Override
	public void insertMeal(MealInfoObject meal) {
		int k = 0;

		String HQL = "From User WHERE id = :id";	
		Query q = (Query)entityManager.createQuery(HQL);
		q.setParameter("id", meal.getUserid());
		User user = (User)q.getSingleResult();
		
		MealInfo tempMealInfo = new MealInfo();
		meal.meal.setUser(user);
		tempMealInfo.setUser(user);
		tempMealInfo.setMeal(meal.getMeal());

		int j = 0;
		for(Product i : meal.getProducts()) {			
			tempMealInfo.setProduct(i);
			tempMealInfo.setProduct_weigth(meal.weightOfProducts.get(j));
			if(k == 0) {
				k = entityManager.merge(tempMealInfo).getMeal().getId();
				tempMealInfo.getMeal().setId(k);
				continue;
			}
			entityManager.merge(tempMealInfo);
			j++;
		}
	}
}
