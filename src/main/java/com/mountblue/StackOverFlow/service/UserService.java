package com.mountblue.StackOverFlow.service;

import com.mountblue.StackOverFlow.exception.UserNotFoundException;
import com.mountblue.StackOverFlow.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    List<User>listAll();

    void saveUser(User user);

    User getUserById(Integer userId) throws UserNotFoundException;

    void deleteUserById(Integer userId);
}
