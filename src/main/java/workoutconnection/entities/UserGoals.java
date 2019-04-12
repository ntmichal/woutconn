package workoutconnection.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_goals")
public class UserGoals {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@Column(name="workout_goal")
	private String workout_goal;

	@Column(name = "start_date")
	private LocalDate start_date;

	@Column(name = "end_date")
	private LocalDate end_date;

	@Column(name = "flag_is_active")
	private int isActive;

	@Column(name = "calories_intake")
	private int calories_intake;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getWorkout_goal() {
		return workout_goal;
	}

	public void setWorkout_goal(String workout_goal) {
		this.workout_goal = workout_goal;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getCalories_intake() {
		return calories_intake;
	}

	public void setCalories_intake(int calories_intake) {
		this.calories_intake = calories_intake;
	}
}
