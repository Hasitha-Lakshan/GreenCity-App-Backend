package com.greencity.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greencity.app.dto.CollectionCenterDetailAddRequest;
import com.greencity.app.dto.CollectionCenterDetailsResponse;
import com.greencity.app.dto.CollectionCenterDetailsUpdateRequset;
import com.greencity.app.dto.CollectionCenterFullDetailsUpdateRequset;
import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.ProfileDetailsRequest;
import com.greencity.app.dto.UserDetailsResponse;
import com.greencity.app.dto.UserDetailsUpdateRequset;
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

	public UserDetailsResponse getUserDetails(ProfileDetailsRequest profileDetailsRequest) {
		if (profileDetailsRequest != null && profileDetailsRequest.getRole().equals("USER")) {
			User user = userRepository.findByUsername(profileDetailsRequest.getUsername());
			if (user != null) {
				UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
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

	public CollectionCenterDetailsResponse getCollectionCenterDetails(ProfileDetailsRequest profileDetailsRequest) {
		if (profileDetailsRequest != null && profileDetailsRequest.getRole().equals("COLLECTION_CENTER")) {
			CollectionCenter collectionCenter = collectionCenterRepository
					.findByUsername(profileDetailsRequest.getUsername());
			if (collectionCenter != null) {
				CollectionCenterDetailsResponse collectionCenterDetailsResponse = new CollectionCenterDetailsResponse();
				collectionCenterDetailsResponse.setCenterId(collectionCenter.getCenterId());
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
				collectionCenterDetailsResponse.setMoreDetailStatus(collectionCenter.isMoreDetailStatus());
				collectionCenterDetailsResponse.setStatus(collectionCenter.isActive_or_disable());
				return collectionCenterDetailsResponse;
			}
		}
		return null;
	}

	public CommonResponse<String> updateUserDetails(UserDetailsUpdateRequset userDetailsUpdateRequset) {
		if (userDetailsUpdateRequset != null) {
			User user = userRepository.findByUsername(userDetailsUpdateRequset.getCurrentUserName());
			if (user != null) {
					user.setFirstName(userDetailsUpdateRequset.getFirstName());
					user.setLastName(userDetailsUpdateRequset.getLastName());
					user.setUsername(userDetailsUpdateRequset.getUsername());
					user.setContactNumber(userDetailsUpdateRequset.getContactNumber());
					user.setEmail(userDetailsUpdateRequset.getEmail());
					user.setAddressLine1(userDetailsUpdateRequset.getAddressLine1());
					user.setAddressLine2(userDetailsUpdateRequset.getAddressLine2());
					user.setAddressLine3(userDetailsUpdateRequset.getAddressLine3());
					if (userDetailsUpdateRequset.getStatus().equals("off")) {
						user.setActive_or_disable(false);
					}else if (userDetailsUpdateRequset.getStatus().equals("on")) {
						user.setActive_or_disable(true);
					}
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

	public CommonResponse<String> updateCollectionCenterDetails(
			CollectionCenterDetailsUpdateRequset collectionCenterDetailsUpdateRequset) {
		if (collectionCenterDetailsUpdateRequset != null) {
			CollectionCenter collectionCenter = collectionCenterRepository
					.findByUsername(collectionCenterDetailsUpdateRequset.getCurrentUserName());
			if (collectionCenter != null) {
				collectionCenter.setUsername(collectionCenterDetailsUpdateRequset.getUsername());
					collectionCenter.setCentertName(collectionCenterDetailsUpdateRequset.getCentername());
					collectionCenter.setContactNumber(collectionCenterDetailsUpdateRequset.getContactnumber());
					collectionCenter.setEmail(collectionCenterDetailsUpdateRequset.getEmail());
					collectionCenter.setAddressLine1(collectionCenterDetailsUpdateRequset.getAddressline1());
					collectionCenter.setAddressLine2(collectionCenterDetailsUpdateRequset.getAddressline2());
					collectionCenter.setAddressLine3(collectionCenterDetailsUpdateRequset.getAddressline3());
					collectionCenter.setLocation(collectionCenterDetailsUpdateRequset.getLocation());
					if (collectionCenterDetailsUpdateRequset.getStatus().equals("off")) {
						collectionCenter.setActive_or_disable(false);
					}else if (collectionCenterDetailsUpdateRequset.getStatus().equals("on")) {
						collectionCenter.setActive_or_disable(true);
					}
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
	
	public CommonResponse<String> updateFullCollectionCenterDetails(
			CollectionCenterFullDetailsUpdateRequset collectionCenterDetailsFullUpdateRequset) {
		if (collectionCenterDetailsFullUpdateRequset != null) {
			CollectionCenter collectionCenter = collectionCenterRepository
					.findByUsername(collectionCenterDetailsFullUpdateRequset.getCurrentUserName());
			if (collectionCenter != null) {
				collectionCenter.setUsername(collectionCenterDetailsFullUpdateRequset.getUsername());
					collectionCenter.setCentertName(collectionCenterDetailsFullUpdateRequset.getCentername());
					collectionCenter.setContactNumber(collectionCenterDetailsFullUpdateRequset.getContactnumber());
					collectionCenter.setEmail(collectionCenterDetailsFullUpdateRequset.getEmail());
					collectionCenter.setAddressLine1(collectionCenterDetailsFullUpdateRequset.getAddressline1());
					collectionCenter.setAddressLine2(collectionCenterDetailsFullUpdateRequset.getAddressline2());
					collectionCenter.setAddressLine3(collectionCenterDetailsFullUpdateRequset.getAddressline3());
					collectionCenter.setLocation(collectionCenterDetailsFullUpdateRequset.getLocation());
					collectionCenter.setWaste_type(collectionCenterDetailsFullUpdateRequset.getWastetype());
					collectionCenter.setPayment(collectionCenterDetailsFullUpdateRequset.getPayment());
					collectionCenter.setDescription(collectionCenterDetailsFullUpdateRequset.getDescription());
					if (collectionCenterDetailsFullUpdateRequset.getStatus().equals("off")) {
						collectionCenter.setActive_or_disable(false);
					}else if (collectionCenterDetailsFullUpdateRequset.getStatus().equals("on")) {
						collectionCenter.setActive_or_disable(true);
					}
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

	public CommonResponse<String> addCollectionCenterDetails(
			CollectionCenterDetailAddRequest collectionCenterDetailAddRequest) {
		if (collectionCenterDetailAddRequest != null) {
			CollectionCenter collectionCenter = collectionCenterRepository
					.findByUsername(collectionCenterDetailAddRequest.getCurrentUserName());
			if (collectionCenter != null) {
				collectionCenter.setWaste_type(collectionCenterDetailAddRequest.getWastetype());
				collectionCenter.setPayment(collectionCenterDetailAddRequest.getPayment());
				collectionCenter.setDescription(collectionCenterDetailAddRequest.getDescription());
//				collectionCenter.setCollectionCenter_WorkingDays(collectionCenterDetailAddRequest.getWorkingDays());
				collectionCenter.setMoreDetailStatus(true);
				collectionCenterRepository.save(collectionCenter);
				commonResponse.setResponse("Collection center new details added  successfully");
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
