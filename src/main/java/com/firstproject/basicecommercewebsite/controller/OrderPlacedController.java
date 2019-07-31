package com.firstproject.basicecommercewebsite.controller;

import com.firstproject.basicecommercewebsite.model.OrderPlaced;
import com.firstproject.basicecommercewebsite.service.OrderPlacedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.xml.SqlXmlFeatureNotImplementedException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderPlacedController {

    @Autowired
    private OrderPlacedService orderPlacedService;

    @GetMapping("/getAllOrders")
    public List<Map<String, Object>> getAllOrders() {
        return orderPlacedService.getAllOrders();
    }

    @GetMapping("/getAllOrdersPagination")
    public List<Map<String, Object>> getAllOrdersPagination(@RequestParam @Min(0) int limitv,
                                                            @RequestParam @Min(1) int pageno) {
        return orderPlacedService.getAllOrdersPaginationService(limitv,pageno);
    }


    @GetMapping("/getAllOrdersOfUserPagination")
    public List<Map<String, Object>> getAllOrdersOfUserPagination(@RequestParam("userId") @Min(1) int user_id,
                                                                 @RequestParam @Min(0) int limitv,
                                                                 @RequestParam @Min(1) int pageno) {
        return orderPlacedService.getAllOrdersOfUserPaginationService(user_id,limitv,pageno);
    }

    @GetMapping("/getAllOrdersOfUser")
    public List<OrderPlaced> getAllOrdersOfUser(@RequestParam("userId") @Min(1) int user_id) {
        return orderPlacedService.getAllOrdersOfUser(user_id);
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public String placeOrder(@RequestParam("userId") @Min(1) int user_id,
                             @RequestParam("productId") @Min(1) int product_id,
                             @RequestParam("quantityRequired") @Min(1) int quantity_required) {
        return orderPlacedService.placeOrder(product_id, user_id, quantity_required);
    }
}
