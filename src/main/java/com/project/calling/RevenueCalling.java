package com.project.calling;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.InvoiceSupplier;
import com.project.modelview.InvoiceSupplierView;
import com.project.utils.BaseUrl;

public class RevenueCalling {
	private static RevenueCalling instance = null;
	private RestTemplate restTemplate;

	public static RevenueCalling getInstance() {
		if (instance == null) {
			instance = new RevenueCalling();
		}
		return instance;
	}

	/**
	 * 
	 * @author MaiTran
	 */

	public List<InvoiceSupplierView> CallChartByMonth(int month) {
		List<InvoiceSupplierView> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_GETCHARTBYMONTH.concat("?month=" + month);
			ResponseEntity<List<InvoiceSupplierView>> res = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceSupplierView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallChartByMonth");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceSupplierView> CallRevenueByMonth(int month) {
		List<InvoiceSupplierView> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_GETREVENUEBYMONTH.concat("?month=" + month);
			ResponseEntity<List<InvoiceSupplierView>> res = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceSupplierView>>() {
					});
			return res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallRevenueByMonth");
			System.err.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceSupplierView> CallChartByYear(int year) {
		List<InvoiceSupplierView> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_GETCHARTBYYEAR.concat("?year=" + year);
			ResponseEntity<List<InvoiceSupplierView>> res = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceSupplierView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallChartByYear");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceSupplierView> CallRevenueByYear(int year) {
		List<InvoiceSupplierView> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_GETREVENUEBYYEAR.concat("?year=" + year);
			ResponseEntity<List<InvoiceSupplierView>> res = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceSupplierView>>() {
					});
			return res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallRevenueByYear");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceSupplierView> CallChartByMonthYear(int month, int year) {
		List<InvoiceSupplierView> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_GETCHARTBYMONTHYEAR.concat("?month=" + month + "&year=" + year);
			ResponseEntity<List<InvoiceSupplierView>> res = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceSupplierView>>() {
					});
			return res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallChartByMonthYear");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceSupplierView> CallRevenueByMonthYear(int month, int year) {
		List<InvoiceSupplierView> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_GETREVENUEBYMONTHYEAR.concat("?month=" + month + "&year=" + year);
			ResponseEntity<List<InvoiceSupplierView>> res = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceSupplierView>>() {
					});
			return res.getBody();
		} catch (Exception e) {
			System.out.println("ERROR CallRevenueByMonthYear");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int AD_countInvCurrDate() {
		int result = 0;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Integer> responseEntity = restTemplate.exchange(BaseUrl.AD_countInvCurrDate, HttpMethod.GET,
					null, new ParameterizedTypeReference<Integer>() {
					});
			result = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR Sup_CallCountOrder");
			System.out.println(e.getMessage());
		}
		return result;
	};
	public BigDecimal AD_profitsCurrDate() {
		BigDecimal result = BigDecimal.ZERO;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<BigDecimal> responseEntity = restTemplate.exchange(BaseUrl.AD_profitsCurrDate, HttpMethod.GET,
					null, new ParameterizedTypeReference<BigDecimal>() {
					});
			result = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR Sup_CallCountOrder");
			System.out.println(e.getMessage());
		}
		return result;
	};
	

	/**
	 * @author Thuan
	 **/
	public List<InvoiceSupplier> Sup_CallRevenue(int idSupplier, int year, int month) {
		List<InvoiceSupplier> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceSupplier>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetRevenue + "?idSupplier=" + idSupplier + "&year=" + year + "&month=" + month,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceSupplier>>() {
					});
			ls.addAll(responseEntity.getBody());
		} catch (Exception e) {
			ls = new ArrayList<InvoiceSupplier>();
			System.out.println("ERROR Sup_CallRevenue");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public BigDecimal Sup_CallRevenueToday(int idSupplier) {
		BigDecimal result = new BigDecimal(0);
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<BigDecimal> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetRevenueToday + "?idSupplier=" + idSupplier, HttpMethod.GET, null,
					new ParameterizedTypeReference<BigDecimal>() {
					});
			result = responseEntity.getBody();
		} catch (Exception e) {
			result = new BigDecimal(0);
			System.out.println("ERROR Sup_CallRevenueToday");
			System.out.println(e.getMessage());
		}
		return result;
	}

	public BigDecimal Sup_CallRevenuePreMonth(int idSupplier) {
		BigDecimal result = new BigDecimal(0);
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<BigDecimal> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetRevenuePreMonth+ "?idSupplier=" + idSupplier, HttpMethod.GET, null,
					new ParameterizedTypeReference<BigDecimal>() {
					});
			result = responseEntity.getBody();
		} catch (Exception e) {
			result = new BigDecimal(0);
			System.out.println("ERROR Sup_CallRevenuePreMonth");
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public List<InvoiceSupplier> Sup_CallTotalRevenueYear(int idSupplier) {
		List<InvoiceSupplier> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceSupplier>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetTotalRevenueYear + "?idSupplier=" + idSupplier,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceSupplier>>() {
					});
			ls.addAll(responseEntity.getBody());
		} catch (Exception e) {
			ls = new ArrayList<InvoiceSupplier>();
			System.out.println("ERROR Sup_CallTotalRevenueYear");
			System.out.println(e.getMessage());
		}
		return ls;
	}

	public int Sup_CallCountOrder(int idSupplier) {
		int result = 0;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Integer> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_GetCountOrder + "?idSupplier=" + idSupplier, HttpMethod.GET, null,
					new ParameterizedTypeReference<Integer>() {
					});
			result = responseEntity.getBody();
		} catch (Exception e) {
			result = 0;
			System.out.println("ERROR Sup_CallCountOrder");
			System.out.println(e.getMessage());
		}
		return result;
	}
}
