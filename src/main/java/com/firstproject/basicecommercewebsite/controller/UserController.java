package com.firstproject.basicecommercewebsite.controller;

import com.firstproject.basicecommercewebsite.annotation.learn;
import com.firstproject.basicecommercewebsite.model.Learn;
import com.firstproject.basicecommercewebsite.model.User;
import com.firstproject.basicecommercewebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.List;

//import com.firstproject.basicecommercewebsite.model.UserSubset;
//import org.springframework.stereotype.Controller;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/getLearn", method = RequestMethod.POST)
    public Learn getLearn(@learn Learn ob) {
        return ob;
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsersService();
    }

//    @RequestMapping(value = "/getAllUsersSubset",method = RequestMethod.GET)
//    public List<UserSubset> getAllUsersSubset() {
//        return userService.getAllUsersSubsetService();
//    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestBody @Valid User user) {
        return userService.addUserService(user);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam @Email String email) {
        return userService.deleteUserService(email);
    }

    @RequestMapping(value = "/getSingleUser", method = RequestMethod.GET)
    public User getSingleUser(@RequestParam("userId") @Min(1) int user_id) {
        return userService.getSingleUserService(user_id);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public String updateUser(@RequestBody @Valid User user_ob) {
        return userService.updateUserService(user_ob);
    }
}
