package com.firstproject.basicecommercewebsite;

import com.firstproject.basicecommercewebsite.model.OrderPlaced;
import com.firstproject.basicecommercewebsite.model.Product;
import com.firstproject.basicecommercewebsite.service.OrderPlacedService;
import com.firstproject.basicecommercewebsite.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private OrderPlacedService orderPlacedService;

  @Autowired
  private ProductService productService;

  //	@Test(expected = QuantityNotAvailableException.class)

  public Product addProduct(int initial_quantity) {
    Product product = new Product("Iphone XR", "smartphone",
      initial_quantity, 53000, "It has an LED Display!!");
    System.out.println(productService.addProductService(product));
    return product;
  }

  public void placeOrder(int user_id,int quantity_bought,Product product) {
    String pattern = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String date = simpleDateFormat.format(new Date().getTime());
    OrderPlaced ob = new OrderPlaced(user_id, product.getProduct_id(), quantity_bought);
  }

  @Test
  public void validateOrderPlaced() throws Exception {

    //Setup
    int initial_quantity = 20;
    Product product = addProduct(initial_quantity);

    //Perform

    int quantity_bought = 5;
    int user_id = 1;
    placeOrder(user_id,quantity_bought,product);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/placeOrder")
      .accept(MediaType.APPLICATION_JSON)
      .param("userId", String.valueOf(user_id))
      .param("productId", String.valueOf(product.getProduct_id()))
      .param("quantityRequired", String.valueOf(quantity_bought));

    MvcResult result = mockMvc.perform(requestBuilder)
                      .andExpect(status().isOk())
                      .andReturn();
    MockHttpServletResponse response = result.getResponse();


//  orderPlacedService.placeOrder(product.getProduct_id(),user_id,quantity_bought);
//    System.out.println(response.getContentAsString() + " ******  ");
//    System.out.println(orderPlacedService.placeOrder(product.getProduct_id(), user_id, quantity_bought));


    //Validate

    int quantity_remaining = initial_quantity - quantity_bought;
    Product new_ob = productService.getSingleProductService(product.getProduct_id());
    Assert.assertEquals(new_ob.getQuantity_available(), quantity_remaining);
    List<OrderPlaced> ls = orderPlacedService.getAllOrdersOfUser(user_id, product.getProduct_id());
    System.out.println(ls);
    Assert.assertEquals(ls.size(), 1);
    Assert.assertEquals(ls.get(0).getQuantity_bought(), quantity_bought);
    Assert.assertEquals(ls.get(0).getUser_id(), user_id);
    Assert.assertEquals(ls.get(0).getProduct_id(), product.getProduct_id());
  }
}