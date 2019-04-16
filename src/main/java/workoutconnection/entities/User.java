package workoutconnection.entities;


import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;





@Entity
@Table(name="users")
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name="EMAIL")
    private String email;
    
    @Column(name = "ACCOUNT_EXPIRED")
    private boolean accountExpired;

    @Column(name = "ACCOUNT_LOCKED")
    private boolean accountLocked;

    @Column(name = "CREDENTIALS_EXPIRED")
    private boolean credentialsExpired;

    @Column(name = "ENABLED")
    private boolean enabled;


	@Column(name="authorities")
	@ManyToMany(cascade = {CascadeType.PERSIST,
		CascadeType.MERGE},
		fetch = FetchType.EAGER)
	@JoinTable(name="authoritiesList",
		joinColumns = @JoinColumn(name="authority_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
    private List<Authority> authorityList;


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Meal> mealList;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Measurement> measurementList;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserGoals> goalsList;

	public List<UserGoals> getGoalsList() {
		return goalsList;
	}

	public void setGoalsList(List<UserGoals> goalsList) {
		this.goalsList = goalsList;
	}

	public List<Measurement> getMeasurementList() {
		return measurementList;
	}

	public void setMeasurementList(List<Measurement> measurementList) {
		this.measurementList = measurementList;
	}

	public List<Meal> getMealList() {
		return mealList;
	}

	public void setMealList(List<Meal> mealList) {
		this.mealList = mealList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isAccountExpired() {
		return accountExpired;
	}



	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}



	public boolean isAccountLocked() {
		return accountLocked;
	}



	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}



	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}



	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return this.getId() + " - " + this.getUsername() + "- " + this.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) authorityList;
	}



	@Override
	public boolean isAccountNonExpired() {

		return !this.accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {

		return !this.accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return !this.credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}
}
