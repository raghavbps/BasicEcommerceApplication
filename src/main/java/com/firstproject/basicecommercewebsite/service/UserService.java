package com.firstproject.basicecommercewebsite.service;


import com.firstproject.basicecommercewebsite.db.Userdao;
import com.firstproject.basicecommercewebsite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private Userdao userdao;

    public List<User> getAllUsersService() {
        return userdao.getAllUsersDAO();
    }

//    public List<UserSubset> getAllUsersSubsetService() {
//        return userdao.getAllUsersSubsetDAO();
//    }

    public String addUserService(User user) {
        return userdao.addUserDAO(user);
    }

    public String deleteUserService(String email) {
        return userdao.deleteUserDAO(email);
    }

    public User getSingleUserService(int user_id) {
        return userdao.getSingleUserDAO(user_id);
    }

    public User getSingleUserService(String email) {
        return userdao.getSingleUserDAO(email);
    }

    public String updateUserService(User user_ob) {
       return userdao.updateUserDAO(user_ob);
    }
}
