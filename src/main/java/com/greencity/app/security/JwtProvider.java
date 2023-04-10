package com.greencity.app.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.greencity.app.exception.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {

	private KeyStore keyStore;

	@PostConstruct
	public void init() {

		try {
			keyStore = KeyStore.getInstance("JKS");
			InputStream resourceAsStream = getClass().getResourceAsStream("/greenCity.jks");
			keyStore.load(resourceAsStream, "keystoretestkey".toCharArray());

		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {

			throw new CustomException("Exception occured while loading keystore");
		}

	}

	public String generateToken(Authentication authentication) {

		User princlipal = (User) authentication.getPrincipal();
		Date validity = new Date(new Date().getTime() + 300000);

		return Jwts.builder().setSubject(princlipal.getUsername()).claim("authorities", princlipal.getAuthorities())
				.setExpiration(validity).signWith(getPrivateKey()).compact();
	}

	private PrivateKey getPrivateKey() {

		try {
			return (PrivateKey) keyStore.getKey("greenCity", "keystoretestkey".toCharArray());

		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {

			throw new CustomException("Exception occured while retrieving private key from keystore");
		}
	}

	public boolean validateToken(String jwt) {

		Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);

		return true;
	}

	private PublicKey getPublicKey() {

		try {
			return keyStore.getCertificate("greenCity").getPublicKey();

		} catch (KeyStoreException e) {

			throw new CustomException("Exception occured while retrieving public key from keystore");
		}
	}

	public String getUsernameFromJwt(String token) {

		Claims claims = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

}
