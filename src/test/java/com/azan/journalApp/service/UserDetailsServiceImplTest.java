package com.azan.journalApp.service;

import com.azan.journalApp.entity.User;
import com.azan.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;


public class UserDetailsServiceImplTest {


   @InjectMocks
   private UserDetailsServiceImpl userDetailsService;

   @Mock
   private UserRepository userRepository;

   @BeforeEach
   void setUp() {
       MockitoAnnotations.openMocks(this);
   }

   @Test
   void loadUserDetailsTest() {
       User applicationUser = new User("azan", "azan");
       applicationUser.setRoles(java.util.List.of("USER"));
       when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(applicationUser);
       UserDetails user = userDetailsService.loadUserByUsername("azan");
       Assertions.assertNotNull(user);
   }

}
