package com.stackroute.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.user.model.User;
import com.stackroute.user.service.UserAuthService;
import com.stackroute.user.util.exception.UserAlreadyExistsException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;	

@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthController {

	@Autowired
	private UserAuthService userAuthService;

	private static final int EXPIRY_DAYS = 300000;

	private Map<String, String> returnMap = new HashMap<>();

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		try {
			userAuthService.saveUser(user);
			return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws ServletException {

		String jwtToken = "";
		if (null != user && !StringUtils.isEmpty(user.getUserId()) && !StringUtils.isEmpty(user.getPassword())) {
			try {
				User fetchedUser = userAuthService.findByUserIdAndPassword(user.getUserId(), user.getPassword());
				String userId = fetchedUser.getUserId();
				String password = fetchedUser.getPassword();
				if (!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(password)) {
					jwtToken = getToken(userId, password);
					returnMap.clear();
					returnMap.put("accessToken", jwtToken);
					returnMap.put("message", "user successfully logged in");
				} else {
					returnResp("Validation of user name and password fails please try again !!");
				}
			} catch (Exception e) {
				String exceptionMessage = e.getMessage();
				returnResp(exceptionMessage);
			}
		} else {
			returnResp("Validation of user name and password fails please try again !!");
		}
		return new ResponseEntity<>(returnMap, HttpStatus.OK);
	}

	/**
	 * Return the Response if UnAUthorized
	 * 
	 * @param exceptionMessage
	 * @return
	 */
	private ResponseEntity<?> returnResp(String exceptionMessage) {
		returnMap.clear();
		returnMap.put("accessToken", null);
		returnMap.put("message", exceptionMessage);
		return new ResponseEntity<>(returnMap, HttpStatus.UNAUTHORIZED);

	}

	private String getToken(String userName, String password) throws ServletException {
		if (userName == null || password == null) {
			throw new ServletException("Please fill in the user Name and Password");
		}

		return Jwts.builder().setSubject(userName).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRY_DAYS))
				.signWith(SignatureAlgorithm.HS256, "secretKey".getBytes()).compact();
	}


}
