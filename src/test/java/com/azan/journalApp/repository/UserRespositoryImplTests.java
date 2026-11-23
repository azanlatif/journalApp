package com.azan.journalApp.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRespositoryImplTests {

    @Autowired
    private UserRespositoryImpl userRespositoryImpl;

    @Test
    public void testSaveNewUser(){
        userRespositoryImpl.getUserForSA();
    }
}
