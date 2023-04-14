package com.greencity.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greencity.app.dto.CollectionCenterDetailsResponse;
import com.greencity.app.dto.CollectionCenterSummaryResponse;
import com.greencity.app.dto.CommonResponse;
import com.greencity.app.service.PublicService;

@RestController
@RequestMapping("/api/public/")
public class PublicController {

	@Autowired
	private PublicService publicService;

	@GetMapping("collectionCenters/{pageNumber}/{pageSize}")
	public ResponseEntity<CommonResponse<List<CollectionCenterSummaryResponse>>> userRegister(
			@PathVariable int pageNumber, @PathVariable int pageSize) {
		return new ResponseEntity<>(publicService.getAllCollectionCenters(pageNumber, pageSize), HttpStatus.OK);
	}

	@GetMapping("collectionCenter/{username}")
	public ResponseEntity<CollectionCenterDetailsResponse> getCollectionCenterDetails(@PathVariable String username) {
		return new ResponseEntity<CollectionCenterDetailsResponse>(publicService.getCollectionCenterDetails(username),
				HttpStatus.OK);
	}

}
