package workoutconnection.entities;


import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
public class Meal {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="meal_date")
	private LocalDate mealDate;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="meal")
	private Set<MealInfo> association;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
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

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Integer,Integer> sortByMonth() {
	
		int year = this.getMealDate().getYear();

		int month = this.getMealDate().getMonth().getValue();

		
		Map<Integer,Integer> yearMonth= new HashMap<Integer,Integer>();
		yearMonth.put(year, month);
		
		System.out.println("year " + year + "month " + month + "hash: " + yearMonth.hashCode());
		return yearMonth;
	}

	@Override
	public String toString() {
		return "Meal [id=" + id + ", name=" + name + ", user_id= , mealDate=" + mealDate
				+ ", association=" + association + "] \n";
	}
	
	
	
}
