package com.project.calling;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.Account;
import com.project.model.ObjectMessage;
import com.project.model.RecoverInfo;
import com.project.model.Supplier;
import com.project.utils.BaseUrl;

public class SupplierCalling {
	private static SupplierCalling instance = null;
	private RestTemplate restTemplate;

	public static SupplierCalling getInstance() {
		if (instance == null) {
			instance = new SupplierCalling();
		}
		return instance;
	}

	/**
	 * 
	 * @author MaiTran
	 */
	public List<Supplier> CallAllSupp() {
		List<Supplier> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Supplier>> res = restTemplate.exchange(BaseUrl.AD_ALLSUPPLIER,HttpMethod.GET,null,
					new ParameterizedTypeReference<List<Supplier>>() {}
					);
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallAllSupp");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Boolean CallUpdateSupplier(Supplier modelUpdate) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_UPDATESUPPLIER);
			//ResponseEntity<Supplier> result = restTemplate.postForEntity(uri, modelUpdate, Supplier.class);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println("ERROR CallUpdateSupplier");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public List<Supplier> CallFilterSupplier(int isActive) {
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_FILTERSUPPLIER.concat("?isActive="+isActive);
			ResponseEntity<List<Supplier>> res = restTemplate.exchange(currentURL,HttpMethod.GET,null,
					new ParameterizedTypeReference<List<Supplier>>() {}
					);
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallFilterSupplier");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * @author Vinh
	 */
	public List<Supplier> userGetSupplierInvDetails(int idInvoice){
		List<Supplier> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_SUPPLIER_INVDS.replace("queryParam", String.valueOf(idInvoice));
			ResponseEntity<List<Supplier>> responseEntity = restTemplate.exchange(currentURL,HttpMethod.GET,null,
					new ParameterizedTypeReference<List<Supplier>>() {}
					);
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userGetSupplierInvDetails");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	public boolean checkExitsTitleOfSupplier(String supplierTitle) {
		boolean flagExists = false;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.SUPP_CKEX_TITLE.replace("queryParam", supplierTitle);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL,HttpMethod.GET,null,
					new ParameterizedTypeReference<Boolean>() {}
					);
			flagExists = responseEntity.getBody();
		} catch (Exception e) {
			flagExists = true;
			//
			System.out.println("ERROR checkExitsTitleofSupplier");
			System.out.println(e.getMessage());
		}
		return flagExists;
	}
	
	public boolean checkExistsUserNameOfSupplier(String userName) {
		boolean flagExists = false;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.SUPP_CHEX_UNAME.replace("queryParam", userName);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL,HttpMethod.GET,null,
					new ParameterizedTypeReference<Boolean>() {}
					);
			flagExists = responseEntity.getBody();
		} catch (Exception e) {
			flagExists = true;
			//
			System.out.println("ERROR checkExistsUserNameOfSupplier");
			System.out.println(e.getMessage());
		}
		return flagExists; 
	}
	
	public boolean checkExistsEmailOfSupplier(String supplierEmail) {
		boolean flagExists = false;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.SUPP_CHEX_EMAIL.replace("queryParam", supplierEmail);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL,HttpMethod.GET,null,
					new ParameterizedTypeReference<Boolean>() {}
					);
			flagExists = responseEntity.getBody();
		} catch (Exception e) {
			flagExists = true;
			//
			System.out.println("ERROR checkExistsEmailOfSupplier");
			System.out.println(e.getMessage());
		}
		return flagExists; 
	}
	
	public Supplier supplierLoginAccount(String userName, String userPassword) {
		Supplier acLogin = new Supplier();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.SUPP_LOGIN.replace("queryUName", userName).replace("queryUPass", userPassword);
			ResponseEntity<Supplier> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Supplier>() {
					});
			//
			acLogin = responseEntity.getBody();
		} catch (Exception e) {
			acLogin.setFlagLogin(false);
			//
			System.out.println("ERROR supplierLoginAccount");
			System.out.println(e.getMessage());
		}
		return acLogin;
	}
	
	public RecoverInfo supplierRegisterAccount(Account modelRegister) {
		RecoverInfo rEI = new RecoverInfo();
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.SUPP_REGISTER);
			ResponseEntity<RecoverInfo> result = restTemplate.postForEntity(uri, modelRegister, RecoverInfo.class);
			rEI = result.getBody();
		} catch (Exception e) {
			rEI.setFlagSuccess(false);
			//
			System.out.println("ERROR supplierRegisterAccount");
			System.out.println(e.getMessage());
		}
		return rEI;
	}
	
	public boolean supplierVerifyRegisterAccount(String codeLink) {
		boolean flagVerify = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.SUPP_VERIFY_REGISTER.replace("queryParam", codeLink);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			//
			flagVerify = responseEntity.getBody();
		} catch (Exception e) {
			flagVerify = false;
			//
			System.out.println("ERROR supplierVerifyRegisterAccount");
			System.out.println(e.getMessage());
		}
		return flagVerify;
	}
	
	/**
	 * @author Thuan
	 */
	public Boolean CallUpdateSupplier2(Supplier modelUpdate) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.Sup_UpdateProfile2);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println("ERROR CallUpdateSupplier2");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public List<Supplier> CallGetProfileSupplier(int idSupplier) {
		List<Supplier> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Supplier>> result = restTemplate.exchange(
					BaseUrl.Sup_GetProfileSupplier + "?id=" + idSupplier,
					HttpMethod.GET,null,
					new ParameterizedTypeReference<List<Supplier>>() {});
			return result.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallGetProfileSupplier");
			System.out.println(e.getMessage());
			return ls;
		}
	}
	
	public String CallChangeAvatarSupplier(String avatar, int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<String> result = restTemplate.exchange(
					BaseUrl.Sup_ChangeAvatarSupplier + "?avatar=" + avatar + "&id=" + idSupplier,
					HttpMethod.GET,null,
					new ParameterizedTypeReference<String>() {});
		} catch (Exception e) {
			System.out.println("ERROR CallChangeAvatarSupplier");
			System.out.println(e.getMessage());
		}
		return avatar;
	}
	
	public boolean CallCheckPassword(int idSupplier, String password) {
		boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> result = restTemplate.exchange(
					BaseUrl.Sup_CheckPassword + "?idSup=" + idSupplier + "&currentPass=" + password,
					HttpMethod.GET,null,
					new ParameterizedTypeReference<Boolean>() {});
			flag = result.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallCheckPassword");
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	public boolean CallChangePasswordSupplier(String newPassword, String oldPassword, int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			restTemplate.exchange(
					BaseUrl.Sup_ChangePasswordSupplier + "?idSup=" + idSupplier + 
					"&newPassword=" + newPassword + "&oldPassword=" + oldPassword,
					HttpMethod.GET,null,
					new ParameterizedTypeReference<Boolean>() {});
			return true;
		} catch (Exception e) {
			System.out.println("ERROR CallChangePasswordSupplier");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public Boolean CallCheckExistsOrganizationName(String title) {
		boolean rs = false;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_CheckExistsOrganizationName + "?title=" + title,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<Boolean>() {
					});
			rs = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallCheckExistsOrganizationName");
			System.out.println(e.getMessage());
		}
		return rs;
	}
	
	public Boolean CallCheckExistsEmail(String email) {
		boolean rs = false;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_CheckExistsEmail + "?email=" + email,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<Boolean>() {
					});
			rs = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallCheckExistsEmail");
			System.out.println(e.getMessage());
		}
		return rs;
	}
	
	public Boolean CallCheckExistsUsername(String username) {
		boolean rs = false;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_CheckExistsUsername + "?username=" + username,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<Boolean>() {
					});
			rs = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallCheckExistsUsername");
			System.out.println(e.getMessage());
		}
		return rs;
	}
	
	public Boolean CallCheckExistsPhone(String phone) {
		boolean rs = false;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_CheckExistsPhone + "?phone=" + phone,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<Boolean>() {
					});
			rs = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallCheckExistsPhone");
			System.out.println(e.getMessage());
		}
		return rs;
	}
	
	public ObjectMessage suppCheckEmailForChange(int idSupplier, String newEmail) {
		ObjectMessage ojM = new ObjectMessage();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.Sup_CheckEmailForChange + "?idSupplier=" + idSupplier + "&newEmail=" + newEmail;
			ResponseEntity<ObjectMessage> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<ObjectMessage>() {
					});
			//
			ojM = responseEntity.getBody();
		} catch (Exception e) {
			ojM.setFlagMessage(false);
			//
			System.out.println("ERROR suppCheckEmailForChange");
			System.out.println(e.getMessage());
		}
		return ojM;
	}
	
	public RecoverInfo suppRequestSendNewMail(int idSupplier,String newEmail,int userType) {
		RecoverInfo rEI = new RecoverInfo();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.Sup_SendMailChange + "?idSupplier=" + idSupplier + "&newEmail=" + newEmail + "&userType=" + userType;
			ResponseEntity<RecoverInfo> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<RecoverInfo>() {
					});
			//
			rEI = responseEntity.getBody();
		} catch (Exception e) {
			rEI.setFlagSuccess(false);
			//
			System.out.println("ERROR suppRequestSendNewMail");
			System.out.println(e.getMessage());
		}
		return rEI;
	}
	
	public boolean suppVerifyChangeEmail(String codeVerify) {
		boolean flagVerify = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.Sup_VerifyChangeEmail + "?codeVerify=" + codeVerify;
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
	
}
