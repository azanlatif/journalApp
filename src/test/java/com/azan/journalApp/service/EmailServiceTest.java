package com.azan.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {


    @Autowired
    private EmailService emailService;


    @Disabled("Requires valid external SMTP credentials")
    @Test
    public void testSendEmail() {
        emailService.sendEmail("abdullatif6614960@gmail.com","test email", "hello my testing 123");
    }
}
