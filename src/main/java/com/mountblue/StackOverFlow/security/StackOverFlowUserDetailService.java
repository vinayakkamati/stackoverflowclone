package com.mountblue.StackOverFlow.security;

import com.mountblue.StackOverFlow.model.User;
import com.mountblue.StackOverFlow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class StackOverFlowUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if (user != null){
            return new StackOverFlowUserDetail(user);
        }
        throw new UsernameNotFoundException("Could not found user with email : "+email);
    }
}
