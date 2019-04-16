package workoutconnection.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="meals_list")
public class MealsList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @JsonBackReference
    private Meal meal;


    @ManyToOne(cascade = {CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    private Product product;


    @Column(name="product_weight")
    private int productWeight;

    private  MealsList(){};
    public MealsList(Meal meal, Product product, int productWeight){
        this.meal = meal;
        this.product = product;
        this.productWeight = productWeight;
    }


    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(int productWeight) {
        this.productWeight = productWeight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealsList mealsList = (MealsList) o;
        return Objects.equals(id, mealsList.id) &&
                Objects.equals(meal, mealsList.meal) &&
                Objects.equals(product, mealsList.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meal, product);
    }
}
