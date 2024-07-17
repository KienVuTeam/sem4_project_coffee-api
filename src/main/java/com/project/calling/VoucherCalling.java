package com.project.calling;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.Voucher;
import com.project.modelview.VoucherView;
import com.project.utils.BaseUrl;

public class VoucherCalling {
	private static VoucherCalling instance = null;
	private RestTemplate restTemplate;

	public static VoucherCalling getInstance() {
		if (instance == null) {
			instance = new VoucherCalling();
		}
		return instance;
	}

	/**
	 * @author Vinh
	 */

	public List<Voucher> userGetAvalibleVoucher(String voucherType) {
		List<Voucher> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_VOUCHER.replace("queryParam", voucherType);
			ResponseEntity<List<Voucher>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Voucher>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR userGetAvalibleVoucher");
		}
		return ls;
	}

	public Voucher userGetVoucherDetails(String idVoucher) {
		Voucher voucherGet = new Voucher();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_VOUCHER_INFO.replace("queryParam", idVoucher);
			ResponseEntity<Voucher> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<Voucher>() {
					});
			voucherGet = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR userGetVoucherDetails");
		}
		return voucherGet;
	}

	public List<VoucherView> userGetVoucherSupplierbyID(int idSupplier) {
		List<VoucherView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GET_VOUCHER_SUPPLIER.replace("queryParam", String.valueOf(idSupplier));
			ResponseEntity<List<VoucherView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<VoucherView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userGetVoucherSupplierbyID");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	/**
	 * @author MaiTran
	 */

	public BigDecimal CallGetPriceVoucherInvoiceSupplier(int idSupplier, String lsVoucherS) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<BigDecimal> res = restTemplate.exchange(
					BaseUrl.AD_PRICEVOUCHER_ORDEROFSUPP
							.concat("?idSupplier=" + idSupplier + "&lsVoucherS=" + lsVoucherS),
					HttpMethod.GET, null, new ParameterizedTypeReference<BigDecimal>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallGetPriceVoucherInvoiceSupplier");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Voucher> CallGetUseVoucherILA(String id) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Voucher>> res = restTemplate.exchange(BaseUrl.AD_GETUSEVOUCHERILA.concat("?id=" + id),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Voucher>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallGetUseVoucherILA");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Voucher> CallGetPriceVoucherSupplier(String id, String usercreate) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Voucher>> res = restTemplate.exchange(BaseUrl.AD_GETPRICEVOUCHERSUPPLIER,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Voucher>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallGetPriceVoucherSupplier");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Voucher> CallAllVoucher() {
		List<Voucher> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Voucher>> res = restTemplate.exchange(BaseUrl.AD_ALLVOUCHER, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Voucher>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallAllVoucher");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Boolean CallUpdateActiveVoucher(Voucher modelUpdate) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_UPDATEVOUCHER);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println("ERROR CallUpdateActiveVoucher");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Boolean CallDeleteVoucher(Voucher modelDelete) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_DELETEVOUCHER);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelDelete, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println("ERROR CallDeleteVoucher");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Boolean CallInsertVou(Voucher addCate) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_INSERTVOUCHER);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, addCate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallInsertVou");
			return false;
		}
	}

	public List<Voucher> CallFilter1() {
		List<Voucher> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Voucher>> res = restTemplate.exchange(BaseUrl.AD_FILTER1, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Voucher>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallFilter1");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Voucher> CallFilter2() {
		List<Voucher> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Voucher>> res = restTemplate.exchange(BaseUrl.AD_FILTER2, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Voucher>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallFilter2");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Voucher> CallFilter3() {
		List<Voucher> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Voucher>> res = restTemplate.exchange(BaseUrl.AD_FILTER3, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Voucher>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallFilter3");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Boolean CallUpdateVoucher(Voucher modelUpdate) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_UPDATEVOUCHER);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println("ERROR CallUpdateVoucher");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Voucher CallCheckVoucher(String idVoucher) {
		Voucher lstCust = new Voucher();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Voucher> res = restTemplate.exchange(BaseUrl.AD_CHECKVOUCHER.concat("?id=" + idVoucher),
					HttpMethod.GET, null, new ParameterizedTypeReference<Voucher>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallCheckVoucher");
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * @author Thuan
	 **/
	public List<Voucher> CallGetAllVoucher(String usercreate) {
		List<Voucher> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Voucher>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetAllVoucher + "?userCreate=" + usercreate, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Voucher>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallGetAllVoucher");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public List<Voucher> CallFilterVoucher1(String usercreate) {
		List<Voucher> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Voucher>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetFilterVoucher1 + "?userCreate=" + usercreate, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Voucher>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallFilterVoucher1");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public List<Voucher> CallFilterVoucher2(String usercreate) {
		List<Voucher> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Voucher>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetFilterVoucher2 + "?userCreate=" + usercreate, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Voucher>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallFilterVoucher2");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public List<Voucher> CallFilterVoucher3(String usercreate) {
		List<Voucher> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Voucher>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetFilterVoucher3 + "?userCreate=" + usercreate, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Voucher>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallFilterVoucher3");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public Boolean CallInsertVoucher(Voucher v) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			restTemplate.postForEntity(BaseUrl.Sup_InsertVoucher, v, Boolean.class);
			flag = true;
		} catch (Exception e) {
			System.out.println("ERROR CallInsertVoucher");
			System.out.println(e.getMessage());
		}
		return flag;
	}

	public Boolean Sup_CallUpdateVoucher(Voucher v) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			restTemplate.postForEntity(BaseUrl.Sup_UpdateVoucher, v, Boolean.class);
			flag = true;
		} catch (Exception e) {
			System.out.println("ERROR CallUpdateVoucher");
			System.out.println(e.getMessage());
		}
		return flag;
	}

	public Boolean CallDeleteVoucher(String id) {
		boolean flag = false;
		Voucher v = new Voucher();
		v.setId(id);
		try {
			restTemplate = new RestTemplate();
			restTemplate.postForObject(BaseUrl.Sup_DeleteVoucher, v, Voucher.class);
			flag = true;
		} catch (Exception e) {
			System.out.println("ERROR CallDeleteVoucher");
			System.out.println(e.getMessage());
		}
		return flag;
	}

	public Boolean CallCheckDuplicateID(String id) {
		boolean rs = false;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_CheckDuplicateIDVoucher + "?id=" + id, HttpMethod.GET,
					null, new ParameterizedTypeReference<Boolean>() {
					});
			rs = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallCheckDuplicateID");
			System.out.println(e.getMessage());
		}
		return rs;
	}

	public Boolean CallEndVoucher(String id) {
		boolean rs = false;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_EndVoucher + "?id=" + id, HttpMethod.GET,
					null, new ParameterizedTypeReference<Boolean>() {
					});
			rs = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallEndVoucher");
			System.out.println(e.getMessage());
		}
		return rs;
	}

}
