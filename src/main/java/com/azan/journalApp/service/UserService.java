package com.azan.journalApp.service;

import com.azan.journalApp.entity.User;
import com.azan.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        log.info("Created user '{}'", user.getUserName());
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
        log.info("Created admin user '{}'", user.getUserName());
    }


    public void  saveUser(User user) {
      userRepository.save(user);
      log.info("Saved user '{}'", user.getUserName());
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }




    public User findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

}
