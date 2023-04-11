package com.greencity.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greencity.app.dto.CollectionCenterDetailsResponse;
import com.greencity.app.dto.CollectionCenterDetailsUpdateRequest;
import com.greencity.app.dto.CollectionCenterSettingsResponse;
import com.greencity.app.dto.CollectionCenterSettingsUpdateRequset;
import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.ProfileRequest;
import com.greencity.app.dto.UserSettingsResponse;
import com.greencity.app.dto.UserSettingsUpdateRequest;
import com.greencity.app.entity.CollectionCenter;
import com.greencity.app.entity.User;
import com.greencity.app.repository.CollectionCenterRepository;
import com.greencity.app.repository.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CollectionCenterRepository collectionCenterRepository;

	@Autowired
	private CommonResponse<String> commonResponse;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// This function is used to get user settings using username
	public UserSettingsResponse getUserSettings(ProfileRequest profileDetailsRequest) {
		if (profileDetailsRequest != null && profileDetailsRequest.getRole().equals("USER")) {
			User user = userRepository.findByUsername(profileDetailsRequest.getUsername());
			if (user != null) {
				UserSettingsResponse userDetailsResponse = new UserSettingsResponse();
				userDetailsResponse.setUsername(user.getUsername());
				userDetailsResponse.setFirstName(user.getFirstName());
				userDetailsResponse.setLastName(user.getLastName());
				userDetailsResponse.setEmail(user.getEmail());
				userDetailsResponse.setContactNumber(user.getContactNumber());
				userDetailsResponse.setAddressLine1(user.getAddressLine1());
				userDetailsResponse.setAddressLine2(user.getAddressLine2());
				userDetailsResponse.setAddressLine3(user.getAddressLine3());
				return userDetailsResponse;
			}
		}
		return null;
	}
	
	// This function is used to update user settings using username
	public CommonResponse<String> updateUserSettings(UserSettingsUpdateRequest userSettingsUpdateRequest) {
		if (userSettingsUpdateRequest != null) {
			User user = userRepository.findByUsername(userSettingsUpdateRequest.getUsername());
			if (user != null) {
				user.setFirstName(userSettingsUpdateRequest.getFirstName());
				user.setLastName(userSettingsUpdateRequest.getLastName());
				user.setContactNumber(userSettingsUpdateRequest.getContactNumber());
				user.setAddressLine1(userSettingsUpdateRequest.getAddressLine1());
				user.setAddressLine2(userSettingsUpdateRequest.getAddressLine2());
				user.setAddressLine3(userSettingsUpdateRequest.getAddressLine3());
				userRepository.save(user);
				
				commonResponse.setResponse("User details updated successfully");
				commonResponse.setStatus(true);
				return commonResponse;

			} else {
				commonResponse.setResponse("Invalid username");
				commonResponse.setStatus(false);
				return commonResponse;
			}
		} else {
			commonResponse.setResponse("Invalid request");
			commonResponse.setStatus(false);
			return commonResponse;
		}
	}

	// This function is used to get collection center settings using username
	public CollectionCenterSettingsResponse getCollectionCenterSettings(ProfileRequest profileRequest) {
		if (profileRequest != null && profileRequest.getRole().equals("COLLECTION_CENTER")) {
			CollectionCenter collectionCenter = collectionCenterRepository.findByUsername(profileRequest.getUsername());
			if (collectionCenter != null) {
				CollectionCenterSettingsResponse response = new CollectionCenterSettingsResponse();
				response.setUsername(collectionCenter.getUsername());
				response.setCenterName(collectionCenter.getCentertName());
				response.setEmail(collectionCenter.getEmail());
				response.setContactNumber(collectionCenter.getContactNumber());
				response.setAddressLine1(collectionCenter.getAddressLine1());
				response.setAddressLine2(collectionCenter.getAddressLine2());
				response.setAddressLine3(collectionCenter.getAddressLine3());
				response.setLocation(collectionCenter.getLocation());
				response.setActive(collectionCenter.isActive());
				return response;
			}
		}
		return null;
	}
	
	// This function is used to update collection center settings using username
	public CommonResponse<String> updateCollectionCenterSettings(CollectionCenterSettingsUpdateRequset collectionCenterSettingsUpdateRequset) {
		if (collectionCenterSettingsUpdateRequset != null) {
			CollectionCenter collectionCenter = collectionCenterRepository.findByUsername(collectionCenterSettingsUpdateRequset.getUsername());
			if (collectionCenter != null) {
				collectionCenter.setCentertName(collectionCenterSettingsUpdateRequset.getCenterName());
				collectionCenter.setContactNumber(collectionCenterSettingsUpdateRequset.getContactNumber());
				collectionCenter.setAddressLine1(collectionCenterSettingsUpdateRequset.getAddressLine1());
				collectionCenter.setAddressLine2(collectionCenterSettingsUpdateRequset.getAddressLine2());
				collectionCenter.setAddressLine3(collectionCenterSettingsUpdateRequset.getAddressLine3());
				collectionCenter.setLocation(collectionCenterSettingsUpdateRequset.getLocation());
				collectionCenterRepository.save(collectionCenter);
				
				commonResponse.setResponse("Collection center details updated successfully");
				commonResponse.setStatus(true);
				return commonResponse;
			} else {
				commonResponse.setResponse("Invalid username");
				commonResponse.setStatus(false);
				return commonResponse;
			}
		} else {
			commonResponse.setResponse("Invalid request");
			commonResponse.setStatus(false);
			return commonResponse;
		}
	}

	// This function is used to get collection center details
	public CollectionCenterDetailsResponse getCollectionCenterDetails(ProfileRequest profileRequest) {
		if (profileRequest != null && profileRequest.getRole().equals("COLLECTION_CENTER")) {
			CollectionCenter collectionCenter = collectionCenterRepository.findByUsername(profileRequest.getUsername());
			if (collectionCenter != null) {
				CollectionCenterDetailsResponse collectionCenterDetailsResponse = new CollectionCenterDetailsResponse();
				collectionCenterDetailsResponse.setUsername(collectionCenter.getUsername());
				collectionCenterDetailsResponse.setCenterName(collectionCenter.getCentertName());
				collectionCenterDetailsResponse.setEmail(collectionCenter.getEmail());
				collectionCenterDetailsResponse.setContactNumber(collectionCenter.getContactNumber());
				collectionCenterDetailsResponse.setAddressLine1(collectionCenter.getAddressLine1());
				collectionCenterDetailsResponse.setAddressLine2(collectionCenter.getAddressLine2());
				collectionCenterDetailsResponse.setAddressLine3(collectionCenter.getAddressLine3());
				collectionCenterDetailsResponse.setLocation(collectionCenter.getLocation());
				collectionCenterDetailsResponse.setWastetype(collectionCenter.getWaste_type());
				collectionCenterDetailsResponse.setPayment(collectionCenter.getPayment());
				collectionCenterDetailsResponse.setDescription(collectionCenter.getDescription());
				collectionCenterDetailsResponse.setActive(collectionCenter.isActive());
				return collectionCenterDetailsResponse;
			}
		}
		return null;
	}

	// This function is used to update collection center details
	public CommonResponse<String> updateCollectionCenterDetails(CollectionCenterDetailsUpdateRequest collectionCenterDetailsUpdateRequest) {
		if (collectionCenterDetailsUpdateRequest != null) {
			CollectionCenter collectionCenter = collectionCenterRepository.findByUsername(collectionCenterDetailsUpdateRequest.getUsername());
			if (collectionCenter != null) {
				collectionCenter.setCentertName(collectionCenterDetailsUpdateRequest.getCentername());
				collectionCenter.setContactNumber(collectionCenterDetailsUpdateRequest.getContactnumber());
				collectionCenter.setAddressLine1(collectionCenterDetailsUpdateRequest.getAddressline1());
				collectionCenter.setAddressLine2(collectionCenterDetailsUpdateRequest.getAddressline2());
				collectionCenter.setAddressLine3(collectionCenterDetailsUpdateRequest.getAddressline3());
				collectionCenter.setLocation(collectionCenterDetailsUpdateRequest.getLocation());
				collectionCenter.setWaste_type(collectionCenterDetailsUpdateRequest.getWastetype());
				collectionCenter.setPayment(collectionCenterDetailsUpdateRequest.getPayment());
				collectionCenter.setDescription(collectionCenterDetailsUpdateRequest.getDescription());
				collectionCenterRepository.save(collectionCenter);
				
				commonResponse.setResponse("Collection center details updated successfully");
				commonResponse.setStatus(true);
				return commonResponse;
			} else {
				commonResponse.setResponse("Invalid username");
				commonResponse.setStatus(false);
				return commonResponse;
			}
		} else {
			commonResponse.setResponse("Invalid request");
			commonResponse.setStatus(false);
			return commonResponse;
		}
	}

	private Boolean encodePassword(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}
