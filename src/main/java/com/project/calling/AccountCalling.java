package com.project.calling;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.Account;
import com.project.model.Address;
import com.project.model.ObjectMessage;
import com.project.model.Product;
import com.project.model.RecoverInfo;
import com.project.utils.BaseUrl;

public class AccountCalling {
	private static AccountCalling instance = null;
	private RestTemplate restTemplate;

	public static AccountCalling getInstance() {
		if (instance == null) {
			instance = new AccountCalling();
		}
		return instance;
	}
	
	/**
	 * @author: Vinh
	 */
	
	public Account getUserAddress(int idUser) {
		Account userAddress = new Account();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GET_ADDRESS.replace("queryParam", String.valueOf(idUser));
			ResponseEntity<Account> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Account>() {
					});
			userAddress = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR getUserAddress");
			System.out.println(e.getMessage());
		}
		return userAddress;
	}

	public List<Address> userGetAllAddress(int idAccount) {
		List<Address> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GET_ALL_ADDRESS.replace("queryParam", String.valueOf(idAccount));
			ResponseEntity<List<Address>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Address>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userGetAllAddress");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public Address userGetDetailsAddress(int idAddress) {
		Address modelGet = new Address();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GET_DETAIS_ADDRESS.replace("queryParam", String.valueOf(idAddress));
			ResponseEntity<Address> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Address>() {
					});
			modelGet = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userGetDetailsAddress");
			System.out.println(e.getMessage());
		}
		return modelGet;
	}

	public boolean userUpdateAddress(Address modelUpdate) {
		boolean flagUpdate = true;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.US_UPDATE_ADDRESS);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, boolean.class);
			flagUpdate = result.getBody();
		} catch (Exception e) {
			flagUpdate = false;
			//
			System.out.println("ERROR userUpdateAddress");
			System.out.println(e.getMessage());
		}
		return flagUpdate;
	}

	public boolean userInsertAddress(Address modelInsert) {
		boolean flagInsert = true;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.US_INSERT_ADDRESS);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelInsert, boolean.class);
			flagInsert = result.getBody();
		} catch (Exception e) {
			flagInsert = false;
			//
			System.out.println("ERROR userInsertAddress");
			System.out.println(e.getMessage());
		}	
		return flagInsert;
	}
	
	public boolean userDeleteAddress(int idAddress) {
		boolean flagDelete = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_DELETE_ADDRESS.replace("queryParam", String.valueOf(idAddress));
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			flagDelete = responseEntity.getBody();
		} catch (Exception e) {
			flagDelete = false;
			//
			System.out.println("ERROR userInsertAddress");
			System.out.println(e.getMessage());
		}
		return flagDelete;
	}
	
	public Account userGetProfiles(int idAccount) {
		Account ac = new Account();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETPROFILE.replace("queryParam", String.valueOf(idAccount));
			ResponseEntity<Account> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Account>() {
					});
			//
			ac = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userGetProfiles");
			System.out.println(e.getMessage());
		}
		return ac;
	}
	
	public boolean userUpdateProfile(Account modelUpdate) {
		boolean flagUpdate = true;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.US_UPDATEPROFILE);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, boolean.class);
			flagUpdate = result.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userUpdateProfile");
			System.out.println(e.getMessage());
		}
		return flagUpdate;
	}
	
	public boolean usersVerifyOldPass(int idUsers,String currentPassword) {
		boolean flagVerify = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_VERIFYPASS.replace("queryUser", String.valueOf(idUsers)).replace("queryPass", currentPassword);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			//
			flagVerify = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR usersVerifyOldPass");
			System.out.println(e.getMessage());
		}
		return flagVerify;
	}
	
	public boolean usersChangePassword(int idUsers ,String oldPassword ,String newPassword) {
		boolean flagChangePass = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_CHANGEPASS.replace("queryUser", String.valueOf(idUsers)).replace("queryOldPass", oldPassword).replace("queryNewPass", newPassword);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			//
			flagChangePass = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR usersChangePassword");
			System.out.println(e.getMessage());
		}
		return flagChangePass;
	}
	
	public ObjectMessage userCheckEmailForChange(int idUsers, String newEmail) {
		ObjectMessage ojM = new ObjectMessage();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_CHKEMAIL_CHG.replace("queryIdUser", String.valueOf(idUsers)).replace("queryEmail", newEmail);
			ResponseEntity<ObjectMessage> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<ObjectMessage>() {
					});
			//
			ojM = responseEntity.getBody();
		} catch (Exception e) {
			ojM.setFlagMessage(false);
			//
			System.out.println("ERROR userCheckEmailForChange");
			System.out.println(e.getMessage());
		}
		return ojM;
	}
	
	public RecoverInfo userRequestSendNewMail(int idUser,int userType,String newEmail) {
		RecoverInfo rEI = new RecoverInfo();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_SENDRCH_MAIL.replace("queryId", String.valueOf(idUser)).replace("queryUser", String.valueOf(userType)).replace("queryEmail", newEmail);
			ResponseEntity<RecoverInfo> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<RecoverInfo>() {
					});
			//
			rEI = responseEntity.getBody();
		} catch (Exception e) {
			rEI.setFlagSuccess(false);
			//
			System.out.println("ERROR userRequestSendNewMail");
			System.out.println(e.getMessage());
		}
		return rEI;
	}
	
	public boolean userVerifyChangeEmail(String codeVerify) {
		boolean flagVerify = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_VERIFY_CHMAIL.replace("queryCode", codeVerify);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			//
			flagVerify = responseEntity.getBody();
		} catch (Exception e) {
			flagVerify = false;
			//
			System.out.println("ERROR userVerifyChangeEmail");
			System.out.println(e.getMessage());
		}
		return flagVerify;
	}
	
	public boolean userCheckExistsUserName(String userName) {
		boolean flagExists = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_CHK_USNAME.replace("queryCheck", userName);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			//
			flagExists = responseEntity.getBody();
		} catch (Exception e) {
			flagExists = true;
			//
			System.out.println("ERROR userCheckExistsUserName");
			System.out.println(e.getMessage());
		}
		return flagExists;
	}
	
	public boolean userCheckExistsUserEmail(String userEmail) {
		boolean flagExists = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_CHK_UEMAIL.replace("queryEmail", userEmail);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			//
			flagExists = responseEntity.getBody();
		} catch (Exception e) {
			flagExists = true;
			//
			System.out.println("ERROR userCheckExistsUserEmail");
			System.out.println(e.getMessage());
		}
		return flagExists;
	}
	
	public RecoverInfo userRegisterAccount(Account modelRegister) {
		RecoverInfo rEI = new RecoverInfo();
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.US_REGISTER);
			ResponseEntity<RecoverInfo> result = restTemplate.postForEntity(uri, modelRegister, RecoverInfo.class);
			rEI = result.getBody();
		} catch (Exception e) {
			rEI.setFlagSuccess(false);
			//
			System.out.println("ERROR userRegisterAccount");
			System.out.println(e.getMessage());
		}
		return rEI;
	}
	
	public boolean userVerifyRegisterAccount(String codeLink) {
		boolean flagVerify = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_VERIFY_REGISTER.replace("queryCode", codeLink);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			//
			flagVerify = responseEntity.getBody();
		} catch (Exception e) {
			flagVerify = false;
			//
			System.out.println("ERROR userVerifyRegisterAccount");
			System.out.println(e.getMessage());
		}
		return flagVerify;
	}
	
	public Account userLoginAccount(String userName, String userPassword) {
		Account acLogin = new Account();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_LOGIN_ACCO.replace("queryUName", userName).replace("queryUPass", userPassword);
			ResponseEntity<Account> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Account>() {
					});
			//
			acLogin = responseEntity.getBody();
		} catch (Exception e) {
			acLogin.setFlagLogin(false);
			//
			System.out.println("ERROR userLoginAccount");
			System.out.println(e.getMessage());
			
		}
		return acLogin;
	}
	
	public boolean usersCheckStatusAccount(int idUsers) {
		boolean isStatus = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_CHECK_STATUS.replace("queryParam", String.valueOf(idUsers));
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			//
			isStatus = responseEntity.getBody();
		} catch (Exception e) {
			//
			System.out.println("ERROR usersCheckStatusAccount");
			System.out.println(e.getMessage());
			
		}
		return isStatus; 
	}
	
	
	/**
	 * @author: MaiTran
	 */
	
	public List<Account> CallAllAccount() {
		List<Account> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Account>> res = restTemplate.exchange(BaseUrl.AD_ALLACCOUNT, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Account>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("loi CallAllAccount");
		}
		return null;
	}

	public Boolean CallUpdateAccount(Account modelUpdate) {
		Boolean flag = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_UPDATEACCOUNT);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println("ERROR CallUpdateAccount");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	
	
	
	
	

}
