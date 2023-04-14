package com.greencity.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.greencity.app.dto.CollectionCenterDetailsResponse;
import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.ProfileRequest;
import com.greencity.app.entity.CollectionCenter;
import com.greencity.app.repository.CollectionCenterRepository;

@Service
public class PublicService {

	@Autowired
	private CollectionCenterRepository collectionCenterRepository;

	@Autowired
	private CommonResponse<List<CollectionCenterDetailsResponse>> commonResponse;

	//	This function is used to get active collection centers according to the given pagination
	public CommonResponse<List<CollectionCenterDetailsResponse>> getAllCollectionCenters(int pageNumber, int pageSize) {

		Page<CollectionCenter> collectionCenters = collectionCenterRepository.findByActive(true, PageRequest.of(pageNumber, pageSize));
		List<CollectionCenterDetailsResponse> response = new ArrayList<CollectionCenterDetailsResponse>();

		if (!collectionCenters.isEmpty()) {
			for (CollectionCenter collectionCenter : collectionCenters) {
				CollectionCenterDetailsResponse item = new CollectionCenterDetailsResponse();
				item.setCenterName(collectionCenter.getCentertName());
				item.setUsername(collectionCenter.getUsername());
				item.setContactNumber(collectionCenter.getContactNumber());
				item.setEmail(collectionCenter.getEmail());
				item.setAddressLine1(collectionCenter.getAddressLine1());
				item.setAddressLine2(collectionCenter.getAddressLine2());
				item.setAddressLine3(collectionCenter.getAddressLine3());
				item.setLocation(collectionCenter.getLocation());
				item.setWasteType(collectionCenter.getWaste_type());
				response.add(item);
			}
		}
		commonResponse.setResponse(response);
		commonResponse.setStatus(true);
		return commonResponse;
	}

	public CollectionCenterDetailsResponse getCollectionCenterDetails(ProfileRequest profileDetailsRequest) {
		if (profileDetailsRequest != null && profileDetailsRequest.getRole().equals("COLLECTION_CENTER")) {
			CollectionCenter collectionCenter = collectionCenterRepository
					.findByUsername(profileDetailsRequest.getUsername());
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
				collectionCenterDetailsResponse.setWasteType(collectionCenter.getWaste_type());
				collectionCenterDetailsResponse.setPayment(collectionCenter.getPayment());
				collectionCenterDetailsResponse.setDescription(collectionCenter.getDescription());
				return collectionCenterDetailsResponse;
			}
		}
		return null;
	}

}
