package workoutconnection;





import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	System.setProperty("spring.devtools.restart.enabled", "true"); 
        SpringApplication.run(Application.class, args);
    }



}