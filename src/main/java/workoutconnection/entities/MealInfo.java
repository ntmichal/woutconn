package workoutconnection.entities;

import javax.persistence.*;

@Entity
@Table(name="mealinfo")
public class MealInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name = "mealId")
	private Meal meal;
	
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name = "productId")
	private Product product;

	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;

	@Column(name = "product_weigth")
	private double product_weigth;

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getProduct_weigth() {
		return product_weigth;
	}

	public void setProduct_weigth(double product_weigth) {
		this.product_weigth = product_weigth;
	}

	@Override
	public int hashCode() {
		return this.getProduct().getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		MealInfo xd = (MealInfo)obj;

		return this.getProduct().equals(xd.getProduct());
	}

	
}