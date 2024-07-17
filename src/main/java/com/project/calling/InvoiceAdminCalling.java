package com.project.calling;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.SupplierRefundV;
import com.project.model.InvoiceAdmin;
import com.project.model.InvoiceDetails;
import com.project.model.InvoiceSupplier;
import com.project.modelview.InvoiceView;
import com.project.modelview._dllTestInv;
import com.project.utils.BaseUrl;

public class InvoiceAdminCalling {
	private static InvoiceAdminCalling instance = null;
	private RestTemplate restTemplate;

	public static InvoiceAdminCalling getInstance() {
		if (instance == null) {
			instance = new InvoiceAdminCalling();
		}
		return instance;
	}

	/**
	 * @author MaiTran
	 */
	public List<InvoiceAdmin> CallActionOrder(int id) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceAdmin>> res = restTemplate.exchange(BaseUrl.AD_ACTIONORDER.concat("?id=" + id),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceAdmin>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallActionOrder");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceView> CallInforCusOrderOfSupp(int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> res = restTemplate.exchange(
					BaseUrl.AD_INFORCUS_ORDEROFSUPP.concat("?idSupplier=" + idSupplier), HttpMethod.GET, null,
					new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallInforCusOrderOfSupp");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceView> CallGetTotalOrderAmountOfSupp(int idSupplier, int idInvoice) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> res = restTemplate.exchange(
					BaseUrl.AD_TOTALAMOUNT_ORDEROFSUPP.concat("?idSupplier=" + idSupplier + "&idInvoice=" + idInvoice),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallGetTotalOrderAmountOfSupp");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceView> CallGetRefundtoCustomers(int idSupplier, int idInvoice) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> res = restTemplate.exchange(
					BaseUrl.AD_REFUNDCUS_ORDEROFSUPP.concat("?idSupplier=" + idSupplier + "&idInvoice=" + idInvoice),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallGetRefundtoCustomers");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceView> CallGetMoneyReceived(int status, int idInvoice, int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> res = restTemplate.exchange(
					BaseUrl.AD_GETMONEYRECEIVED_ORDEROFSUPP
							.concat("?status=" + status + "&idInvoice=" + idInvoice + "&idSupplier=" + idSupplier),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallGetMoneyReceived");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceSupplier> CallGetStatus(int idSupplier, int idInvoice) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceSupplier>> res = restTemplate.exchange(
					BaseUrl.AD_STATUSORDER_ORDEROFSUPP.concat("?id=" + idInvoice + "&idSupplier=" + idSupplier),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceSupplier>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallGetStatus");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<InvoiceView> Call_detailOrdersSupp(int id, int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<InvoiceView>> res = restTemplate.exchange(
					BaseUrl.AD_DETAILORDER_ORDEROFSUPP.concat("?id=" + id + "&idSupplier=" + idSupplier),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<InvoiceView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR Call_detailOrdersSupp");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int SelectQuantityInvoice_Ad(int id) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Integer> res = restTemplate.exchange(BaseUrl.AD_COUNTPROD_OFINV.concat("?id=" + id),
					HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR SelectQuantityInvoice_Ad");
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public Boolean AdminPaymentForSupp(InvoiceSupplier modelUpdate) {
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_PAYMENTFORSUPP);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR AdminPaymentForSupp");
			return false;
		}
	}

	public List<_dllTestInv> CallGetInvDetails(int idInvoice) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<_dllTestInv>> res = restTemplate.exchange(
					BaseUrl.AD_GETINVOICEDETAILS.concat("?idInvoice=" + idInvoice), HttpMethod.GET, null,
					new ParameterizedTypeReference<List<_dllTestInv>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallGetInvDetails");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Boolean AdminRefundToCus(InvoiceDetails modelUpdate) {
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_REFUNDTOCUS);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR AdminPaymentForSupp");
			return false;
		}
	}

	// RefundWhenInv9
	public Boolean AdminRefundToSupp(int idSupplier, int idInvoice) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> res = restTemplate.exchange(
					BaseUrl.AD_REFUND_TOSUPP.concat("?idSupplier=" + idSupplier + "&idInvoice=" + idInvoice),
					HttpMethod.GET, null, new ParameterizedTypeReference<Boolean>() {
					});
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR AdminRefundToSupp");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Boolean AdminRefundToAllSupp(int idInvoice) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> res = restTemplate.exchange(
					BaseUrl.AD_REFUND_ALLSUPP.concat("?idInvoice=" + idInvoice), HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR AdminRefundToAllSupp");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Boolean AdminRefundFeeToCus(int idInvoice) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Boolean> res = restTemplate.exchange(
					BaseUrl.AD_REFUND_FEETOCUS.concat("?idInvoice=" + idInvoice), HttpMethod.GET, null,
					new ParameterizedTypeReference<Boolean>() {
					});
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR AdminRefundFeeToCus");
			System.out.println(e.getMessage());
			return false;
		}
	}

	// check sttRefundVSupp
	public List<SupplierRefundV> AdminGetSupplierRefundByInv(int idInvoice) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<SupplierRefundV>> res = restTemplate.exchange(
					BaseUrl.AD_getSupplierRefundByInv.concat("?idInvoice=" + idInvoice), HttpMethod.GET, null,
					new ParameterizedTypeReference<List<SupplierRefundV>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR AdminGetSupplierRefundByInv");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<SupplierRefundV> AdminGetSupplierRefundByInvAndIdSup(int idInvoice, int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<SupplierRefundV>> res = restTemplate.exchange(
					BaseUrl.AD_getSupplierRefundByInvAndIdSup
							.concat("?idInvoice=" + idInvoice + "&idSupplier=" + idSupplier),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<SupplierRefundV>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR AdminGetSupplierRefundByInvAndIdSup");
			System.out.println(e.getMessage());
			return null;
		}
	}
}
