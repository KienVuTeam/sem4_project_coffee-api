package com.project.calling;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.ObjectMessage;
import com.project.modelview.WatchListU;
import com.project.utils.BaseUrl;

public class WatchListCalling {
	private static WatchListCalling instance = null;
	private RestTemplate restTemplate;
	
	public static WatchListCalling getInstance() {
		if (instance == null) {
			instance = new WatchListCalling();
		}
		return instance;
	}
	
	/**
	 * @author Vinh
	 */
	public List<WatchListU> usersGetWatchList(
			int idUser,
			String lsWatchW,
			String searchProductN,
			int sortDFlag,
			int sortPFlag
			)
		{
		List<WatchListU> ls = new ArrayList<>();
		try {
			//
			if (searchProductN == null) {
				searchProductN = "";
			}
			//
			restTemplate = new RestTemplate();
			String urlC = BaseUrl.US_GETWATCHLS.replace("queryIdU",  String.valueOf(idUser))
											   .replace("queryLs", lsWatchW)
											   .replace("querySearchN",searchProductN)
											   .replace("querySortD",String.valueOf(sortDFlag))
											   .replace("querySortP", String.valueOf(sortPFlag));
			
			ResponseEntity<List<WatchListU>> res = restTemplate.exchange(urlC,HttpMethod.GET,null,
					new ParameterizedTypeReference<List<WatchListU>>() {}
					);
			ls = res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR usersGetWatchList");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	public boolean userLoginAddWList(String lsProducts,int idUser) {
		boolean flagLoginA = true;
		try {
			if (lsProducts.isBlank()) {
				return false;
			}
			//
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> res = restTemplate.exchange(BaseUrl.US_LOGADDW.replace("queryLsProducts",lsProducts).replace("queryIdUser", String.valueOf(idUser)),HttpMethod.GET,null,
					new ParameterizedTypeReference<Boolean>() {}
					);
			//
			flagLoginA = res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userLoginAddWList");
			System.out.println(e.getMessage());
		}
		return flagLoginA;
	}
	
	public ObjectMessage usersAddWatchList(int idProduct,int idUser) {
		ObjectMessage objMsg = new ObjectMessage();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<ObjectMessage> res = restTemplate.exchange(BaseUrl.US_ADDWATCHLIST.replace("queryProduct",String.valueOf(idProduct))
																					         .replace("queryIdUser", String.valueOf(idUser)),HttpMethod.GET,null,
					new ParameterizedTypeReference<ObjectMessage>() {}
					);
			//
			objMsg = res.getBody();
		} catch (Exception e) {
			objMsg.setFlagMessage(false);
			objMsg.setMessage("There Are Some Errors Please Try Back");
			//
			System.out.println("ERROR usersAddWatchList");
			System.out.println(e.getMessage());
		}
		return objMsg;
	}
	
	public boolean usersDeleteWatchList(int idProduct,int idUser) {
		boolean flagDelete = true;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> res = restTemplate.exchange(BaseUrl.US_DELWALIST.replace("queryProduct",String.valueOf(idProduct))
																					.replace("queryIdUser", String.valueOf(idUser)),HttpMethod.GET,null,
					new ParameterizedTypeReference<Boolean>() {}
					);
			//
			flagDelete = res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR usersDeleteWatchList");
			System.out.println(e.getMessage());
		}
		return flagDelete;
	}
}
