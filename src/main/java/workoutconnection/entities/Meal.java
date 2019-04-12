package workoutconnection.entities;


import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name="xyz")
	@ManyToMany(cascade = {CascadeType.PERSIST},
				fetch = FetchType.EAGER)
	@JoinTable(name = "meals_list",
				joinColumns = @JoinColumn(name = "product_id"),
				inverseJoinColumns = @JoinColumn(name = "meal_id"))
	private List<Product>  productList;

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

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
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

	public static MealBuidler builder(){
		return new MealBuidler();
	}
	public static final class MealBuidler{

		private int id;
		private String name;
		private LocalDate mealDate;
		private User user;
		private List<Product> productList = new ArrayList<>();

		public MealBuidler setName(String name){
			this.name = name;
			return this;
		}
		public MealBuidler setMealDate(LocalDate mealDate){
			this.mealDate = mealDate;
			return this;
		}
		public MealBuidler addProduct(Product product){
			this.productList.add(product);
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
			Meal meal = new Meal();
			meal.setId(id);
			meal.setName(name);
			meal.setMealDate(mealDate);
			meal.setProductList(this.productList);
			meal.setUser(user);
			return meal;
		}
	}

}
