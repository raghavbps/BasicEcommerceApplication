package com.firstproject.basicecommercewebsite.controller;

import com.firstproject.basicecommercewebsite.model.Product;
import com.firstproject.basicecommercewebsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/getAllProducts",method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productService.getAllProductsService();
    }

    @RequestMapping(value = "/getAllProductsByType",method = RequestMethod.GET)
    public List<Product> getAllProductsByType(@RequestParam("productType") String product_type) {
        return productService.getAllProductsByTypeService(product_type);
    }

    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public String addProduct(@RequestBody @Valid Product product) {
        return productService.addProductService(product);
    }


    @RequestMapping(value = "/getSingleProduct",method = RequestMethod.GET)
    public Product getSingleUser(@RequestParam("productId") int product_id) {
        return productService.getSingleProductService(product_id);
    }

//    @RequestMapping(value = "/updateProductQuantity",method = RequestMethod.PUT)
//    public String updateProductQuantity(@RequestParam @Min(1) int product_id, @RequestParam @Min(0) int new_quantity) {
//        return productService.updateQuantityService(product_id,new_quantity);
//    }
}
