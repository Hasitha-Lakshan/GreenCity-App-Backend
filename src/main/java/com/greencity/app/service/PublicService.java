package com.greencity.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.greencity.app.dto.CollectionCenterDetailsResponse;
import com.greencity.app.dto.CollectionCenterSummaryResponse;
import com.greencity.app.dto.CommonResponse;
import com.greencity.app.entity.CollectionCenter;
import com.greencity.app.repository.CollectionCenterRepository;

@Service
public class PublicService {

	@Autowired
	private CollectionCenterRepository collectionCenterRepository;

	@Autowired
	private CommonResponse<List<CollectionCenterSummaryResponse>> commonResponse;

	// This function is used to get active collection centers according to the given
	// pagination
	public CommonResponse<List<CollectionCenterSummaryResponse>> getAllCollectionCenters(int pageNumber, int pageSize) {

		Page<CollectionCenter> collectionCenters = collectionCenterRepository.findByActive(true,
				PageRequest.of(pageNumber, pageSize));
		List<CollectionCenterSummaryResponse> response = new ArrayList<CollectionCenterSummaryResponse>();

		if (!collectionCenters.isEmpty()) {
			for (CollectionCenter collectionCenter : collectionCenters) {
				CollectionCenterSummaryResponse item = new CollectionCenterSummaryResponse();
				item.setCenterName(collectionCenter.getCentertName());
				item.setUsername(collectionCenter.getUsername());
				item.setLocation(collectionCenter.getLocation());
				item.setWasteType(collectionCenter.getWasteType());
				response.add(item);
			}
		}
		commonResponse.setResponse(response);
		commonResponse.setStatus(true);
		return commonResponse;
	}

	// this function is used to get collection center details using collection center username
	@Transactional
	public CollectionCenterDetailsResponse getCollectionCenterDetails(String collectionCenterUsername) {
		if (collectionCenterUsername != "") {
			CollectionCenter collectionCenter = collectionCenterRepository.findByUsername(collectionCenterUsername);
			if (collectionCenter != null) {
				CollectionCenterDetailsResponse collectionCenterDetailsResponse = new CollectionCenterDetailsResponse();
				collectionCenterDetailsResponse.setUsername(collectionCenter.getUsername());
				collectionCenterDetailsResponse.setCenterName(collectionCenter.getCentertName());
				collectionCenterDetailsResponse.setContactNumber(collectionCenter.getContactNumber());
				collectionCenterDetailsResponse.setAddressLine1(collectionCenter.getAddressLine1());
				collectionCenterDetailsResponse.setAddressLine2(collectionCenter.getAddressLine2());
				collectionCenterDetailsResponse.setAddressLine3(collectionCenter.getAddressLine3());
				collectionCenterDetailsResponse.setLocation(collectionCenter.getLocation());
				collectionCenterDetailsResponse.setWasteType(collectionCenter.getWasteType());
				collectionCenterDetailsResponse.setPayment(collectionCenter.getPayment());
				collectionCenterDetailsResponse.setDescription(collectionCenter.getDescription());

				if (collectionCenter.getWorkingDays() != null) {
					ArrayList<String> workingDays = new ArrayList<String>();
					for (String workingDay : collectionCenter.getWorkingDays()) {
						workingDays.add(workingDay);
					}
					collectionCenterDetailsResponse.setWorkingDays(workingDays);
				}
				return collectionCenterDetailsResponse;
			}
		}
		return null;
	}

}
