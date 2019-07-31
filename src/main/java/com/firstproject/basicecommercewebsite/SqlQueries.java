package com.firstproject.basicecommercewebsite;

public class SqlQueries {

    public static final String DELETE_USER_QUERY="delete from User where email=:email";

    public static final String ADD_USER_QUERY="select * from User where email=:email or phoneno=:phoneno or (name=:name and address=:address)";

    public static final String GET_SINGLE_USER_QUERY="select * from User where user_id=:user_id";

    public static final String GET_SINGLE_USER_EMAIL_QUERY="select * from User where email=:email";


    public static final String GET_ALL_USERS_QUERY="select * from User";

    public static final String UPDATE_USER_QUERY="update User set email=:email,name=:name,phoneno=:phoneno,address=:address where user_id=:user_id";

    public static final String GET_SINGLE_PRODUCT_QUERY="select * from Product where product_id=:product_id";

    public static final String GET_PRODUCT_TYPE_QUERY="select * from Product where product_type=:product_type";

    public static final String GET_ALL_PRODUCTS_QUERY="select * from Product";

    public static final String ADD_PRODUCT_QUERY="insert into Product(product_name,product_type," +
            "product_description,quantity_available,price) values (:product_name,:product_type," +
            ":product_description,:quantity_available,:price)";

    public static final String GET_ALL_ORDERS_QUERY="select * from OrderPlaced inner join Product on " +
            "OrderPlaced.product_id=Product.product_id inner join User on OrderPlaced.user_id=User.user_id";

    public static final String GET_ALL_ORDERS_PAGINATION_QUERY="select * from OrderPlaced inner join Product on OrderPlaced.product_id=Product.product_id " +
            "inner join User on OrderPlaced.user_id=User.user_id " +
            "order by placing_time desc limit :offsetv,:limitv";

    public static final String GET_ALL_ORDERS_USER_QUERY="select * from OrderPlaced where user_id=:user_id";

    public static final String GET_ALL_ORDERS_USER_PRODUCT_QUERY="select * from OrderPlaced where user_id=:user_id and product_id=:product_id";

    public static final String GET_ALL_ORDERS_USER_PAGINATION_QUERY="select * from OrderPlaced inner join Product on OrderPlaced.product_id=Product.product_id " +
            "inner join User on OrderPlaced.user_id=User.user_id where User.user_id=:user_id " +
            "order by placing_time desc limit :offsetv,:limitv";

    public static final String ADD_ORDER_QUERY="insert into OrderPlaced(product_id,user_id,quantity_bought,placing_time) values " +
            "(:product_id,:user_id,:quantity_bought,:placing_time)";

    public static final String CHECK_IF_PRODUCT_EXISTS_QUERY="select * from Product where product_id=:product_id";

    public static final String CHECK_IF_USER_EXISTS_QUERY="select * from User where user_id=:user_id";

    public static final String UPDATE_QUANTITY_QUERY="update Product set quantity_available=:quantity_available where product_id=:product_id";

}
