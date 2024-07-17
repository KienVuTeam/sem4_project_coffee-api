package com.project.calling;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.RecoverInfo;
import com.project.utils.BaseUrl;

public class RecoverCalling {
	private static RecoverCalling instance = null;
	private RestTemplate restTemplate;

	public static RecoverCalling getInstance() {
		if (instance == null) {
			instance = new RecoverCalling();
		}
		return instance;
	}
	
	/**
	 * @author: Vinh
	 */
	
	public RecoverInfo userRecoverPassword(String emailSendding,int userType) {
		RecoverInfo userAddress = new RecoverInfo();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_RECOVERPASS.replace("queryEmail", emailSendding).replace("queryTypeU", String.valueOf(userType));
			ResponseEntity<RecoverInfo> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<RecoverInfo>() {
					});
			userAddress = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userRecoverPassword");
			System.out.println(e.getMessage());
		}
		return userAddress;
	}
	
	public boolean userCheckRecoverkeyPassword(String keyRecover) {
		boolean flagCheck = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_CHKKEYREPASS.replace("queryKey", keyRecover);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			flagCheck = responseEntity.getBody();
		} catch (Exception e) {
			flagCheck = false;
			//
			System.out.println("ERROR userCheckRecoverkeyPassword");
			System.out.println(e.getMessage());
		}
		return flagCheck;
	}
	
	public boolean userChangePasswordByRecover(String newPassword,String keyRecover) {
		boolean flagRecover = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_CHGPASS_BYREC.replace("queryKeyRecover", keyRecover).replace("queryNewPass", newPassword);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			flagRecover = responseEntity.getBody();
		} catch (Exception e) {
			flagRecover = false;
			//
			System.out.println("ERROR userChangePasswordByRecover");
			System.out.println(e.getMessage());
		}
		return flagRecover;
	}
	
}
