package com.greencity.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greencity.app.dto.CollectionCenterRegisterRequest;
import com.greencity.app.dto.CommonResponse;
import com.greencity.app.dto.LoginRequest;
import com.greencity.app.dto.LoginResponse;
import com.greencity.app.dto.UserRegisterRequest;
import com.greencity.app.entity.CollectionCenter;
import com.greencity.app.entity.User;
import com.greencity.app.repository.CollectionCenterRepository;
import com.greencity.app.repository.UserRepository;
import com.greencity.app.security.JwtProvider;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CollectionCenterRepository collectionCenterRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private CommonResponse<String> response;

	public CommonResponse<String> userRegister(UserRegisterRequest registerRequest) {

		if (registerRequest != null) {

			User checkUserByUsername = userRepository.findByUsername(registerRequest.getUsername());
			User checkUserByEmail = userRepository.findByEmail(registerRequest.getEmail());
			User checkUserByMobileNumber = userRepository.findByContactNumber(registerRequest.getContactNumber());

			CollectionCenter checkCollectionCenterByUsername = collectionCenterRepository
					.findByUsername(registerRequest.getUsername());
			CollectionCenter checkCollectionCenterByEmail = collectionCenterRepository
					.findByEmail(registerRequest.getEmail());
			CollectionCenter checkCollectionCenterByMobileNumber = collectionCenterRepository
					.findByContactNumber(registerRequest.getContactNumber());

			if ((checkUserByUsername != null && checkUserByEmail != null && checkUserByMobileNumber != null)
					|| (checkCollectionCenterByUsername != null && checkCollectionCenterByEmail != null
							&& checkCollectionCenterByMobileNumber != null)) {

				response.setResponse("Given username, email and mobile number maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else if ((checkUserByUsername != null && checkUserByEmail != null)
					|| (checkCollectionCenterByUsername != null && checkCollectionCenterByEmail != null)) {

				response.setResponse("Given username and email maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else if ((checkUserByUsername != null && checkUserByMobileNumber != null)
					|| (checkCollectionCenterByUsername != null && checkCollectionCenterByMobileNumber != null)) {

				response.setResponse("Given username and mobile number maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else if ((checkUserByEmail != null && checkUserByMobileNumber != null)
					|| (checkCollectionCenterByEmail != null && checkCollectionCenterByMobileNumber != null)) {

				response.setResponse("Given email and mobile number maybe exist, Registration failed!");
				response.setStatus(false);
				return response;
			}

			else if ((checkUserByUsername != null) || (checkCollectionCenterByUsername != null)) {

				response.setResponse("Given username maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else if ((checkUserByEmail != null) || (checkCollectionCenterByEmail != null)) {

				response.setResponse("Given email maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else if ((checkUserByMobileNumber != null) || (checkCollectionCenterByMobileNumber != null)) {

				response.setResponse("Given mobile number maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else {

				User user = new User();

				user.setUsername(registerRequest.getUsername());
				user.setFirstName(registerRequest.getFirstName());
				user.setLastName(registerRequest.getLastName());
				user.setEmail(registerRequest.getEmail());
				user.setContactNumber(registerRequest.getContactNumber());
				user.setAddressLine1(registerRequest.getAddressLine1());
				user.setAddressLine2(registerRequest.getAddressLine2());
				user.setAddressLine3(registerRequest.getAddressLine3());
				user.setAccountStatus(true);
				user.setAdmin(false);

				if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {

					response.setResponse("Password and Confirm Password does not match, Registration failed!");
					response.setStatus(false);

					return response;

				} else {
					user.setPassword(encodePassword(registerRequest.getPassword()));
					userRepository.save(user);

					response.setResponse("Registration Completed!");
					response.setStatus(true);

					return response;
				}
			}
		}

		else {
			response.setResponse("Registration Failed!");
			response.setStatus(false);

			return response;
		}
	}

	public CommonResponse<String> collectionCenterRegister(CollectionCenterRegisterRequest registerRequest) {

		if (registerRequest != null) {

			User checkUserByUsername = userRepository.findByUsername(registerRequest.getUsername());
			User checkUserByEmail = userRepository.findByEmail(registerRequest.getEmail());
			User checkUserByMobileNumber = userRepository.findByContactNumber(registerRequest.getContactNumber());

			CollectionCenter checkCollectionCenterByUsername = collectionCenterRepository
					.findByUsername(registerRequest.getUsername());
			CollectionCenter checkCollectionCenterByEmail = collectionCenterRepository
					.findByEmail(registerRequest.getEmail());
			CollectionCenter checkCollectionCenterByMobileNumber = collectionCenterRepository
					.findByContactNumber(registerRequest.getContactNumber());

			if ((checkUserByUsername != null && checkUserByEmail != null && checkUserByMobileNumber != null)
					|| (checkCollectionCenterByUsername != null && checkCollectionCenterByEmail != null
							&& checkCollectionCenterByMobileNumber != null)) {

				response.setResponse("Given username, email and mobile number maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else if ((checkUserByUsername != null && checkUserByEmail != null)
					|| (checkCollectionCenterByUsername != null && checkCollectionCenterByEmail != null)) {

				response.setResponse("Given username and email maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else if ((checkUserByUsername != null && checkUserByMobileNumber != null)
					|| (checkCollectionCenterByUsername != null && checkCollectionCenterByMobileNumber != null)) {

				response.setResponse("Given username and mobile number maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else if ((checkUserByEmail != null && checkUserByMobileNumber != null)
					|| (checkCollectionCenterByEmail != null && checkCollectionCenterByMobileNumber != null)) {

				response.setResponse("Given email and mobile number maybe exist, Registration failed!");
				response.setStatus(false);
				return response;
			}

			else if ((checkUserByUsername != null) || (checkCollectionCenterByUsername != null)) {

				response.setResponse("Given username maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else if ((checkUserByEmail != null) || (checkCollectionCenterByEmail != null)) {

				response.setResponse("Given email maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else if ((checkUserByMobileNumber != null) || (checkCollectionCenterByMobileNumber != null)) {

				response.setResponse("Given mobile number maybe exist, Registration failed!");
				response.setStatus(false);
				return response;

			} else {

				CollectionCenter collectionCenter = new CollectionCenter();

				collectionCenter.setUsername(registerRequest.getUsername());
				collectionCenter.setCentertName(registerRequest.getCenterName());
				collectionCenter.setContactNumber(registerRequest.getContactNumber());
				collectionCenter.setEmail(registerRequest.getEmail());
				collectionCenter.setAddressLine1(registerRequest.getAddressLine1());
				collectionCenter.setAddressLine2(registerRequest.getAddressLine2());
				collectionCenter.setAddressLine3(registerRequest.getAddressLine3());
				collectionCenter.setLocation(registerRequest.getLocation());
				collectionCenter.setActive(false);
				collectionCenter.setAccountStatus(true);

				if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {

					response.setResponse("Password and Confirm Password does not match, Registration failed!");
					response.setStatus(false);
					return response;

				} else {
					collectionCenter.setPassword(encodePassword(registerRequest.getPassword()));
					collectionCenterRepository.save(collectionCenter);

					response.setResponse("Registration Completed!");
					response.setStatus(true);

					return response;
				}
			}
		}

		else {
			response.setResponse("Registration Failed!");
			response.setStatus(false);

			return response;
		}
	}

	private String encodePassword(String password) {

		return passwordEncoder.encode(password);
	}

	public LoginResponse login(LoginRequest loginRequest) {

		LoginResponse loginResponse = new LoginResponse();

		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		loginResponse.setAuthenticationToken(jwtProvider.generateToken(authenticate));
		loginResponse.setUsername(loginRequest.getUsername());
		loginResponse.setUserRole(this.getUserRole(loginRequest.getUsername()));

		return loginResponse;
	}

	private String getUserRole(String username) {

		User user = userRepository.findByUsername(username);
		CollectionCenter collectionCenter = collectionCenterRepository.findByUsername(username);

		if (user != null) {
			if (user.isAdmin()) {
				return "ADMIN";
			} else {
				return "USER";
			}
		} else if (collectionCenter != null) {
			return "COLLECTION_CENTER";
		} else {
			return null;
		}
	}

}
