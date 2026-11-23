package com.azan.journalApp.cache;

import com.azan.journalApp.entity.ConfigJournalAppEntity;
import com.azan.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        try {
            List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
            for(ConfigJournalAppEntity configJournalAppEntity: all){
                appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
            }
            log.info("AppCache initialized successfully with {} entries", appCache.size());
        } catch (Exception e) {
            log.error("Failed to initialize appCache from database: {}", e.getMessage());
        }
    }

}
