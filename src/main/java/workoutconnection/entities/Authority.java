package workoutconnection.entities;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable = false)
	@JsonIgnore
	private int id;

	@Column(name="role")
	private String role;

	@Column(name="users")
	@ManyToMany(cascade = {CascadeType.PERSIST,
							CascadeType.MERGE},
				fetch = FetchType.EAGER,
				mappedBy = "authorityList")
	private List<User> usersList;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<User> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	@Override
	public String getAuthority() {
		return role;
	}
	
	
	public static AuthorityBuilder builder(){
		return new AuthorityBuilder();
	}

	public static  final class AuthorityBuilder{
		private int id;
		private String role;

		public AuthorityBuilder setAuthority(String role){
			this.role = role;
			return this;
		}

		public Authority build(){
			Authority authority = new Authority();
			authority.setRole(this.role);
			return authority;
		}

	}
}