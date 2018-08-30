package workoutconnection.entities;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="barcode")
	private String barcode;
	
	@Column(name="proteins")
	private int proteins;
	
	@Column(name="carbs")
	private int carbs;
	
	@Column(name="fats")
	private int fats;
	
	@Column(name="kcal")
	private int kcal;
	
	@Column(name="volume")
	private int volume;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="product")
	private Set<MealInfo> association;
	
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
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public int getProteins() {
		return proteins;
	}
	public void setProteins(int proteins) {
		this.proteins = proteins;
	}
	public int getCarbs() {
		return carbs;
	}
	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}
	public int getFats() {
		return fats;
	}
	public void setFats(int fats) {
		this.fats = fats;
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
//	public Meal getMeal() {
//		return meal;
//	}
//	public void setMeal(Meal meal) {
//		this.meal = meal;
//	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.getName().hashCode();
	}
	@Override
	public boolean equals(Object obj) {

		Product xd = (Product)obj;

		return this.getName().compareTo(xd.getName()) == 0 ? true : false;

	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", barcode=" + barcode + ", proteins=" + proteins + ", carbs="
				+ carbs + ", fats=" + fats + ", kcal=" + kcal + ", volume=" + volume + ", association=" + association
				+ "]\n";
	}

	
}
