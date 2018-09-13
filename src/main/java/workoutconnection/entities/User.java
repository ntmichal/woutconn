package workoutconnection.entities;


import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Column(name = "ACCOUNT_EXPIRED")
    private boolean accountExpired;

    @Column(name = "ACCOUNT_LOCKED")
    private boolean accountLocked;

    @Column(name = "CREDENTIALS_EXPIRED")
    private boolean credentialsExpired;

    @Column(name = "ENABLED")
    private boolean enabled;
    
//    @Transient
//    private List<SimpleGrantedAuthority> authority;
    
//    public User(String username, String password, List<SimpleGrantedAuthority> authority) {
// 
//		this.username = username;
//		this.password = password;
//
//	}

//	public User(int id, String username, String password, boolean accountExpired, boolean accountLocked,
//			boolean credentialsExpired, boolean enabled) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.accountExpired = accountExpired;
//		this.accountLocked = accountLocked;
//		this.credentialsExpired = credentialsExpired;
//		this.enabled = enabled;
//	}

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



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getId() + " -------------------- " + this.getUsername() + "------------------ " + this.getPassword();
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}





	
	
}
