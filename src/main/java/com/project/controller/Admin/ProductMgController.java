package com.project.controller.Admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.modelview.ProductView_A;
import com.project.repository.ilaProjectCoffeeRepository;

@Controller
@RequestMapping("/Admin")
public class ProductMgController {
	@GetMapping("/supplier-s-products")
	public String dashboard(Model lstProd, String isActive, String idSupplier) {

		lstProd.addAttribute("lstProd", allProduct(0));
		lstProd.addAttribute("lstSupp", ilaProjectCoffeeRepository.getInstance().CallAllSupp());
		return "Admin/productMg";

	}

	@GetMapping("/supplier-s-products/detail")
	public String detailProd(Model lstSupp, @RequestParam int idPro) {
		List<ProductView_A> lstProd = allProduct(1);
		ProductView_A item = new ProductView_A();
		for (int i = 0; i < lstProd.size(); i++) {
			if (lstProd.get(i).getId() == idPro) {
				item.setId(lstProd.get(i).getId());
				item.setTitle(lstProd.get(i).getTitle());
				item.setDescription(lstProd.get(i).getDescription());
				item.setNameCate(lstProd.get(i).getNameCate());
				item.setPrice(lstProd.get(i).getPrice());
				item.setImage(lstProd.get(i).getImage());
				item.setImage1(lstProd.get(i).getImage1());
				item.setImage2(lstProd.get(i).getImage2());
				item.setImage3(lstProd.get(i).getImage3());
				item.setIsActive(lstProd.get(i).getIsActive());
				item.setSold(lstProd.get(i).getSold());
			}
		}
		lstSupp.addAttribute("detailProd", item);
		return "Admin/Share/partialProdDetail";
	}

	@PostMapping("/update-product")
	public String updateSupp(@ModelAttribute ProductView_A updateProd, Model lstProd, String isActiveFilter,
			String idSupplierFilter) {
		ilaProjectCoffeeRepository.getInstance().CallUpdateProd(updateProd);
		//
		if (isActiveFilter.equals("allStt") && idSupplierFilter.equals("allSupp")) {
			lstProd.addAttribute("lstProd", allProduct(0));
			return "Admin/Share/partialProductMg";

		} else if (!isActiveFilter.equals("allStt") && idSupplierFilter.equals("allSupp")) {
			lstProd.addAttribute("lstProd", prodFilterActive(Integer.parseInt(isActiveFilter), 0));
			return "Admin/Share/partialProductMg";

		} else if (!idSupplierFilter.equals("allSupp") && isActiveFilter.equals("allStt")) {
			lstProd.addAttribute("lstProd", prodFilterSupp(Integer.parseInt(idSupplierFilter), 0));
			return "Admin/Share/partialProductMg";
		} else {
			lstProd.addAttribute("lstProd",
					prodFilterMul(Integer.parseInt(isActiveFilter), Integer.parseInt(idSupplierFilter), 0));
			return "Admin/Share/partialProductMg";
		}
	}

	@GetMapping("/supplier-s-products/filter")
	public String filterActive(Model lstProd, @RequestParam int isActive) {
		lstProd.addAttribute("lstProd", prodFilterActive(isActive, 0));
		return "Admin/Share/partialProductMg";
	}

	@GetMapping("/supplier-s-products/filterSupp")
	public String filterSupplier(Model lstProd, @RequestParam int idSupplier) {
		lstProd.addAttribute("lstProd", prodFilterSupp(idSupplier, 0));
		return "Admin/Share/partialProductMg";
	}

	@GetMapping("/supplier-s-products/filterMul")
	public String filterMul(Model lstProd, @RequestParam int isActive, int idSupplier) {
		lstProd.addAttribute("lstProd", prodFilterMul(isActive, idSupplier, 0));
		return "Admin/Share/partialProductMg";
	}

	public List<ProductView_A> allProduct(int dt) {
		List<ProductView_A> ls = new ArrayList<>();
		List<ProductView_A> lstProd = ilaProjectCoffeeRepository.getInstance().CallAllProduct();
		for (int i = 0; i < lstProd.size(); i++) {
			ProductView_A prod = new ProductView_A();
			prod.setId(lstProd.get(i).getId());
			prod.setTitle(lstProd.get(i).getTitle());
			if (lstProd.get(i).getDescription().length() > 25) {
				if (dt == 1) {
					prod.setDescription(lstProd.get(i).getDescription());
				} else {
					prod.setDescription(lstProd.get(i).getDescription().substring(0, 25).concat("..."));
				}
			} else {
				prod.setDescription(lstProd.get(i).getDescription());
			}
			prod.setImage(lstProd.get(i).getImage());
			prod.setImage1(lstProd.get(i).getImage1());
			prod.setImage2(lstProd.get(i).getImage2());
			prod.setImage3(lstProd.get(i).getImage3());
			prod.setPrice(lstProd.get(i).getPrice());
			prod.setIsActive(lstProd.get(i).getIsActive());
			prod.setIdcate(lstProd.get(i).getIdcate());
			prod.setIdSupplier(lstProd.get(i).getIdSupplier());
			prod.setNameCate(lstProd.get(i).getNameCate());
			prod.setNameSupp(lstProd.get(i).getNameSupp());
			List<ProductView_A> sold = ilaProjectCoffeeRepository.getInstance()
					.CallSoldOfProduct(lstProd.get(i).getIdSupplier());
			for (int j = 0; j < sold.size(); j++) {
				if (sold.get(j).getId() == lstProd.get(i).getId()) {
					prod.setSold(sold.get(j).getSold());
				}
			}
			ls.add(prod);
		}
		return ls;
	}

	public List<ProductView_A> prodFilterActive(int isActive, int dt) {
		List<ProductView_A> ls = new ArrayList<>();
		List<ProductView_A> lstProd = ilaProjectCoffeeRepository.getInstance().CallProductByActive(isActive);
		for (int i = 0; i < lstProd.size(); i++) {
			ProductView_A prod = new ProductView_A();
			prod.setId(lstProd.get(i).getId());
			prod.setTitle(lstProd.get(i).getTitle());
			if (lstProd.get(i).getDescription().length() > 25) {
				if (dt == 1) {
					prod.setDescription(lstProd.get(i).getDescription());
				} else {
					prod.setDescription(lstProd.get(i).getDescription().substring(0, 25).concat("..."));
				}
			} else {
				prod.setDescription(lstProd.get(i).getDescription());
			}
			prod.setImage(lstProd.get(i).getImage());
			prod.setImage1(lstProd.get(i).getImage1());
			prod.setImage2(lstProd.get(i).getImage2());
			prod.setImage3(lstProd.get(i).getImage3());
			prod.setPrice(lstProd.get(i).getPrice());
			prod.setIsActive(lstProd.get(i).getIsActive());
			prod.setIdcate(lstProd.get(i).getIdcate());
			prod.setIdSupplier(lstProd.get(i).getIdSupplier());
			prod.setNameCate(lstProd.get(i).getNameCate());
			prod.setNameSupp(lstProd.get(i).getNameSupp());
			List<ProductView_A> sold = ilaProjectCoffeeRepository.getInstance()
					.CallSoldOfProduct(lstProd.get(i).getIdSupplier());
			for (int j = 0; j < sold.size(); j++) {
				if (sold.get(j).getId() == lstProd.get(i).getId()) {
					prod.setSold(sold.get(j).getSold());
				}
			}
			ls.add(prod);
		}
		return ls;
	}

	public List<ProductView_A> prodFilterSupp(int idSupplier, int dt) {
		List<ProductView_A> ls = new ArrayList<>();
		List<ProductView_A> lstProd = ilaProjectCoffeeRepository.getInstance().CallProductByIdSupp(idSupplier);
		for (int i = 0; i < lstProd.size(); i++) {
			ProductView_A prod = new ProductView_A();
			prod.setId(lstProd.get(i).getId());
			prod.setTitle(lstProd.get(i).getTitle());
			if (lstProd.get(i).getDescription().length() > 25) {
				if (dt == 1) {
					prod.setDescription(lstProd.get(i).getDescription());
				} else {
					prod.setDescription(lstProd.get(i).getDescription().substring(0, 25).concat("..."));
				}
			} else {
				prod.setDescription(lstProd.get(i).getDescription());
			}

			prod.setPrice(lstProd.get(i).getPrice());
			prod.setIsActive(lstProd.get(i).getIsActive());
			prod.setIdcate(lstProd.get(i).getIdcate());
			prod.setIdSupplier(lstProd.get(i).getIdSupplier());
			prod.setNameCate(lstProd.get(i).getNameCate());
			prod.setNameSupp(lstProd.get(i).getNameSupp());
			List<ProductView_A> sold = ilaProjectCoffeeRepository.getInstance()
					.CallSoldOfProduct(lstProd.get(i).getIdSupplier());
			for (int j = 0; j < sold.size(); j++) {
				if (sold.get(j).getId() == lstProd.get(i).getId()) {
					prod.setSold(sold.get(j).getSold());
				}
			}
			ls.add(prod);
		}
		return ls;
	}

	public List<ProductView_A> prodFilterMul(int isActive, int idSupplier, int dt) {
		List<ProductView_A> ls = new ArrayList<>();
		List<ProductView_A> lstProd = ilaProjectCoffeeRepository.getInstance().CallProdByActiveAndSupp(isActive,
				idSupplier);
		for (int i = 0; i < lstProd.size(); i++) {
			ProductView_A prod = new ProductView_A();
			prod.setId(lstProd.get(i).getId());
			prod.setTitle(lstProd.get(i).getTitle());
			if (lstProd.get(i).getDescription().length() > 25) {
				if (dt == 1) {
					prod.setDescription(lstProd.get(i).getDescription());
				} else {
					prod.setDescription(lstProd.get(i).getDescription().substring(0, 25).concat("..."));
				}
			} else {
				prod.setDescription(lstProd.get(i).getDescription());
			}

			prod.setPrice(lstProd.get(i).getPrice());
			prod.setIsActive(lstProd.get(i).getIsActive());
			prod.setIdcate(lstProd.get(i).getIdcate());
			prod.setIdSupplier(lstProd.get(i).getIdSupplier());
			prod.setNameCate(lstProd.get(i).getNameCate());
			prod.setNameSupp(lstProd.get(i).getNameSupp());
			List<ProductView_A> sold = ilaProjectCoffeeRepository.getInstance()
					.CallSoldOfProduct(lstProd.get(i).getIdSupplier());
			for (int j = 0; j < sold.size(); j++) {
				if (sold.get(j).getId() == lstProd.get(i).getId()) {
					prod.setSold(sold.get(j).getSold());
				}
			}
			ls.add(prod);
		}
		return ls;
	}

}
