package workoutconnection.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_access_token")
public class OAuthAccessToken {
    @Id
    private String authentication_id;
    private String token_id;
    @Lob
    private byte[] token;
    private String user_name;
    private String client_id;
    @Lob
    private byte[] authentication;
    private String refresh_token;
}
