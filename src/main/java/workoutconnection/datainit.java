package workoutconnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import workoutconnection.dao.*;
import workoutconnection.entities.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class datainit implements ApplicationRunner {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MealInfoDAO mealInfoDAO;

    @Autowired
    private IProductDAO productDAO;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserInfoDAO userInfoDAO;
    public static User GenerateUser(){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setUsername("ADMIN");
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        user.setEnabled(true);
        user.setCredentialsExpired(false);
        user.setAccountExpired(false);

        List<Authority> lAuthorities = new ArrayList<>();
        lAuthorities.add(Authority.builder().setAuthority("ROLE_ADMIN").build());
        lAuthorities.add(Authority.builder().setAuthority("ROLE_USER").build());
        user.setAuthorityList(lAuthorities);


        return user;
    }
    public static User GenerateUser2(){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmail("admin2@gmail.com");
        user.setUsername("ADMIN2");
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        user.setEnabled(true);
        user.setCredentialsExpired(false);
        user.setAccountExpired(false);

        List<Authority> lAuthorities = new ArrayList<>();
        lAuthorities.add(Authority.builder().setAuthority("ROLE_ADMIN").build());
        lAuthorities.add(Authority.builder().setAuthority("ROLE_USER").build());
        user.setAuthorityList(lAuthorities);


        return user;
    }
    public static UserGoals GenerateGoals(EntityManager entityManager){
        UserGoals ug = UserGoals.builder()
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusMonths(5))
                .setCaloriesIntake(3500)
                .setWorkoutGoal("GAIN WEIGHT")
                .setIsActive(true)
                .setUser(entityManager.find(User.class,1))
                .build();

        return ug;
    }
    public static Meal GenerateMeal(EntityManager entityManager){
        Meal meal = Meal.builder()
                .setName("Sniadanie")
                .setMealDate(LocalDate.now())
                .addUser(entityManager.find(User.class,1))
                .addProduct(entityManager.find(Product.class, 1),100)
                .addProduct(entityManager.find(Product.class, 2),100)
                .addProduct(entityManager.find(Product.class, 3),50)
                .addProduct(entityManager.find(Product.class, 2),50)
                .addProduct(entityManager.find(Product.class, 3),50)
                .addProduct(entityManager.find(Product.class, 2),50)
                .addProduct(entityManager.find(Product.class, 3),50)
                .addProduct(entityManager.find(Product.class, 2),50)
                .addProduct(entityManager.find(Product.class, 3),50)
                .build();
        return meal;
    }
    public static Meal GenerateMeal2(EntityManager entityManager){
        Meal meal = Meal.builder()
                .setName("Sniadanie2")
                .setMealDate(LocalDate.now())
                .addUser(entityManager.find(User.class,1))
                .addProduct(entityManager.find(Product.class, 1),50)
                .addProduct(entityManager.find(Product.class, 1),50)
                .addProduct(entityManager.find(Product.class, 2),50)
                .addProduct(entityManager.find(Product.class, 3),50)
                .addProduct(entityManager.find(Product.class, 4),50)
                .addProduct(entityManager.find(Product.class, 5),50)
                .addProduct(entityManager.find(Product.class, 6),50)
                .build();

        return meal;
    }

    public static Measurement GenerateMeasurement(EntityManager entityManager){
        Measurement measurement = Measurement.builder().setArm(0).setCalf(0).setUser(entityManager.find(User.class, 1)).build();

        return measurement;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        userDAO.save(GenerateUser());
        userDAO.save(GenerateUser2());
        //create products db
        productDAO.save(Product.builder().setName("product 1").setCarbs(150).setFats(150).setProteins(150).setKcal(150).build());
        productDAO.save(Product.builder().setName("product 2").setCarbs(250).setFats(150).setProteins(150).setKcal(250).build());
        productDAO.save(Product.builder().setName("product 3").setCarbs(80).setFats(150).setProteins(150).setKcal(50).build());
        productDAO.save(Product.builder().setName("product 4").setCarbs(150).setFats(150).setProteins(150).setKcal(250).build());
        productDAO.save(Product.builder().setName("product 5").setCarbs(150).setFats(150).setProteins(150).setKcal(350).build());
        productDAO.save(Product.builder().setName("product 6").setCarbs(150).setFats(150).setProteins(150).setKcal(150).build());
        productDAO.save(Product.builder().setName("product 7").setCarbs(150).setFats(150).setProteins(150).setKcal(150).build());
        productDAO.save(Product.builder().setName("product 8").setCarbs(150).setFats(150).setProteins(150).setKcal(150).build());
        productDAO.save(Product.builder().setName("product 9").setCarbs(150).setFats(150).setProteins(150).setKcal(150).build());

        mealInfoDAO.insertMeal(GenerateMeal(entityManager));
        mealInfoDAO.insertMeal(GenerateMeal2(entityManager));

        userInfoDAO.insertMeasurement(GenerateMeasurement(entityManager),1);

        userInfoDAO.insertGoals(GenerateGoals(entityManager));

    }
}
