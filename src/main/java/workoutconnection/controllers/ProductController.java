package workoutconnection.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import workoutconnection.dao.ProductDAO;
import workoutconnection.entities.Product;
import workoutconnection.service.IProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    private IProductService productService;
    @Autowired
    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity getProduct(@RequestParam("name") String name){
        List<Product> foundProducts = productService.findProductByName(name);
        if(foundProducts.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(foundProducts);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Product> productList(){

        return productService.getAllProducts();
    }



    @RequestMapping(value = "/api/product/", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        Product savedProduct = productService.insertProduct(product);

        //response path
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).header("id", Integer.toString(savedProduct.getId())).build();
    }


    //TODO delete product only from admin panel, function for admins/moderators
    @RequestMapping(value = "/api/product/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
    }


    @RequestMapping(value = "/api/product/{id}", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody Product product, @PathVariable int id){
        product.setId(id);
        productService.update(product);
    }
}
