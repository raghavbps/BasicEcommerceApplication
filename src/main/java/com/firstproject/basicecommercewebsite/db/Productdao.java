package com.firstproject.basicecommercewebsite.db;

import com.firstproject.basicecommercewebsite.SqlQueries;
import com.firstproject.basicecommercewebsite.exceptionhandlers.DatabaseException;
import com.firstproject.basicecommercewebsite.exceptionhandlers.ProductNotFoundException;
import com.firstproject.basicecommercewebsite.model.Product;
import com.firstproject.basicecommercewebsite.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs,int i) throws SQLException {
        Product product=new Product();
        product.setProduct_id(rs.getInt("product_id"));
        product.setProduct_description(rs.getString("product_description"));
        product.setProduct_name(rs.getString("product_name"));
        product.setProduct_type(rs.getString("product_type"));
        product.setQuantity_available(rs.getInt("quantity_available"));
        product.setPrice(rs.getInt("price"));
        return product;
    }
}


@Repository
public class Productdao {

//    @Autowired
//    JdbcTemplate jdbc;

    private static final Logger logger = LoggerFactory.getLogger(Productdao.class);

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Product toProduct(ResultSet rs) throws SQLException {
        Product product=new Product();
        product.setProduct_id(rs.getInt("product_id"));
        product.setProduct_description(rs.getString("product_description"));
        product.setProduct_name(rs.getString("product_name"));
        product.setProduct_type(rs.getString("product_type"));
        product.setQuantity_available(rs.getInt("quantity_available"));
        product.setPrice(rs.getInt("price"));
        return product;
    }

    private Map<String,Object> toMap(Product product_ob) {
        Map<String,Object> map=new HashMap<>();
        map.put("product_name",product_ob.getProduct_name());
        map.put("product_type",product_ob.getProduct_type());
        map.put("product_description",product_ob.getProduct_description());
        map.put("quantity_available",product_ob.getQuantity_available());
        map.put("price",product_ob.getPrice());
        return map;
    }

    public List<Product> getAllProductsDAO() {
        String query= SqlQueries.GET_ALL_PRODUCTS_QUERY;
        return namedParameterJdbcTemplate.query(query,new ProductMapper());
    }

//    public String updateQuantityDAO(int product_id,int new_quantity) {
//        String query="update Product set quantity_available=:quantity_available where product_id=:product_id";
//        Map <String,Object> map=new HashMap<>();
//        map.put("quantity_available",new_quantity);
//        map.put("product_id",product_id);
//        int rows_affected=namedParameterJdbcTemplate.update(query,map);
//        if(rows_affected==1) {
//            logger.info("Updated Quantity Successfully!!");
//            return "Updated Successfully!!";
//        }
//        else {
//             logger.error("Unable to Update Quantity!!");
//             throw new DatabaseException("Unable to Update Quantity!!");
//        }
//    }

    public Product getSingleProductDAO(int product_id) {
        String query=SqlQueries.GET_SINGLE_PRODUCT_QUERY;
        Map <String,Object> map=new HashMap<>();
        map.put("product_id",product_id);
        Product ob = null;
        try {
            ob = namedParameterJdbcTemplate.queryForObject(query, map, new ProductMapper());
        }catch(EmptyResultDataAccessException ex) {
            System.out.println("Product not found exception!!");
            logger.error("Product not found!!");
            throw new ProductNotFoundException("Product with id " +product_id+" not found!!");
        }
        return ob;
    }

    public List<Product> getProductsByTypeDAO(String product_type) {
        String query=SqlQueries.GET_PRODUCT_TYPE_QUERY;
        Map<String,Object> map=new HashMap<>();
        map.put("product_type",product_type);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("product_type", product_type);
        List <Product> list =
                namedParameterJdbcTemplate.query(query,sqlParameterSource,new ProductMapper());
        return list;
    }

    public String addProductDAO(Product product_ob) {

        String query=SqlQueries.ADD_PRODUCT_QUERY;
        System.out.println(product_ob.getQuantity_available()+" "+product_ob.getProduct_name());
        Map <String,Object> map=toMap(product_ob);
        System.out.println(map);
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(product_ob);
        int rows_affected = namedParameterJdbcTemplate.update(query,parameters,holder);
        System.out.println(product_ob.getProduct_id()+" "+holder.getKey().longValue()+" ***** ");
        product_ob.setProduct_id((int)holder.getKey().longValue());
        if(rows_affected==1) {
            logger.info("Added the Product Successfully!!");
            return "Added Successfully!!";
        }
        else {
            logger.error("Unable to Add the Product!!");
            throw new DatabaseException("Unable to Add the product!!");
        }
    }
}
