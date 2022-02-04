package com.stackroute.userprofile.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userprofile.model.UserProfile;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
}


