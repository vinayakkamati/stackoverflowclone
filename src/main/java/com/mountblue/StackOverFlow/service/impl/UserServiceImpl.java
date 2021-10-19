package com.mountblue.StackOverFlow.service.impl;

import com.mountblue.StackOverFlow.exception.UserNotFoundException;
import com.mountblue.StackOverFlow.model.Role;
import com.mountblue.StackOverFlow.model.User;
import com.mountblue.StackOverFlow.repository.UserRepository;
import com.mountblue.StackOverFlow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public User getCurrentUser() {
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if(principal instanceof UserDetails){
            String username = ((UserDetails) principal).getUsername();
            System.out.println("Current User Details: " + username);
            user = userRepository.findByEmail(username);
        }
        return user;
    }
}
