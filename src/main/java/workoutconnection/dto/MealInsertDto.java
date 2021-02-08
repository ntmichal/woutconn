package workoutconnection.dto;

import java.time.LocalDate;
import java.util.List;

public class MealInsertDto {

    private String name;
    private LocalDate mealDate;
    private List<ProductPath> productPaths;

    public static class ProductPath{
        private Long id;
        private int volume;

        public Long getId() {
            return id;
        }

        public ProductPath() {
        }

        public ProductPath(Long id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        public ProductPath setId(Long id) {
            this.id = id;
            return this;
        }

        public int getVolume() {
            return volume;
        }

        public ProductPath setVolume(int volume) {
            this.volume = volume;
            return this;
        }
    }

    public String getName() {
        return name;
    }

    public MealInsertDto setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public MealInsertDto setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
        return this;
    }

    public List<ProductPath> getProductPaths() {
        return productPaths;
    }

    public MealInsertDto setProductPaths(List<ProductPath> productPaths) {
        this.productPaths = productPaths;
        return this;
    }
}
