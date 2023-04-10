package com.greencity.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.greencity.app.dto.CollectionCenterDetailsResponse;
import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.ProfileDetailsRequest;
import com.greencity.app.entity.CollectionCenter;
import com.greencity.app.repository.CollectionCenterRepository;

@Service
public class PublicService {

	@Autowired
	private CollectionCenterRepository collectionCenterRepository;

	@Autowired
	private CommonResponse<List<CollectionCenterDetailsResponse>> commonResponse;

	public CommonResponse<List<CollectionCenterDetailsResponse>> getAllCollectionCenters(int pageNumber, int pageSize) {

		Page<CollectionCenter> collectionCenters = collectionCenterRepository.findAll(PageRequest.of(pageNumber, pageSize));
		List<CollectionCenterDetailsResponse> response = new ArrayList<CollectionCenterDetailsResponse>();

		if (!collectionCenters.isEmpty()) {

			for (CollectionCenter collectionCenter : collectionCenters) {
				CollectionCenterDetailsResponse item = new CollectionCenterDetailsResponse();
				if(collectionCenter.isMoreDetailStatus() && collectionCenter.isActive_or_disable()) {
					item.setCenterName(collectionCenter.getCentertName());
					item.setUsername(collectionCenter.getUsername());
					item.setWastetype(collectionCenter.getWaste_type());
					item.setLocation(collectionCenter.getLocation());
					response.add(item);
				}
				
				item.setCenterName(collectionCenter.getCentertName());
				item.setUsername(collectionCenter.getUsername());
				item.setContactNumber(collectionCenter.getContactNumber());
				item.setEmail(collectionCenter.getEmail());
				item.setAddressLine1(collectionCenter.getAddressLine1());
				item.setAddressLine2(collectionCenter.getAddressLine2());
				item.setAddressLine3(collectionCenter.getAddressLine3());
				item.setLocation(collectionCenter.getLocation());
				response.add(item);
				
			}
		}

		commonResponse.setResponse(response);
		commonResponse.setStatus(true);
		return commonResponse;
	}


public CollectionCenterDetailsResponse getCollectionCenterDetails(ProfileDetailsRequest profileDetailsRequest) {
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

}

