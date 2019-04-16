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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@Column(name="workout_goal")
	private String workoutGoal;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "flag_is_active")
	private boolean isActive;

	@Column(name = "calories_intake")
	private int caloriesIntake;

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

	public String getWorkoutGoal() {
		return workoutGoal;
	}

	public void setWorkoutGoal(String workoutGoal) {
		this.workoutGoal = workoutGoal;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getCaloriesIntake() {
		return caloriesIntake;
	}

	public void setCaloriesIntake(int caloriesIntake) {
		this.caloriesIntake = caloriesIntake;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}


	public static UserGoalsBuilder builder(){return new UserGoalsBuilder();};
	public static final class UserGoalsBuilder{

		private int id;
		private User user;
		private String workoutGoal;
		private LocalDate startDate;
		private LocalDate endDate;
		private boolean isActive;
		private int caloriesIntake;

		public UserGoalsBuilder setId(int id){
			this.id = id;
			return this;
		}
		public UserGoalsBuilder setUser(User user){
			this.user = user;
			return this;
		}
		public UserGoalsBuilder setWorkoutGoal(String workoutGoal){
			this.workoutGoal = workoutGoal;
			return this;
		}
		public UserGoalsBuilder setStartDate(LocalDate startDate){
			this.startDate = startDate;
			return this;
		}
		public UserGoalsBuilder setEndDate(LocalDate endDate){
			this.endDate = endDate;
			return this;
		}
		public UserGoalsBuilder setIsActive(boolean isActive){
			this.isActive = isActive;
			return this;
		}
		public UserGoalsBuilder setCaloriesIntake(int caloriesIntake){
			this.caloriesIntake = caloriesIntake;
			return this;
		}

		public  UserGoals build(){
			UserGoals userGoals = new UserGoals();
			userGoals.setId(this.id);
			userGoals.setUser(this.user);
			userGoals.setWorkoutGoal(this.workoutGoal);
			userGoals.setStartDate(this.startDate);
			userGoals.setEndDate(this.endDate);
			userGoals.setCaloriesIntake(this.caloriesIntake);
			userGoals.setIsActive(this.isActive);

			return userGoals;
		}
	}
}
