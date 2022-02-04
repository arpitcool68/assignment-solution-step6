package com.stackroute.userprofile.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.userprofile.model.UserProfile;
import com.stackroute.userprofile.repository.UserProfileRepository;
import com.stackroute.userprofile.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.userprofile.util.exception.UserProfileNotFoundException;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

    public UserProfile registerUser(UserProfile user) throws UserProfileAlreadyExistsException {
    	UserProfile userProfile = userProfileRepository.insert(user);
    	if(null != userProfile) {
    		return userProfile;
    	}else {
    		throw new UserProfileAlreadyExistsException("Profile not found");
    	}
    	
    }

	 
    @Override
    public UserProfile updateUser(String userId, UserProfile user) throws UserProfileNotFoundException {
    	try {
    		Optional<UserProfile> optionalNews = userProfileRepository.findById(userId);
        	if(optionalNews.isPresent()) {
        		BeanUtils.copyProperties(user, optionalNews.get());
        		userProfileRepository.insert(optionalNews.get());
        		return optionalNews.get();
        	}else {
        		throw new UserProfileNotFoundException("Profile not found");
    		}
    	}catch (Exception e) {
    		throw new UserProfileNotFoundException("Profile not found");
		}
    	

		
    }

    @Override
    public boolean deleteUser(String userId) throws UserProfileNotFoundException {
    	boolean isDeleted = false;
    	Optional<UserProfile> fetchedUserProfileObj = userProfileRepository.findById(userId);
		if(null != fetchedUserProfileObj) {
			userProfileRepository.deleteById(userId);
			isDeleted = true;
		}else {
			isDeleted=false;
			throw new UserProfileNotFoundException("Profile not exist");
		}
		return isDeleted;
    }
    


    @Override
    public UserProfile getUserById(String userId) throws UserProfileNotFoundException {
    	Optional<UserProfile> optionalReminder = userProfileRepository.findById(userId);
		if (!optionalReminder.isPresent()) {
			throw new UserProfileNotFoundException("not exist");
		}

		return optionalReminder.get();
    }
}
