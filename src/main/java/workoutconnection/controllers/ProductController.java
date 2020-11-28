package workoutconnection.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import workoutconnection.dao.ProductDAO;
import workoutconnection.entities.Product;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductDAO productDAO;
    @Autowired
    public ProductController(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @GetMapping()
    public ResponseEntity getProduct(@RequestParam("name") String name){
        List<Product> foundProducts = productDAO.findProductByName(name);
        if(foundProducts.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(foundProducts);
    }
}
