package com.demo.signup.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.signup.model.GenericResponse;
import com.demo.signup.model.Login;
import com.demo.signup.model.RegisterUser;
import com.demo.signup.model.RegisterUserValidate;
import com.demo.signup.repository.RegisterUserLoginRepository;
import com.demo.signup.utility.Constants;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {
	private static final Logger logger = LoggerFactory.getLogger(RegisterUserServiceImpl.class);

	@Autowired
	RegisterUserLoginRepository registerUserLoginRepository;

	@Override
	public GenericResponse ValidateNewUser(RegisterUserValidate user) {
		return null;
	}

	@Override
	public GenericResponse registerUser(RegisterUser user) {
		String returnMessage = null;
		String returnCode = null;
		logger.info("=== insertLoginDetails ===");
		try {
			if (isValidPassword(user.getPassword())) {
				if (isValidphonenum(String.valueOf(user.getMobilenumber()))) {
					if (isUserPresentAlreadyinLoginTable(user.getUsername())) {
						returnMessage = "This username already exists. Please try a different username to register";
						returnCode = Constants.FAILURE_CODE;
					} else {
						Login loginObj = new Login();
						loginObj.setUsername(user.getUsername());
						loginObj.setPassword(user.getPassword());
						registerUserLoginRepository.save(loginObj);
						logger.info("=== inserted new entry ===");
						returnMessage = "Data Inserted";
						returnCode = Constants.SUCCESS_CODE;
					}
				} else {
					returnMessage = "Invalid Mobile Number.";
					returnCode = Constants.FAILURE_CODE;
				}
			} else {
				returnMessage = "Password doesnt match the requirements. Pls try a different password.";
				returnCode = Constants.FAILURE_CODE;
			}
		} catch (Exception e) {
			logger.error("===insertLoginDetails Exception===", e);
		}
		return new GenericResponse(returnCode, returnMessage, "");
	}

	private boolean isValidPassword(String password) {
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		Pattern p = Pattern.compile(regex);
		if (password == null) {
			return false;
		}
		Matcher m = p.matcher(password);
		return m.matches();
	}

	private boolean isValidphonenum(String phoneNumber) {
		String regex = "^\\d{10}$";
		if (phoneNumber == null) {
			return false;
		}
		return phoneNumber.matches(regex);

	}

	private boolean isUserPresentAlreadyinLoginTable(String username) {
		logger.info("===isUserPresentAlreadyinLoginTable===");
		Integer count = registerUserLoginRepository.fetchLoginCountUsername(username);
		if (count > 0)
			return true;
		else
			return false;
	}

	@Override
	public GenericResponse getAllUsers() {
		int size = registerUserLoginRepository.findAll().size();
		if (size > 0) {
			return new GenericResponse(Constants.SUCCESS_CODE, "Users listed successfully",
					registerUserLoginRepository.findAll());
		} else {
			return new GenericResponse(Constants.SUCCESS_CODE, "No Users to display", "");
		}
	}

	@Override
	public GenericResponse loginToPortal(RegisterUserValidate user) {

		String returnMessage = null;
		String returnCode = null;
		if (isUserPresentAlreadyinLoginTable(user.getUsername())) {
			returnMessage = "Login Successful";
			returnCode = Constants.SUCCESS_CODE;
		} else {
			returnMessage = "Login Failed. Username/Password wrong";
			returnCode = Constants.FAILURE_CODE;
		}
		return new GenericResponse(returnCode, returnMessage, "");
	}

	@Override
	public GenericResponse deleteByUser(String username) {
		logger.info("===deleteByUser===");
		Login deleteUser = new Login();
		deleteUser.setUsername(username);
		if (isUserPresentAlreadyinLoginTable(username)) {
			registerUserLoginRepository.delete(deleteUser);
			return new GenericResponse(Constants.SUCCESS_CODE, "User Deleted Successfully", "");
		} else {
			return new GenericResponse(Constants.FAILURE_CODE, "User not found in db", "");
		}

	}

}
