package com.greencity.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greencity.app.dto.CollectionCenterRegisterRequest;
import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.LoginRequest;
import com.greencity.app.dto.LoginResponse;
import com.greencity.app.dto.UserRegisterRequest;
import com.greencity.app.service.AuthService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("userRegister")
	public ResponseEntity<CommonResponse<String>> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {

		return new ResponseEntity<>(authService.userRegister(userRegisterRequest), HttpStatus.OK);
	}

	@PostMapping("collectionCenterRegister")
	public ResponseEntity<CommonResponse<String>> collectionCenterRegister(
			@RequestBody CollectionCenterRegisterRequest collectionCenterRegisterRequest) {

		return new ResponseEntity<>(authService.collectionCenterRegister(collectionCenterRegisterRequest),HttpStatus.OK);
	}

	@PostMapping("login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
	}

}
