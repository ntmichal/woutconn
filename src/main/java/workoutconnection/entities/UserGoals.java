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
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@Column(name="cel_treningowy")
	private String cel_treningowy;
	
	@Column(name = "data_rozpoczecia")
	private LocalDate data_rozpoczecia;
	
	@Column(name = "data_zakonczenia")
	private LocalDate data_zakonczenia;
	
	@Column(name = "flag_is_active")
	private int isActive;
	
	@Column(name = "zapotrzebowanie_kcal")
	private int zapotrzebowanie_kcal;
	
	
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
	public String getCel_treningowy() {
		return cel_treningowy;
	}
	public void setCel_treningowy(String cel_treningowy) {
		this.cel_treningowy = cel_treningowy;
	}
	public LocalDate getData_rozpoczecia() {
		return data_rozpoczecia;
	}
	public void setData_rozpoczecia(LocalDate data_rozpoczecia) {
		this.data_rozpoczecia = data_rozpoczecia;
	}
	public LocalDate getData_zakonczenia() {
		return data_zakonczenia;
	}
	public void setData_zakonczenia(LocalDate data_zakonczenia) {
		this.data_zakonczenia = data_zakonczenia;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getZapotrzebowanie_kcal() {
		return zapotrzebowanie_kcal;
	}
	public void setZapotrzebowanie_kcal(int zapotrzebowanie_kcal) {
		this.zapotrzebowanie_kcal = zapotrzebowanie_kcal;
	}
	
	
	
}
