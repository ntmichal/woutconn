package workoutconnection.dto;

import org.junit.jupiter.api.Test;
import workoutconnection.entities.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductDtoTest {

    @Test
    void convertToDto() {
        Product product = Product.builder()
                .setId(0l)
                .setName("Product")
                .setBarcode("Barcode")
                .setCarbs(10)
                .setFats(10)
                .setProteins(10)
                .setKcal(100)
                .setVolume(100)
                .build();

        ProductDto productDto = product.convertToDto();

        assertEquals(productDto.getId(),product.getId());
        assertEquals(productDto.getName(),product.getName());
        assertEquals(productDto.getBarcode(),product.getBarcode());
        assertEquals(productDto.getCarbs(),product.getCarbs());
        assertEquals(productDto.getFats(),product.getFats());
        assertEquals(productDto.getProteins(),product.getProteins());
        assertEquals(productDto.getKcal(),product.getKcal());
        assertEquals(productDto.getVolume(),product.getVolume());


    }
}