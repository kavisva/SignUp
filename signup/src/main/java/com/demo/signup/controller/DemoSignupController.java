package com.demo.signup.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.signup.model.GenericResponse;
import com.demo.signup.model.RegisterUser;
import com.demo.signup.model.RegisterUserValidate;
import com.demo.signup.service.RegisterUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "REST APIs related to registering user")
public class DemoSignupController {

	private static final Logger logger = LoggerFactory.getLogger(DemoSignupController.class);

	@Autowired
	RegisterUserService registerUserService;

	@ApiOperation(value = "Register a user to login list")
	@PostMapping(value = "/users/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse registerUser(@Valid @RequestBody  RegisterUser user) {
		logger.info("===registerUser===");
		return registerUserService.registerUser(user);
	}
	@ApiOperation(value = "Get all login list")
	@PostMapping(value = "/users/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse getAllUsers() {
		logger.info("===getAllUsers===");
		return registerUserService.getAllUsers();

	}
	
	@ApiOperation(value = "Login to portal with username and password")
	@PostMapping(value = "/users/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse loginToPortal(@RequestBody RegisterUserValidate user) {
		logger.info("===loginToPortal===");
		return registerUserService.loginToPortal(user);
	}
	
	
    @DeleteMapping("/users/delete/{username}")
    public GenericResponse deleteUsers(@PathVariable String username) {
        return registerUserService.deleteByUser(username);
    }
}
