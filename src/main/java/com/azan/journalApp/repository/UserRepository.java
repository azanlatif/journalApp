package com.azan.journalApp.repository;

import com.azan.journalApp.entity.JournalEntry;
import com.azan.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//MongoRepository<Entity_type,id data_type>
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);

    void deleteByUserName(String username);
}
