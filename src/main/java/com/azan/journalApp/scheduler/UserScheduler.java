package com.azan.journalApp.scheduler;

import com.azan.journalApp.cache.AppCache;
import com.azan.journalApp.entity.User;
import com.azan.journalApp.repository.UserRespositoryImpl;
import com.azan.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRespositoryImpl userRespository;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0/10 * ? * *")
    public void fetchUserAndSendMail() {
        List<User> users = userRespository.getUserForSA();
        for (User user : users) {
            emailService.sendEmail(user.getEmail(), "Greeting", "Good Morning! Hope your weekend is good , Glad to see you at work");
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void clearAppCache() {
        appCache.init();
    }
}
