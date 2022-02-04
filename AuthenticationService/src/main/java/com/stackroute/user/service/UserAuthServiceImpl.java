package com.stackroute.user.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.stackroute.user.model.User;
import com.stackroute.user.repository.UserAuthRepository;
import com.stackroute.user.util.exception.UserAlreadyExistsException;
import com.stackroute.user.util.exception.UserNotFoundException;

@Service
@Transactional
public class UserAuthServiceImpl implements UserAuthService {

	@Autowired
	private UserAuthRepository userAuthRepository;

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		if (!StringUtils.isEmpty(password) && !StringUtils.isEmpty(userId)) {
			User fetchedUser = userAuthRepository.findByUserIdAndPassword(userId, password);
			if (null != fetchedUser) {
				return fetchedUser;
			} else {
				throw new UserNotFoundException("User already exist in the system");
			}
		}
		return null;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {

		boolean isUserSaved = true;
		Optional<User> optionalUser = userAuthRepository.findById(user.getUserId());
		if (optionalUser.isPresent()) {
			isUserSaved = false;
			throw new UserAlreadyExistsException("User Already Exist");
		}
		userAuthRepository.save(user);
		return isUserSaved;

	}
}
