package com.project.calling;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.Cart;
import com.project.model.ObjectMessage;
import com.project.modelview.CartView;
import com.project.utils.BaseUrl;

public class CartCalling {
	private static CartCalling instance = null;
	private RestTemplate restTemplate;

	public static CartCalling getIntance() {
		if (instance == null) {
			instance = new CartCalling();
		}
		return instance;
	}

	public List<CartView> getUserCartSupplier(int idAccount) {
		List<CartView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GET_CART_SUPPLIER.replace("queryParam", String.valueOf(idAccount));
			ResponseEntity<List<CartView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CartView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR getUserCartSupplier");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public List<CartView> getUserCart(int idAccount) {
		List<CartView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GET_CART.replace("queryParam", String.valueOf(idAccount));
			ResponseEntity<List<CartView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CartView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR getUserCart");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public BigDecimal updateUserCart(int Amount, BigDecimal Price, int idAccount, int idProduct) {
		BigDecimal currentPrice = BigDecimal.ZERO;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_UPDATE_CART.replace("paramAmount", String.valueOf(Amount))
					.replace("paramPrice", String.valueOf(Price)).replace("paramAccount", String.valueOf(idAccount))
					.replace("paramProduct", String.valueOf(idProduct));
			ResponseEntity<BigDecimal> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<BigDecimal>() {
					});
			currentPrice = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR updateUserCart");
			System.out.println(e.getMessage());
		}
		return currentPrice;
	}
	
	public boolean removeCart(int idCart) {
		boolean flagRemove = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_REMOVE_CART.replace("queryParam",String.valueOf(idCart));
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			flagRemove = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR updateUserCart");
			System.out.println(e.getMessage());
		}
		return flagRemove;
	}
	
	public boolean multiRemoveCart(String lsIdCart) {
		boolean flagRemove = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_MULTI_REMOVE_CART.replace("queryParam",lsIdCart);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			flagRemove = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR updateUserCart");
			System.out.println(e.getMessage());
		}
		return flagRemove;
	}
	
	public boolean checkCartCheckOut(String lsCart,int idUser,String idVoucher) {
		boolean flagCheck = true;
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_CHECKLS_CART.replace("queryList",lsCart).replace("queryUser", String.valueOf(idUser))
					.replace("queryVoucher", idVoucher);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			flagCheck = responseEntity.getBody();
		} catch (Exception e) {
			flagCheck = false;
			System.out.println("ERROR checkCartCheckOut");
			System.out.println(e.getMessage());
		}
		return flagCheck;
	}
	
	public List<CartView> userGetLsCartCheckOut(String lsCart){
		List<CartView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GET_LSCART_CKOU.replace("queryParam",lsCart);
			ResponseEntity<List<CartView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CartView>>() {
					});
			//
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userGetLsCartCheckOut");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	public List<CartView> userGetSupplierCheckOut(String lsCart){
		List<CartView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GET_SUPPLIER_CKOU.replace("queryParam",lsCart);
			ResponseEntity<List<CartView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CartView>>() {
					});
			//
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userGetSupplierCheckOut");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	public ObjectMessage userAddProductIntoCart(CartView modelAdd) {
		ObjectMessage msgO = new ObjectMessage();
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.US_ADD_CARTS);										
			ResponseEntity<ObjectMessage> result = restTemplate.postForEntity(uri, modelAdd, ObjectMessage.class);		
			msgO = result.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userAddProductIntoCart");
			System.out.println(e.getMessage());
		}
		return msgO;
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
