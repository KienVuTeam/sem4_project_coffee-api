package com.project.calling;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.Admin;
import com.project.utils.BaseUrl;

public class AdminCalling {
	private static AdminCalling instance = null;
	private RestTemplate restTemplate;

	
	public static AdminCalling getInstance() {
		if (instance == null) {
			instance = new AdminCalling();
		}
		return instance;
	}
	
	public Admin adminLoginAccount(String userName, String userPassword) {
		Admin acLogin = new Admin();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_LOGIN_ACC.replace("queryUName", userName).replace("queryUPass", userPassword);
			ResponseEntity<Admin> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Admin>() {
					});
			//
			acLogin = responseEntity.getBody();
		} catch (Exception e) {
			acLogin.setFlagLogin(false);
			//
			System.out.println("ERROR adminLoginAccount");
			System.out.println(e.getMessage());
			
		}
		return acLogin;
	}
}
