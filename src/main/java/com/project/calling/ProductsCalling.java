package com.project.calling;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.Category;
import com.project.model.Product;
import com.project.modelview.ProductView;
import com.project.modelview.ProductView_A;
import com.project.utils.BaseUrl;

public class ProductsCalling {
	private static ProductsCalling instance = null;
	private RestTemplate restTemplate;

	public static ProductsCalling getInstance() {
		if (instance == null) {
			instance = new ProductsCalling();
		}
		return instance;
	}

	/**
	 * @author Vinh
	 */
	public List<ProductView> userGetAllProduct() {
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(BaseUrl.US_GETPRODUCTS,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR userGetAllProduct");
		}
		return ls;
	}

	public List<ProductView> getProductforUserbyCate(int idCate) {
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETPRODUCTS_BYCATE.replace("cateReplace", String.valueOf(idCate));
			//
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR getProductforUserbyCate");
		}
		return ls;
	}

	public List<ProductView> getProductforUserbyPrice(BigDecimal minPrice, BigDecimal maxPrice) {
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETPORUDCTS_BYPRICE.replace("paramPriceMin", String.valueOf(minPrice))
					.replace("paramPriceMax", String.valueOf(maxPrice));
			//
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR getProductforUserbyPrice");
		}
		return ls;
	}

	public List<ProductView> getProductforUserbyName(String textSearch) {
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETPRODUCTS_SEARCH.replace("queryParam", textSearch);
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR getProductforUserbyName");
		}
		return ls;
	}

	public ProductView getProductforUserByID(int idProduct) {
		ProductView pReturn = new ProductView();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETPRODUCTS_BYID.replace("queryParam", String.valueOf(idProduct));
			ResponseEntity<ProductView> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<ProductView>() {
					});
			//
			pReturn = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR getProductforUserByID");
		}
		return pReturn;
	}

	public List<ProductView> getProductRandomFilterbyID(int currentID, int amountTake) {
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETPRODUCTS_RANDOM.replace("paramID", String.valueOf(currentID))
					.replace("paramAmount", String.valueOf(amountTake));
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR getProductRandomFilterbyID");
		}
		return ls;
	}

	public List<ProductView> userGetAllProductbySupplier(int idSupplier) {
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETPRODUCTS_SUPPLIER.replace("queryParam", String.valueOf(idSupplier));
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR userGetAllProductbySupplier");
		}
		return ls;
	}
	
	public List<ProductView> getProductforUserbyCateSupplier(int idCate,int idSupplier){
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETPRODUCTS_BYCATE_SUPPLIER.replace("cateReplace", String.valueOf(idCate)).replace("queryParam",String.valueOf(idSupplier));
			//
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR getProductforUserbyCateSupplier");
		}
		return ls;
	}
	
	public List<ProductView> getProductforUserbyPriceSupplier(BigDecimal minPrice, BigDecimal maxPrice,int idSupplier) {
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETPORUDCTS_BYPRICE_SUPPLIER.replace("paramPriceMin", String.valueOf(minPrice))
					.replace("paramPriceMax", String.valueOf(maxPrice)).replace("paramSupplier", String.valueOf(idSupplier));
			//
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR getProductforUserbyPriceSupplier");
		}
		return ls;
	}
	
	public List<ProductView> getProductforUserbyNameSupplier(String textSearch,int idSupplier) {
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.US_GETPRODUCTS_SEARCH_SUPPLIER.replace("queryParam", textSearch).replace("paramSupplier", String.valueOf(idSupplier));
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(currentURL, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR getProductforUserbyNameSupplier");
		}
		return ls;
	}
	
	/**
	 * @author MaiTran
	 */
	public List<ProductView_A> CallAllProduct() {
		List<ProductView_A> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<ProductView_A>> res = restTemplate.exchange(
					BaseUrl.AD_ALLPRODUCT,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<ProductView_A>>() {}
					);
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR Ad_CallProduct");
			System.out.println(e.getMessage());
		}
		return null;
	}
	public List<ProductView_A> CallSoldOfProduct(int idSupplier) {
		List<ProductView_A> lstCust = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<ProductView_A>> res = restTemplate.exchange(
					BaseUrl.AD_SOLDPRODUCT.concat("?idSupplier="+idSupplier),
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<ProductView_A>>() {}
					);
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallSoldOfProduct");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Boolean CallUpdateProd(ProductView_A modelUpdate) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_UPDATEPRODUCT);
			//ResponseEntity<Supplier> result = restTemplate.postForEntity(uri, modelUpdate, Supplier.class);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println("ERROR CallUpdateProd");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public List<ProductView_A> CallProductByActive(int isActive) {
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_FILTERACTIVEPROD.concat("?isActive="+isActive);
			ResponseEntity<List<ProductView_A>> res = restTemplate.exchange(currentURL,HttpMethod.GET,null,
					new ParameterizedTypeReference<List<ProductView_A>>() {}
					);
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallProductByActive");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public List<ProductView_A> CallProductByIdSupp(int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_FILTERPRODSUPP.concat("?idSupplier="+idSupplier);
			ResponseEntity<List<ProductView_A>> res = restTemplate.exchange(currentURL,HttpMethod.GET,null,
					new ParameterizedTypeReference<List<ProductView_A>>() {}
					);
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallProductByIdSupp");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public List<ProductView_A> CallProdByActiveAndSupp(int isActive,int idSupplier) {
		try {
			restTemplate = new RestTemplate();
			String currentURL = BaseUrl.AD_FTACTIVEPRODSUPP.concat("?isActive="+isActive+"&idSupplier="+idSupplier);
			ResponseEntity<List<ProductView_A>> res = restTemplate.exchange(currentURL,HttpMethod.GET,null,
					new ParameterizedTypeReference<List<ProductView_A>>() {}
					);
			return res.getBody();

		} catch (Exception e) {
			System.out.println("ERROR CallProdByActiveAndSupp");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	/**
	 * @author Thuan
	 **/
	public List<ProductView> CallGetProductSupplier(int idSupplier) {
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_CallGetProductSupplier + "?idSupplier=" + idSupplier,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallGetProductSupplier");
		}
		return ls;
	}
	
	public List<ProductView> CallFilterActive(int idSupplier, int isActive) {
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_CallFilterActive + "?idSupplier=" + idSupplier + "&isActive=" + isActive,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallFilterActive");
		}
		return ls;
	}
	
	public List<ProductView> CallGetAllProductNotHaveDiscount(int idSupplier) {
		List<ProductView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<ProductView>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_CallGetAllProductNotHaveDiscount + "?idSupplier=" + idSupplier,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<ProductView>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallGetAllProductNotHaveDiscount");
		}
		return ls;
	}
	
	public Boolean CallInsertProduct(ProductView d) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			restTemplate.postForEntity(BaseUrl.Sup_InsertProduct, d, Boolean.class);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallInsertProduct");
		}
		return flag;
	}
	
	public Boolean CallUpdateProduct(Product d) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			restTemplate.postForEntity(BaseUrl.Sup_UpdateProduct, d, Boolean.class);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallUpdateProduct");
		}
		return flag;
	}
	
	public List<Category> CallGetCategory() {
		List<Category> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Category>> responseEntity = restTemplate.exchange(
					BaseUrl.Sup_Category,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<Category>>() {
					});
			ls = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallGetCategory");
		}
		return ls;
	}
	
	public Boolean CallDeleteProduct(int idProduct) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			restTemplate.exchange(
					BaseUrl.Sup_DeleteProduct + "?idProduct=" + idProduct,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<>() {
			});
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallDeleteProduct");
		}
		return flag;
	}
	
	public Boolean CallActiveProduct(int idProduct) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			restTemplate.exchange(
					BaseUrl.Sup_ActiveProduct + "?idProduct=" + idProduct,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<>() {
			});
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallActiveProduct");
		}
		return flag;
	}
	
	public Boolean CallSearchNameProduct(String nameSearch, int idSupplier) {
		Boolean flag = false;
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<ProductView>> ls = restTemplate.exchange(
					BaseUrl.Sup_SearchNameProduct + "?nameSearch=" + nameSearch + "&idSupplier=" + idSupplier,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<ProductView>>() {
			});
			if(ls.getBody().size() == 0) {
				flag = true;						
			};
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallSearchNameProduct");
		}
		return flag;
	}
}
