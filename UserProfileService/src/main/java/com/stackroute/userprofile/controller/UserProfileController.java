package com.stackroute.userprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userprofile.model.UserProfile;
import com.stackroute.userprofile.service.UserProfileService;
import com.stackroute.userprofile.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.userprofile.util.exception.UserProfileNotFoundException;

@RestController
@RequestMapping
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@PostMapping("/api/v1/user")
	public ResponseEntity<UserProfile> register(@RequestBody UserProfile userProfileObj) {
		try {
			return new ResponseEntity<>(userProfileService.registerUser(userProfileObj), HttpStatus.CREATED);
		} catch (UserProfileAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/api/v1/userprofile/{userId}")
	public ResponseEntity<UserProfile> update(@RequestBody UserProfile userProfileObj, @PathVariable String userId) {
		try {
			UserProfile userProfile = userProfileService.updateUser(userId, userProfileObj);
			if(null != userProfile) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (UserProfileNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/api/v1/userprofile/{userId}")
	public ResponseEntity<UserProfile> delete(@PathVariable String userId) {
		try {
			userProfileService.deleteUser(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserProfileNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/v1/userprofile/{userId}")
	public ResponseEntity<UserProfile> getUserProfileById(@PathVariable String userId) {
		try {
			UserProfile userProfileObj;
			if (null != userId) {
				userProfileObj = userProfileService.getUserById(userId);
				if (null != userProfileObj) {
					return new ResponseEntity<>(userProfileObj, HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (UserProfileNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
