package com.demo.signup.service;

import com.demo.signup.model.GenericResponse;
import com.demo.signup.model.RegisterUser;
import com.demo.signup.model.RegisterUserValidate;

public interface RegisterUserService {

	GenericResponse ValidateNewUser(RegisterUserValidate user);

	GenericResponse registerUser(RegisterUser user);

	GenericResponse getAllUsers();

	GenericResponse loginToPortal(RegisterUserValidate user);

	GenericResponse deleteByUser(String username);

}
