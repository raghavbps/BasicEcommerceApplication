package com.firstproject.basicecommercewebsite.service;

import com.firstproject.basicecommercewebsite.db.OrderPlaceddao;
import com.firstproject.basicecommercewebsite.exceptionhandlers.QuantityNotAvailableException;
import com.firstproject.basicecommercewebsite.model.OrderPlaced;
import com.firstproject.basicecommercewebsite.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderPlacedService {

    private static final Logger logger = LoggerFactory.getLogger(OrderPlacedService.class);


    @Autowired
    private OrderPlaceddao orderPlaceddao;

    public List<Map<String, Object>> getAllOrders() {
        return orderPlaceddao.getAllOrdersDAO();
    }

    public List<Map<String, Object>> getAllOrdersPaginationService(int limitv,int pageno) {
        return orderPlaceddao.getAllOrdersPaginationDAO(limitv,pageno);
    }

    public List<Map<String, Object>> getAllOrdersOfUserPaginationService(int user_id,int limitv,int pageno) {
        return orderPlaceddao.getAllOrdersOfUserPaginationDAO(user_id,limitv,pageno);
    }

    public List<OrderPlaced> getAllOrdersOfUser(int user_id) {
        return orderPlaceddao.getAllOrdersUserDAO(user_id);
    }

    public List<OrderPlaced> getAllOrdersOfUser(int user_id,int product_id) {
        return orderPlaceddao.getAllOrdersUserDAO(user_id,product_id);
    }

    @Transactional
    public String placeOrder(int product_id, int user_id, int quantity_required) {
        if (quantity_required <= 0) {
            return "At least One Item must be purchased!!";
        }
        if (!orderPlaceddao.checkIfUserExists(user_id)) {
            return "No such user exists";
        }
        Product product = orderPlaceddao.checkIfProductExists(product_id);
        if (product == null) {
            return "No such product exists!!";
        }
        int quantity_available = product.getQuantity_available();
        if (quantity_required > quantity_available) {
            throw new QuantityNotAvailableException("Only " + quantity_available + " items are left!!");
        }
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date().getTime());
        OrderPlaced ob = new OrderPlaced(user_id,product_id,quantity_required,date);
//        ob.setProduct_id(product_id);
//        ob.setUser_id(user_id);
//        ob.setQuantity_bought(quantity_required);
//        ob.setPlacing_time(date);
        System.out.println(getAllOrdersOfUserPaginationService(user_id,1,1));
        String addOrderResult = orderPlaceddao.addOrder(ob);
        System.out.println(getAllOrdersOfUserPaginationService(user_id,1,1));
        if (addOrderResult.startsWith("Unable")) {
            return "Unable to place Order!!";
        }
        return orderPlaceddao.updateQuantity(product_id, quantity_required, quantity_available);
    }
}
