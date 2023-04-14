package com.greencity.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greencity.app.dto.CollectionCenterDetailsResponse;
import com.greencity.app.dto.CollectionCenterDetailsUpdateRequest;
import com.greencity.app.dto.CollectionCenterSettingsResponse;
import com.greencity.app.dto.CollectionCenterSettingsUpdateRequest;
import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.ProfileRequest;
import com.greencity.app.dto.UserSettingsResponse;
import com.greencity.app.dto.UserSettingsUpdateRequest;
import com.greencity.app.service.ProfileService;

@RestController
@RequestMapping("/api/profile/")
public class ProfileManagementController {

	@Autowired
	private ProfileService profileService;

	@PostMapping("userSettings")
	public ResponseEntity<UserSettingsResponse> getUserSettings(@RequestBody ProfileRequest profileRequest) {
		return new ResponseEntity<UserSettingsResponse>(profileService.getUserSettings(profileRequest), HttpStatus.OK);
	}
	
	@PutMapping("userSettings")
	public ResponseEntity<CommonResponse<String>> updateUserSettings(@RequestBody UserSettingsUpdateRequest userSettingsUpdateRequest) {
		return new ResponseEntity<CommonResponse<String>>(profileService.updateUserSettings(userSettingsUpdateRequest), HttpStatus.OK);
	}

	@PostMapping("collectionCenterSettings")
	public ResponseEntity<CollectionCenterSettingsResponse> getCollectionCenterSettings(@RequestBody ProfileRequest profileRequest) {
		return new ResponseEntity<CollectionCenterSettingsResponse>(profileService.getCollectionCenterSettings(profileRequest), HttpStatus.OK);
	}

	@PutMapping("collectionCenterSettings")
	public ResponseEntity<CommonResponse<String>> updateCollectionCenterSettings(@RequestBody CollectionCenterSettingsUpdateRequest collectionCenterSettingsUpdateRequset) {
		return new ResponseEntity<CommonResponse<String>>(profileService.updateCollectionCenterSettings(collectionCenterSettingsUpdateRequset), HttpStatus.OK);
	}
	
	@PostMapping("collectionCenterDetails")
	public ResponseEntity<CollectionCenterDetailsResponse> getCollectionCenterDetails(@RequestBody ProfileRequest profileRequest) {
		return new ResponseEntity<CollectionCenterDetailsResponse>(profileService.getCollectionCenterDetails(profileRequest), HttpStatus.OK);
	}
	
	@PutMapping("collectionCenterDetails")
	public ResponseEntity<CommonResponse<String>> updateCollectionCenterDetails(@RequestBody CollectionCenterDetailsUpdateRequest collectionCenterDetailsUpdateRequest) {
		return new ResponseEntity<CommonResponse<String>>(profileService.updateCollectionCenterDetails(collectionCenterDetailsUpdateRequest), HttpStatus.OK);
	}
}
