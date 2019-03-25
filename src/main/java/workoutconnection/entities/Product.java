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
	private float proteins;
	
	@Column(name="carbs")
	private float carbs;
	
	@Column(name="fats")
	private float fats;
	
	@Column(name="kcal")
	private float kcal;
	
	@Column(name="volume")
	private float volume;
	
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
	public float getProteins() {
		return proteins;
	}
	public void setProteins(float proteins) {
		this.proteins = proteins;
	}
	public float getCarbs() {
		return carbs;
	}
	public void setCarbs(float carbs) {
		this.carbs = carbs;
	}
	public float getFats() {
		return fats;
	}
	public void setFats(float fats) {
		this.fats = fats;
	}
	public float getKcal() {
		return kcal;
	}
	public void setKcal(float kcal) {
		this.kcal = kcal;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}

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
