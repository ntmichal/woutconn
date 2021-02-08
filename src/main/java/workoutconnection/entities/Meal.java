package workoutconnection.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.modelmapper.ModelMapper;
import workoutconnection.dto.MealDto;

import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "Meal")
public class Meal {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="meal_date")
	private LocalDate mealDate;

	@OneToMany(mappedBy = "meal",
				cascade = CascadeType.ALL,
				orphanRemoval = true)

	private List<MealsList> mealsList = new ArrayList<>();

	public Meal(){};
	public Meal(String name, List<MealsList> mealsList) {
		this.name = name;
		this.mealsList = mealsList;
	}


	public void addProduct(Product product, int weight){
		MealsList meals = new MealsList(this,product,weight);
		this.mealsList.add(meals);
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<MealsList> getMealsList() {
		return mealsList;
	}

	public void setMealsList(List<MealsList> mealsList) {
		this.mealsList = mealsList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getMealDate() {
		return mealDate;
	}

	public void setMealDate(LocalDate mealDate) {
		this.mealDate = mealDate;
	}


	@Override
	public String toString() {
		return "Meal [id=" + id + ", name=" + name + ", user_id= , mealDate=" + mealDate
				+ ", association=] \n";
	}

	public MealDto convertToDto(){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, MealDto.class);
	}

	public static MealBuidler builder(){
		return new MealBuidler();
	}

	public static final class MealBuidler{

		Meal meal = new Meal();
		private int id;
		private String name;
		private LocalDate mealDate;
		private User user;
		private List<MealsList> MealsList = new ArrayList<>();

		public MealBuidler setName(String name){
			this.name = name;
			return this;
		}
		public MealBuidler setMealDate(LocalDate mealDate){
			this.mealDate = mealDate;
			return this;
		}
		public MealBuidler addProduct(Product product,int productWeight){
			this.MealsList.add(new MealsList(meal,product,productWeight));
			return this;
		}
		public MealBuidler addUser(User user){
			this.user = user;
			return this;
		}
		public MealBuidler setId(int id){
			this.id = id;
			return this;
		}
		public Meal build(){
			meal.setId(id);
			meal.setName(name);
			meal.setMealDate(mealDate);
			meal.setMealsList(this.MealsList);
			meal.setUser(user);
			return meal;
		}
	}

}
