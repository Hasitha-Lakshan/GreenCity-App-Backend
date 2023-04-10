package com.greencity.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greencity.app.entity.CollectionCenter;
import com.greencity.app.entity.Customer_Request;
import com.greencity.app.entity.User;


public interface Customer_RequestRepository extends JpaRepository<Customer_Request, Integer>  {
 
	Customer_Request findByRequestId(int requestId);
	
//	List<Customer_Request> findByCustomerAndOrderStatus(User user, String requestStatus);
//
//	List<Customer_Request> findByCollectionCenterUsernameAndOrderStatus(String collectionCenterName, String requestStatus);

	void deleteByRequestId(int requestId);

	List<Customer_Request> findByCollectionCenter(CollectionCenter collectionCenter);
}
