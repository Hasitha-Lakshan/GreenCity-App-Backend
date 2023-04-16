package com.greencity.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.PickupRequestRequest;
import com.greencity.app.dto.PickupRequestSummaryResponse;
import com.greencity.app.dto.SummaryListRequest;
import com.greencity.app.service.RequestService;

@RestController
@RequestMapping("/api/pickup")
public class RequestController {
	
	@Autowired
	private RequestService requestService;
	
	@PostMapping("/newRequest")
	public ResponseEntity<CommonResponse<String>> createRequest(
			@RequestBody PickupRequestRequest pickupRequestRequest) {
		return new ResponseEntity<>(requestService.createRequest(pickupRequestRequest),HttpStatus.OK);
	}
	
	@PostMapping("/summaryList")
	public ResponseEntity<CommonResponse<List<PickupRequestSummaryResponse>>> getSummaryRequestList(
			@RequestBody SummaryListRequest summaryListRequest) {
		return new ResponseEntity<>(requestService.getSummaryRequestList(summaryListRequest),HttpStatus.OK);
	}
}
