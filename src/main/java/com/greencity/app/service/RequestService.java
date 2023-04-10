package com.greencity.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.RequestCreatingRequest;
import com.greencity.app.entity.CollectionCenter;
import com.greencity.app.entity.Customer_Request;
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

	public CommonResponse<String> createRequest(RequestCreatingRequest requestCreatingRequest){
		if (requestCreatingRequest != null) {
			User customer = userRepository.findByUsername(requestCreatingRequest.getCustomerUsername());
			CollectionCenter collectionCenter = collectionCenterRepository.findByCenterId(requestCreatingRequest.getCollectionCenterId());
			
			if (collectionCenter != null && collectionCenter.isActive_or_disable()) {
				if (customer != null) {
					Customer_Request newRequest = new Customer_Request();
					
					newRequest.setCollectionCenter(collectionCenter);
					newRequest.setUser(customer);
					//newRequest.setRequestStatus(requestStatus: "INPROGRESS");
					newRequest.setCollectionAddressLine1(requestCreatingRequest.getCollectionAddressLine1());
					newRequest.setCollectionAddressLine2(requestCreatingRequest.getCollectionAddressLine2());
					newRequest.setCollectionAddressLine3(requestCreatingRequest.getCollectionAddressLine3());
					newRequest.setCollectionLocation(requestCreatingRequest.getCollectionLocation());
					newRequest.setNote(requestCreatingRequest.getNote());
					newRequest.setQuantity(requestCreatingRequest.getQuantity());
					newRequest.setTotalPayment(requestCreatingRequest.getTotalPayment());
					newRequest.setCreatedDate(requestCreatingRequest.getCreatedDate());
					
					customer_RequestRepository.save(newRequest);
					
					commonResponse.setResponse("The request created successfully!");
					commonResponse.setStatus(true);
					
					return commonResponse;
				
				}else {
					commonResponse.setResponse("Customer validation is failed, order creating is failed!");
					commonResponse.setStatus(false);
					
					return commonResponse;
				}
			}else {
				commonResponse.setResponse("Shop does not exsit, order creating is failed!");
				commonResponse.setStatus(false);
				
				return commonResponse;
			}
		}else {
			commonResponse.setResponse("The user not found, Buyer request creating is failed!");
			commonResponse.setStatus(false);
			
			return commonResponse;
		}
	}
}
