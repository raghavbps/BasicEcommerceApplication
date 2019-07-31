package com.firstproject.basicecommercewebsite.db;

import com.firstproject.basicecommercewebsite.SqlQueries;
import com.firstproject.basicecommercewebsite.exceptionhandlers.DatabaseException;
import com.firstproject.basicecommercewebsite.exceptionhandlers.UserNotFoundException;
import com.firstproject.basicecommercewebsite.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPhoneno(rs.getString("phoneno"));
        user.setAddress(rs.getString("address"));
        return user;
    }
}

//class UserMapperSubset implements RowMapper<UserSubset> {
//    @Override
//    public UserSubset mapRow(ResultSet rs, int i) throws SQLException {
//        UserSubset user = new UserSubset();
//        user.setUser_id(rs.getInt("user_id"));
//        user.setName(rs.getString("name"));
//        user.setEmail(rs.getString("email"));
//        return user;
//    }
//}

@Repository
public class Userdao {

//    @Autowired
//    JdbcTemplate jdbc;
    private static final Logger logger = LoggerFactory.getLogger(Userdao.class);

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public User toUser(ResultSet rs) throws SQLException{
        User user=new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPhoneno(rs.getString("phoneno"));
        user.setAddress(rs.getString("address"));
        return user;
    }

    private Map<String,Object> toMap(User user_ob) {
        Map<String,Object> map=new HashMap<>();
        map.put("name",user_ob.getName());
        map.put("email",user_ob.getEmail());
        map.put("phoneno",user_ob.getPhoneno());
        map.put("address",user_ob.getAddress());
        return map;
    }

    public List<User> getAllUsersDAO() {
//        throw new RuntimeException("dummy exception");
        String query= SqlQueries.GET_ALL_USERS_QUERY;
        return namedParameterJdbcTemplate.query(query,new UserMapper());
    }
    public String deleteUserDAO(String email) {
        String query=SqlQueries.DELETE_USER_QUERY;
        Map <String,Object> map=new HashMap<>();
        map.put("email",email);
        int rows_affected=namedParameterJdbcTemplate.update(query,map);
        System.out.println(rows_affected+" *** ");
        if(rows_affected==1) {
            logger.info("Deleted User Successfully!!");
            return "Deleted Successfully!!";
        }
        else {
            throw new DatabaseException("Unable to Delete!!");
        }
    }

    public String updateUserDAO(User user_ob) {
        String query=SqlQueries.UPDATE_USER_QUERY;
        Map <String,Object> map=toMap(user_ob);
        map.put("user_id",user_ob.getUser_id());
        int rows_affected=namedParameterJdbcTemplate.update(query,map);
        if(rows_affected==1) {
            logger.info("Update the User Successfully!!");
            return "Updated Successfully!!";
        }
        else {
            logger.error("Unable to Update!!");
            throw new DatabaseException("Unable to Update!!");
        }
    }

    public User getSingleUserDAO(String email) {
        String query=SqlQueries.GET_SINGLE_USER_EMAIL_QUERY;
        Map <String,Object> map=new HashMap<>();
        map.put("email",email);
        User ob = null;
        try {
            ob = namedParameterJdbcTemplate.queryForObject(query, map, new UserMapper());
        }catch(EmptyResultDataAccessException ex) {
            logger.error("User not found exception!!");
            System.out.println("User not found exception!!");
            throw new UserNotFoundException("User not found!!");
        }
        return ob;
    }

    public User getSingleUserDAO(int user_id) {
        String query=SqlQueries.GET_SINGLE_USER_QUERY;
        Map <String,Object> map=new HashMap<>();
        map.put("user_id",user_id);
        User ob = null;
        try {
            ob = namedParameterJdbcTemplate.queryForObject(query, map, new UserMapper());
        }catch(EmptyResultDataAccessException ex) {
            logger.error("User not found exception!!");
            System.out.println("User not found exception!!");
            throw new UserNotFoundException("User not found!!");
        }
        return ob;
    }

    public String addUserDAO(User user_ob) {

        String query=SqlQueries.ADD_USER_QUERY;
        Map<String,Object> map=toMap(user_ob);
        List <User> list= namedParameterJdbcTemplate.query(query,map,new UserMapper());
        System.out.println(list);
        if(list.size()>0) {
            logger.error("Already added this user");
            throw new DatabaseException("Already Added this User");
        }

        query="insert into User(name,email,phoneno,address) values (:name,:email,:phoneno,:address)";

        map=toMap(user_ob);
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(user_ob);
        int rows_affected = namedParameterJdbcTemplate.update(query,parameters,holder);
        System.out.println(user_ob.getUser_id()+" "+holder.getKey().longValue()+" ***** ");
        user_ob.setUser_id((int)holder.getKey().longValue());
        if(rows_affected==1) {
            logger.info("User Added!!");
            return "User Added!!";
        }
        else {
            logger.error("Unable to Add User");
            throw new DatabaseException("Unable to Add User");
        }
    }
}
