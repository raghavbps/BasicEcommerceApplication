package com.firstproject.basicecommercewebsite.db;

import com.firstproject.basicecommercewebsite.SqlQueries;
import com.firstproject.basicecommercewebsite.exceptionhandlers.DatabaseException;
import com.firstproject.basicecommercewebsite.exceptionhandlers.ProductNotFoundException;
import com.firstproject.basicecommercewebsite.exceptionhandlers.UserNotFoundException;
import com.firstproject.basicecommercewebsite.model.OrderPlaced;
import com.firstproject.basicecommercewebsite.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OrderPlacedMapper implements RowMapper<OrderPlaced> {
    @Override
    public OrderPlaced mapRow(ResultSet rs, int i) throws SQLException {
        OrderPlaced ob = new OrderPlaced();
        ob.setOrder_id(rs.getInt("order_id"));
        ob.setProduct_id(rs.getInt("product_id"));
        ob.setQuantity_bought(rs.getInt("quantity_bought"));
        ob.setUser_id(rs.getInt("user_id"));
        ob.setPlacing_time(rs.getString("placing_time"));
        return ob;
    }
}


@Repository
public class OrderPlaceddao {

    private static final Logger logger = LoggerFactory.getLogger(OrderPlaceddao.class);

    @Autowired
    Productdao productdao;

    @Autowired
    Userdao userdao;


//    @Autowired
//    JdbcTemplate jdbc;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private OrderPlaced toOrderPlaced(ResultSet rs) throws SQLException {
        OrderPlaced ob = new OrderPlaced();
        ob.setOrder_id(rs.getInt("order_id"));
        ob.setProduct_id(rs.getInt("product_id"));
        ob.setQuantity_bought(rs.getInt("quantity_bought"));
        ob.setUser_id(rs.getInt("user_id"));
        ob.setPlacing_time(rs.getString("placing_time"));
        return ob;
    }

    private Map<String, Object> toMap(OrderPlaced orderPlaced) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", orderPlaced.getUser_id());
        map.put("product_id", orderPlaced.getProduct_id());
        map.put("quantity_bought", orderPlaced.getQuantity_bought());
        map.put("placing_time", orderPlaced.getPlacing_time());
        return map;
    }

    public List<Map<String, Object>> getAllOrdersDAO() {
        String query = SqlQueries.GET_ALL_ORDERS_QUERY;
        List<Map<String, Object>> list1 = namedParameterJdbcTemplate.query(query,
                new ResultSetExtractor<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> extractData(ResultSet rs) throws SQLException,
                            DataAccessException {
                        List<Map<String, Object>> list = new ArrayList<>();
                        while (rs.next()) {
                            Map<String, Object> mp = new HashMap<>();
                            mp.put("product_name", rs.getString("product_name"));
                            mp.put("product_description", rs.getString("product_description"));
                            mp.put("name", rs.getString("name"));
                            mp.put("quantity_bought", rs.getString("quantity_bought"));
                            mp.put("placing_time", rs.getString("placing_time"));
                            list.add(mp);
                        }
                        return list;
                    }
                });
        return list1;
    }

    public List<Map<String, Object>> getAllOrdersPaginationDAO(int limitv,int pageno) {
        int offsetv=(pageno-1)*limitv;
        String query=SqlQueries.GET_ALL_ORDERS_PAGINATION_QUERY;
        Map <String,Object> map=new HashMap<>();
        map.put("limitv",limitv);
        map.put("offsetv",offsetv);
        List<Map<String, Object>> list1 = new ArrayList<>();
        if(offsetv<0||limitv<0)return list1;
        list1 = namedParameterJdbcTemplate.query(query,map,
                new ResultSetExtractor<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> extractData(ResultSet rs) throws SQLException,
                            DataAccessException {
                        List<Map<String, Object>> list = new ArrayList<>();
                        while (rs.next()) {
                            Map<String, Object> mp = new HashMap<>();
                            mp.put("product_name", rs.getString("product_name"));
                            mp.put("product_description", rs.getString("product_description"));
                            mp.put("name", rs.getString("name"));
                            mp.put("quantity_bought", rs.getString("quantity_bought"));
                            mp.put("placing_time", rs.getString("placing_time"));
                            list.add(mp);
                        }
                        return list;
                    }
                });
        return list1;
    }

    public List<OrderPlaced> getAllOrdersUserDAO(int user_id) {
          String query = SqlQueries.GET_ALL_ORDERS_USER_QUERY;
          Map <String,Object> map=new HashMap<>();
          map.put("user_id",user_id);
          RowMapper<OrderPlaced> rowMapper = new BeanPropertyRowMapper<>(OrderPlaced.class);
          return namedParameterJdbcTemplate.query(query,map,rowMapper);
    }

    public List<OrderPlaced> getAllOrdersUserDAO(int user_id,int product_id) {
        String query = SqlQueries.GET_ALL_ORDERS_USER_PRODUCT_QUERY;
        Map <String,Object> map=new HashMap<>();
        map.put("user_id",user_id);
        map.put("product_id",product_id);
        RowMapper<OrderPlaced> rowMapper = new BeanPropertyRowMapper<>(OrderPlaced.class);
        return namedParameterJdbcTemplate.query(query,map,rowMapper);
    }

    public List<Map<String, Object>> getAllOrdersOfUserPaginationDAO(int user_id,int limitv,int pageno) {
        int offsetv=(pageno-1)*limitv;
        String query=SqlQueries.GET_ALL_ORDERS_USER_PAGINATION_QUERY;
        Map <String,Object> map=new HashMap<>();
        map.put("user_id",user_id);
        map.put("limitv",limitv);
        map.put("offsetv",offsetv);
        List<Map<String, Object>> list1 = new ArrayList<>();
        if(offsetv<0||limitv<0)return list1;
        list1 = namedParameterJdbcTemplate.query(query,map,
                new ResultSetExtractor<List<Map<String, Object>>>() {
                    @Override
                    public List<Map<String, Object>> extractData(ResultSet rs) throws SQLException,
                            DataAccessException {
                        List<Map<String, Object>> list = new ArrayList<>();
                        while (rs.next()) {
                            Map<String, Object> mp = new HashMap<>();
                            mp.put("product_name", rs.getString("product_name"));
                            mp.put("product_description", rs.getString("product_description"));
                            mp.put("name", rs.getString("name"));
                            mp.put("quantity_bought", rs.getString("quantity_bought"));
                            mp.put("placing_time", rs.getString("placing_time"));
                            list.add(mp);
                        }
                        return list;
                    }
                });
        return list1;
    }

    public String addOrder(OrderPlaced orderPlaced) {

        String query=SqlQueries.ADD_ORDER_QUERY;
        Map <String,Object> map=toMap(orderPlaced);
        int rows_affected = namedParameterJdbcTemplate.update(query,map);
        if(rows_affected!=0) {
            return "Order Placed!!";
        }
        else {
            logger.error("Unable to Place Order!!");
            throw new DatabaseException("Unable to Place Order!!");
        }
    }

    public Boolean checkIfUserExists(int user_id) {
        String query = SqlQueries.CHECK_IF_USER_EXISTS_QUERY;
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user_id);
        try {
            namedParameterJdbcTemplate.queryForObject(query, map, new UserMapper());
            return true;
        }catch(EmptyResultDataAccessException ex) {
            logger.error("User not found exception!!");
            System.out.println("User not found exception!!");
            throw new UserNotFoundException("User not found!!");
        }
    }

    public Product checkIfProductExists(int product_id) {
        String query = SqlQueries.CHECK_IF_PRODUCT_EXISTS_QUERY;
        Map<String, Object> map = new HashMap<>();
        map.put("product_id", product_id);
        Product ob=null;
        try {
            ob = namedParameterJdbcTemplate.queryForObject(query, map, new ProductMapper());
        }catch(EmptyResultDataAccessException ex) {
            System.out.println("Product not found exception!!");
            logger.error("Product not found!!");
            throw new ProductNotFoundException("Product not found!!");
        }
        return ob;
    }

    public String updateQuantity(int product_id, int quantity_required, int quantity_available) {
        String query = SqlQueries.UPDATE_QUANTITY_QUERY;
        Map <String,Object> map=new HashMap<>();
        map.put("quantity_available",quantity_available-quantity_required);
        map.put("product_id",product_id);
        int rows_affected = namedParameterJdbcTemplate.update(query,map);
        if (rows_affected == 1) {
            logger.info("Order Placed!!");
            return "Order Placed!!";
        } else {
            logger.error("Unable to Place Order!!");
            throw new DatabaseException("Unable to Place Order!!");
        }
    }
}
