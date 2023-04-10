package com.greencity.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.RequestCreatingRequest;
import com.greencity.app.service.RequestService;

@RestController
@RequestMapping("/api/request")
public class RequestController {
	
	private RequestService requestService;
	
	@PostMapping("/requestcreation/")
	public ResponseEntity<CommonResponse<String>> createRequest(
			@RequestBody RequestCreatingRequest requestCreatingRequest) {
		return new ResponseEntity<>(requestService.createRequest(requestCreatingRequest),HttpStatus.OK);
	}
}
