package workoutconnection.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{


	
	static final String client_id = "workoutconnection-client";
	//static final String client_secret = "$2y$04$RiHVAIE1tJtYtFwoJtntheX5CSGjM7MS3oGsizGQa.JQ26fcF0qaO";//to moje
	//static final String client_secret = "$2a$04$e/c1/RfsWuThaWFCrcCuJeoyvwCV0URN/6Pn9ZFlrtIWaU/vj/BfG";
	// String client_secret = "workoutconnection-secret";
	static final String client_secret = "$2a$10$fJE7NVz.nvFWxKRjJ4ao7uE.OqBMiy.D2xSrb/wGvbcUqixUVniQa";
	static final String password = "password";
	static final String authorization_code = "authorization_code";
	static final String refresh_token = "refresh_token";
	static final String implict = "implict";
	static final String scope_read = "read";
	static final String scope_write = "write";
	static final String trust = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
    
    @Autowired
    private TokenStore tokenStore;
	
    @Autowired
    private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			.withClient(client_id)
			.secret(client_secret)
			.authorizedGrantTypes(password, authorization_code, refresh_token, implict)
			.scopes(scope_read, scope_write)
			.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
			.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore)
			.authenticationManager(authenticationManager);
	}

    
}
