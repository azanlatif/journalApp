package com.azan.journalApp.repository;

import com.azan.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//MongoRepository<Entity_type,id data_type>
public interface JournalEntryRespository extends MongoRepository<JournalEntry, ObjectId> {
}
