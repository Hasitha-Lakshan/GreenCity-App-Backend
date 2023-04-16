package com.greencity.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.PickupRequestDetailsResponse;
import com.greencity.app.dto.PickupRequestRequest;
import com.greencity.app.dto.PickupRequestStatusUpdateRequest;
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

	@Autowired
	private CommonResponse<PickupRequestDetailsResponse> pickupRequestDetailsResponse;

	public CommonResponse<String> createRequest(PickupRequestRequest pickupRequestRequest) {
		if (pickupRequestRequest != null) {
			User user = userRepository.findByUsername(pickupRequestRequest.getCustomerUsername());
			CollectionCenter collectionCenter = collectionCenterRepository
					.findByUsername(pickupRequestRequest.getCollectionCenterUsername());

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

	public CommonResponse<List<PickupRequestSummaryResponse>> getSummaryRequestList(
			SummaryListRequest summaryListRequest) {
		List<PickupRequestSummaryResponse> pickupRequestSummaryResponseList = new ArrayList<PickupRequestSummaryResponse>();

		if (summaryListRequest != null) {
			if (summaryListRequest.getUserRole().equals("COLLECTION_CENTER")) {
				CollectionCenter collectionCenter = collectionCenterRepository
						.findByUsername(summaryListRequest.getUsername());

				if (collectionCenter != null) {
					List<PickupRequest> pickupRequestList = pickupRequestRepository
							.findByCollectionCenterAndRequestStatus(collectionCenter, summaryListRequest.getListType());

					if (!pickupRequestList.isEmpty()) {
						for (PickupRequest pickupRequest : pickupRequestList) {
							PickupRequestSummaryResponse pickupRequestSummaryResponse = new PickupRequestSummaryResponse();
							pickupRequestSummaryResponse.setRequestId(pickupRequest.getRequestId());
							pickupRequestSummaryResponse
									.setCollectionCenterName(pickupRequest.getCollectionCenter().getCentertName());
							pickupRequestSummaryResponse.setCustomerName(pickupRequest.getUser().getFirstName() + " "
									+ pickupRequest.getUser().getLastName());
							pickupRequestSummaryResponse
									.setWasteType(pickupRequest.getCollectionCenter().getWasteType());
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
					List<PickupRequest> pickupRequestList = pickupRequestRepository.findByUserAndRequestStatus(user,
							summaryListRequest.getListType());

					if (!pickupRequestList.isEmpty()) {
						for (PickupRequest pickupRequest : pickupRequestList) {
							PickupRequestSummaryResponse pickupRequestSummaryResponse = new PickupRequestSummaryResponse();
							pickupRequestSummaryResponse.setRequestId(pickupRequest.getRequestId());
							pickupRequestSummaryResponse
									.setCollectionCenterName(pickupRequest.getCollectionCenter().getCentertName());
							pickupRequestSummaryResponse.setCustomerName(pickupRequest.getUser().getFirstName() + " "
									+ pickupRequest.getUser().getLastName());
							pickupRequestSummaryResponse
									.setWasteType(pickupRequest.getCollectionCenter().getWasteType());
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

	@Transactional
	public CommonResponse<PickupRequestDetailsResponse> getPickupRequestDetails(int requestId) {
		PickupRequest pickupRequest = pickupRequestRepository.findByRequestId(requestId);

		if (pickupRequest != null) {
			PickupRequestDetailsResponse pickupReuestDetailsResponse = new PickupRequestDetailsResponse();
			pickupReuestDetailsResponse.setRequestId(pickupRequest.getRequestId());
			pickupReuestDetailsResponse.setCollectionCenterName(pickupRequest.getCollectionCenter().getCentertName());
			pickupReuestDetailsResponse.setCustomerName(pickupRequest.getUser().getFirstName() + " " + pickupRequest.getUser().getLastName());
			pickupReuestDetailsResponse.setStatus(pickupRequest.getRequestStatus());
			pickupReuestDetailsResponse.setNote(pickupRequest.getNote());
			pickupReuestDetailsResponse.setCreatedDate(pickupRequest.getCreatedDate());
			pickupReuestDetailsResponse.setAcceptedDate(pickupRequest.getAcceptedDate());
			pickupReuestDetailsResponse.setCompletedDate(pickupRequest.getCompletedDate());
			pickupReuestDetailsResponse.setCanceledDate(pickupRequest.getCanceledDate());
			pickupReuestDetailsResponse.setWasteType(pickupRequest.getCollectionCenter().getWasteType());
			pickupReuestDetailsResponse.setQuantity(pickupRequest.getQuantity());
			pickupReuestDetailsResponse.setTotalPayment(pickupRequest.getTotalPayment());
			pickupReuestDetailsResponse.setLocation(pickupRequest.getCollectionLocation());
			pickupReuestDetailsResponse.setAddressLine1(pickupRequest.getCollectionAddressLine1());
			pickupReuestDetailsResponse.setAddressLine2(pickupRequest.getCollectionAddressLine2());
			pickupReuestDetailsResponse.setAddressLine3(pickupRequest.getCollectionAddressLine3());
			
			if (pickupRequest.getCollectionCenter().getWorkingDays() != null) {
				ArrayList<String> workingDays = new ArrayList<String>();
				for (String workingDay : pickupRequest.getCollectionCenter().getWorkingDays()) {
					workingDays.add(workingDay);
				}
				pickupReuestDetailsResponse.setWorkingDays(workingDays);
			}

			pickupRequestDetailsResponse.setResponse(pickupReuestDetailsResponse);
			pickupRequestDetailsResponse.setStatus(true);
			return pickupRequestDetailsResponse;
		} else {
			pickupRequestDetailsResponse.setResponse(null);
			pickupRequestDetailsResponse.setStatus(false);
			return pickupRequestDetailsResponse;
		}
	}

	public CommonResponse<String> updatePickupRequestStatus(PickupRequestStatusUpdateRequest pickupRequestUpdateRequest) {

		if (pickupRequestUpdateRequest != null) {
			if (pickupRequestUpdateRequest.getUserRole().equals("COLLECTION_CENTER")) {
				CollectionCenter collectionCenter = collectionCenterRepository.findByUsername(pickupRequestUpdateRequest.getUsername());
				PickupRequest pickupRequest = pickupRequestRepository.findByRequestId(pickupRequestUpdateRequest.getRequestId());

				if (collectionCenter != null && pickupRequest != null) {
					if (pickupRequestUpdateRequest.getUpdatedStatus().equals("ACTIVE")) {
						pickupRequest.setAcceptedDate(new Date());
						pickupRequest.setRequestStatus(pickupRequestUpdateRequest.getUpdatedStatus());
						pickupRequestRepository.save(pickupRequest);
						
						commonResponse.setResponse("Request status updated to ACTIVE!");
						commonResponse.setStatus(true);
						return commonResponse;
						
					} else if (pickupRequestUpdateRequest.getUpdatedStatus().equals("CANCELED")) {
						pickupRequest.setCanceledDate(new Date());
						pickupRequest.setRequestStatus(pickupRequestUpdateRequest.getUpdatedStatus());
						pickupRequestRepository.save(pickupRequest);
						
						commonResponse.setResponse("Request status updated to CANCELED!");
						commonResponse.setStatus(true);
						return commonResponse;
						
					} else {
						commonResponse.setResponse("Invalid status, request update failed!");
						commonResponse.setStatus(false);
						return commonResponse;
					}
				} else {
					commonResponse.setResponse("Invalid collection center usename or pickup request ID, request update failed!");
					commonResponse.setStatus(false);
					return commonResponse;
				}

			} else if (pickupRequestUpdateRequest.getUserRole().equals("USER")) {
				User user = userRepository.findByUsername(pickupRequestUpdateRequest.getUsername());
				PickupRequest pickupRequest = pickupRequestRepository.findByRequestId(pickupRequestUpdateRequest.getRequestId());

				if (user != null  && pickupRequest != null) {
					if (pickupRequestUpdateRequest.getUpdatedStatus().equals("COMPLETED")) {
						pickupRequest.setCompletedDate(new Date());
						pickupRequest.setRequestStatus(pickupRequestUpdateRequest.getUpdatedStatus());
						pickupRequestRepository.save(pickupRequest);
						
						commonResponse.setResponse("Request status updated to COMPLETED!");
						commonResponse.setStatus(true);
						return commonResponse;
						
					} else {
						commonResponse.setResponse("Invalid status, request update failed!");
						commonResponse.setStatus(false);
						return commonResponse;
					}
				} else {
					commonResponse.setResponse("Invalid customer usename or pickup request ID, request update failed!");
					commonResponse.setStatus(false);
					return commonResponse;
				}
			} else {
				commonResponse.setResponse("Invalid user role, request update failed!");
				commonResponse.setStatus(false);
				return commonResponse;
			}
		}
		commonResponse.setResponse("Invalid pickup update request, request update failed!");
		commonResponse.setStatus(false);
		return commonResponse;
	}
}
