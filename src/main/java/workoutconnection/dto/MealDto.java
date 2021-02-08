package workoutconnection.dto;


import org.modelmapper.ModelMapper;
import workoutconnection.entities.Meal;

import java.time.LocalDate;

public class MealDto {

    private int id;
    private String name;
    private LocalDate mealDate;


    public Meal convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Meal.class);
    }


    public int getId() {
        return id;
    }

    public MealDto setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MealDto setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public MealDto setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
        return this;
    }
}
