package com.greencity.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greencity.app.dto.CollectionCenterDetailAddRequest;
import com.greencity.app.dto.CollectionCenterDetailsResponse;
import com.greencity.app.dto.CollectionCenterDetailsUpdateRequset;
import com.greencity.app.dto.CollectionCenterFullDetailsUpdateRequset;
import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.ProfileDetailsRequest;
import com.greencity.app.dto.UserDetailsResponse;
import com.greencity.app.dto.UserDetailsUpdateRequset;
import com.greencity.app.service.ProfileService;

@RestController
@RequestMapping("/api/profile/")
public class ProfileManagementController {

	@Autowired
	private ProfileService profileService;

	@PostMapping("user")
	public ResponseEntity<UserDetailsResponse> getUserDetails(
			@RequestBody ProfileDetailsRequest profileDetailsReques) {
		return new ResponseEntity<UserDetailsResponse>(profileService.getUserDetails(profileDetailsReques),
				HttpStatus.OK);
	}

	@PostMapping("collectionCenter")
	public ResponseEntity<CollectionCenterDetailsResponse> getCollectionCenterDetails(
			@RequestBody ProfileDetailsRequest profileDetailsReques) {
		return new ResponseEntity<CollectionCenterDetailsResponse>(
				profileService.getCollectionCenterDetails(profileDetailsReques), 
				HttpStatus.OK);
	}

	@PutMapping("user/update")
	public ResponseEntity<CommonResponse<String>> updateUserDetails(
			@RequestBody UserDetailsUpdateRequset userDetailsUpdateRequset) {
		return new ResponseEntity<CommonResponse<String>>(profileService.updateUserDetails(userDetailsUpdateRequset),
				HttpStatus.OK);
	}

	@PutMapping("collectionCenter/updateMore")
	public ResponseEntity<CommonResponse<String>> updateFullCollectionCenterDetails(
			@RequestBody CollectionCenterFullDetailsUpdateRequset collectionCenterFullDetailsUpdateRequset) {
		return new ResponseEntity<CommonResponse<String>>(
				profileService.updateFullCollectionCenterDetails(collectionCenterFullDetailsUpdateRequset), HttpStatus.OK);
	}
	
	@PutMapping("collectionCenter/update")
	public ResponseEntity<CommonResponse<String>> updateCollectionCenterDetails(
			@RequestBody CollectionCenterDetailsUpdateRequset collectionCenterDetailsUpdateRequset) {
		return new ResponseEntity<CommonResponse<String>>(
				profileService.updateCollectionCenterDetails(collectionCenterDetailsUpdateRequset), HttpStatus.OK);
	}
	
	@PutMapping("collectionCenter/addDetail")
	public ResponseEntity<CommonResponse<String>> addCollectionCenterDetails(
			@RequestBody CollectionCenterDetailAddRequest collectionCenterDetailAddRequest) {
		return new ResponseEntity<CommonResponse<String>>(
				profileService.addCollectionCenterDetails(collectionCenterDetailAddRequest), HttpStatus.OK);
	}

}
