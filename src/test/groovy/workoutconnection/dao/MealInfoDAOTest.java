package workoutconnection.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import workoutconnection.entities.Meal;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class MealInfoDAOTest {


    @Autowired
    private IMealInfoDAO mealInfoDAO;

    final LocalDate todayDate = LocalDate.now();

    @Test
    void getMealByDate() {

        List<Meal> mealList = (List<Meal>) mealInfoDAO
                .getMealByDate(1, todayDate);

        assertEquals(2,mealList.size());
    }
}