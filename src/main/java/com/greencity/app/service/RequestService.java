package com.greencity.app.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.PickupRequestRequest;
import com.greencity.app.entity.CollectionCenter;
import com.greencity.app.entity.PickupRequest;
import com.greencity.app.entity.User;
import com.greencity.app.repository.CollectionCenterRepository;
import com.greencity.app.repository.Customer_RequestRepository;
import com.greencity.app.repository.UserRepository;

@Service
public class RequestService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CollectionCenterRepository collectionCenterRepository;

	@Autowired
	private Customer_RequestRepository customer_RequestRepository;

	@Autowired
	private CommonResponse<String> commonResponse;

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
					customer_RequestRepository.save(newRequest);

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
}
