package com.greencity.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.PickupRequestRequest;
import com.greencity.app.dto.PickupRequestSummaryResponse;
import com.greencity.app.dto.SummaryListRequest;
import com.greencity.app.entity.CollectionCenter;
import com.greencity.app.entity.PickupRequest;
import com.greencity.app.entity.User;
import com.greencity.app.repository.CollectionCenterRepository;
import com.greencity.app.repository.PickupRequestRepository;
import com.greencity.app.repository.UserRepository;

@Service
public class RequestService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CollectionCenterRepository collectionCenterRepository;

	@Autowired
	private PickupRequestRepository pickupRequestRepository;

	@Autowired
	private CommonResponse<String> commonResponse;
	
	@Autowired
	private CommonResponse<List<PickupRequestSummaryResponse>> pickupRequestSummaryCommonResponse;

	public CommonResponse<String> createRequest(PickupRequestRequest pickupRequestRequest) {
		if (pickupRequestRequest != null) {
			User user = userRepository.findByUsername(pickupRequestRequest.getCustomerUsername());
			CollectionCenter collectionCenter = collectionCenterRepository.findByUsername(pickupRequestRequest.getCollectionCenterUsername());

			if (user != null) {
				if (collectionCenter != null) {
					PickupRequest newRequest = new PickupRequest();
					newRequest.setCollectionCenter(collectionCenter);
					newRequest.setUser(user);
					newRequest.setCollectionAddressLine1(pickupRequestRequest.getCustomerAddressLine1());
					newRequest.setCollectionAddressLine2(pickupRequestRequest.getCustomerAddressLine2());
					newRequest.setCollectionAddressLine3(pickupRequestRequest.getCustomerAddressLine3());
					newRequest.setCollectionLocation(pickupRequestRequest.getCustomerLocation());
					newRequest.setNote(pickupRequestRequest.getNote());
					newRequest.setQuantity(pickupRequestRequest.getQuantity());
					newRequest.setTotalPayment(pickupRequestRequest.getTotalPayment());
					newRequest.setRequestStatus("INPROGRESS");
					newRequest.setCreatedDate(new Date());
					pickupRequestRepository.save(newRequest);

					commonResponse.setResponse("The request created successfully!");
					commonResponse.setStatus(true);
					return commonResponse;

				} else {
					commonResponse.setResponse("Invalid collection center username, request creation failed!");
					commonResponse.setStatus(false);
					return commonResponse;
				}
			} else {
				commonResponse.setResponse("Invalid username, request creation failed!");
				commonResponse.setStatus(false);
				return commonResponse;
			}
		} else {
			commonResponse.setResponse("Invalid pickup request, request creation failed!");
			commonResponse.setStatus(false);
			return commonResponse;
		}
	}
	
	public CommonResponse<List<PickupRequestSummaryResponse>> getSummaryRequestList(SummaryListRequest summaryListRequest) {
		List<PickupRequestSummaryResponse> pickupRequestSummaryResponseList = new ArrayList<PickupRequestSummaryResponse>();
		
		if (summaryListRequest != null) {
			if (summaryListRequest.getUserRole().equals("COLLECTION_CENTER")) {
				CollectionCenter collectionCenter = collectionCenterRepository.findByUsername(summaryListRequest.getUsername());
				
				if (collectionCenter != null) {
					List<PickupRequest> pickupRequestList = pickupRequestRepository.findByCollectionCenterAndRequestStatus(collectionCenter, summaryListRequest.getListType());
					
					if (!pickupRequestList.isEmpty()) {
						for (PickupRequest pickupRequest : pickupRequestList) {
							PickupRequestSummaryResponse pickupRequestSummaryResponse = new PickupRequestSummaryResponse();
							pickupRequestSummaryResponse.setRequestId(pickupRequest.getRequestId());
							pickupRequestSummaryResponse.setCollectionCenterName(pickupRequest.getCollectionCenter().getCentertName());
							pickupRequestSummaryResponse.setCustomerName(pickupRequest.getUser().getFirstName() + " " + pickupRequest.getUser().getLastName());
							pickupRequestSummaryResponse.setWasteType(pickupRequest.getCollectionCenter().getWasteType());
							pickupRequestSummaryResponse.setCreatedDate(pickupRequest.getCreatedDate());
							pickupRequestSummaryResponseList.add(pickupRequestSummaryResponse);
						}
						pickupRequestSummaryCommonResponse.setResponse(pickupRequestSummaryResponseList);
						pickupRequestSummaryCommonResponse.setStatus(true);
						return pickupRequestSummaryCommonResponse;
					} else {
						pickupRequestSummaryCommonResponse.setResponse(pickupRequestSummaryResponseList);
						pickupRequestSummaryCommonResponse.setStatus(true);
						return pickupRequestSummaryCommonResponse;
					}
				}
				
			} else if (summaryListRequest.getUserRole().equals("USER")) {
				User user = userRepository.findByUsername(summaryListRequest.getUsername());
				
				if (user != null) {
					List<PickupRequest> pickupRequestList = pickupRequestRepository.findByUserAndRequestStatus(user, summaryListRequest.getListType());
					
					if (!pickupRequestList.isEmpty()) {
						for (PickupRequest pickupRequest : pickupRequestList) {
							PickupRequestSummaryResponse pickupRequestSummaryResponse = new PickupRequestSummaryResponse();
							pickupRequestSummaryResponse.setRequestId(pickupRequest.getRequestId());
							pickupRequestSummaryResponse.setCollectionCenterName(pickupRequest.getCollectionCenter().getCentertName());
							pickupRequestSummaryResponse.setCustomerName(pickupRequest.getUser().getFirstName() + " " + pickupRequest.getUser().getLastName());
							pickupRequestSummaryResponse.setWasteType(pickupRequest.getCollectionCenter().getWasteType());
							pickupRequestSummaryResponse.setCreatedDate(pickupRequest.getCreatedDate());
							pickupRequestSummaryResponseList.add(pickupRequestSummaryResponse);
						}
						pickupRequestSummaryCommonResponse.setResponse(pickupRequestSummaryResponseList);
						pickupRequestSummaryCommonResponse.setStatus(true);
						return pickupRequestSummaryCommonResponse;
					} else {
						pickupRequestSummaryCommonResponse.setResponse(pickupRequestSummaryResponseList);
						pickupRequestSummaryCommonResponse.setStatus(true);
						return pickupRequestSummaryCommonResponse;
					}
				}
			}
			pickupRequestSummaryCommonResponse.setResponse(pickupRequestSummaryResponseList);
			pickupRequestSummaryCommonResponse.setStatus(false);
			return pickupRequestSummaryCommonResponse;
		}
		return null;
	}
}
