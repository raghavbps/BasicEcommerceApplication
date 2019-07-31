package com.firstproject.basicecommercewebsite.service;

import com.firstproject.basicecommercewebsite.db.Productdao;
import com.firstproject.basicecommercewebsite.db.Userdao;
import com.firstproject.basicecommercewebsite.model.Product;
import com.firstproject.basicecommercewebsite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private Productdao productdao;

    public List<Product> getAllProductsService() {
        return productdao.getAllProductsDAO();
    }

    public List<Product> getAllProductsByTypeService(String product_type) {
        return productdao.getProductsByTypeDAO(product_type);
    }

    public String addProductService(Product product) {
        return productdao.addProductDAO(product);
    }

    public Product getSingleProductService(int product_id) {
        return productdao.getSingleProductDAO(product_id);
    }

//    public String updateQuantityService(int product_id,int quantity_available) {
//        return productdao.updateQuantityDAO(product_id,quantity_available);
//    }
}

