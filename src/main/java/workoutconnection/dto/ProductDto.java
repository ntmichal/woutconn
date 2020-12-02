package workoutconnection.dto;


import org.modelmapper.ModelMapper;
import workoutconnection.entities.Product;

public class ProductDto {

    private Long id;
    private String name;
    private String barcode;
    private float proteins;
    private float carbs;
    private float fats;
    private float kcal;
    private float volume;

    public Product convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(this,Product.class);

        return product;
    }

    public Long getId() {
        return id;
    }

    public ProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getBarcode() {
        return barcode;
    }

    public ProductDto setBarcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public float getProteins() {
        return proteins;
    }

    public ProductDto setProteins(float proteins) {
        this.proteins = proteins;
        return this;
    }

    public float getCarbs() {
        return carbs;
    }

    public ProductDto setCarbs(float carbs) {
        this.carbs = carbs;
        return this;
    }

    public float getFats() {
        return fats;
    }

    public ProductDto setFats(float fats) {
        this.fats = fats;
        return this;
    }

    public float getKcal() {
        return kcal;
    }

    public ProductDto setKcal(float kcal) {
        this.kcal = kcal;
        return this;
    }

    public float getVolume() {
        return volume;
    }

    public ProductDto setVolume(float volume) {
        this.volume = volume;
        return this;
    }
}
