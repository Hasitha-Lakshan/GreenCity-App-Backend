package com.greencity.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greencity.app.entity.CollectionCenter;

public interface CollectionCenterRepository extends JpaRepository<CollectionCenter, Integer> {

	CollectionCenter findByUsername(String username);

	CollectionCenter findByEmail(String email);

	CollectionCenter findByContactNumber(int contactNumber);
	
	CollectionCenter findByCenterId(int centerId);

}
;