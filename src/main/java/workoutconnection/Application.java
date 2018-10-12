package workoutconnection;





import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@SpringBootApplication
public class Application {



	
    public static void main(String[] args) {
    	System.setProperty("spring.devtools.restart.enabled", "true"); 
        SpringApplication.run(Application.class, args);

    }



}