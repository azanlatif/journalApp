package com.azan.journalApp.service;

import com.azan.journalApp.entity.JournalEntry;
import com.azan.journalApp.entity.User;
import com.azan.journalApp.repository.JournalEntryRespository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRespository journalEntryRespository;
    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUsername(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRespository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
            log.info("Saved journal entry '{}' for user '{}'", saved.getId(), userName);
        } catch (Exception e) {
            log.error("Failed to save journal entry for user '{}'", userName, e);
            throw new RuntimeException("An Error occurred while saving : " + e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRespository.save(journalEntry);
        log.info("Saved journal entry '{}'", journalEntry.getId());
    }

    public List<JournalEntry> getAll() {
        return journalEntryRespository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRespository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUsername(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRespository.deleteById(id);
                log.info("Deleted journal entry '{}' for user '{}'", id, userName);
            }
            return removed;
        }catch (Exception e) {
            log.error("Failed to delete journal entry '{}' for user '{}'", id, userName, e);
            throw new RuntimeException("An Error occurred while deleting : " + e);
        }
    }


}
