package com.mountblue.StackOverFlow.service.impl;

import com.mountblue.StackOverFlow.exception.UserNotFoundException;
import com.mountblue.StackOverFlow.model.User;
import com.mountblue.StackOverFlow.repository.UserRepository;
import com.mountblue.StackOverFlow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    private void encodePassword(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        encodePassword(user);
         this.userRepository.save(user);
    }

    @Override
    public User getUserById(Integer userId) throws UserNotFoundException {
        try {
            return userRepository.findById(userId).get();
        }catch (NoSuchElementException ex){
            throw new UserNotFoundException("Could not found user with ID"+userId);
        }

    }

    @Override
    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }


}
