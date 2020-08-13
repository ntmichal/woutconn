package workoutconnection.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="oauth_refresh_token")
public class OAuthRefreshToken {

    @Id
    private String token_id;

    @Lob
    private byte[] token;

    @Lob
    private byte[] authentication;
}
