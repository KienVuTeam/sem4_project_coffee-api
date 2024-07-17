package com.project.controller.Admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.Category;
import com.project.modelview.ProductView_A;
import com.project.repository.ilaProjectCoffeeRepository;

@Controller
@RequestMapping("/Admin")
public class CategoriesMgController {
	@GetMapping("/categories-management")
	public String dashboard(Model lstCate) {
		lstCate.addAttribute("listCate", ilaProjectCoffeeRepository.getInstance().CallAllCate());
		lstCate.addAttribute("listProduct", ilaProjectCoffeeRepository.getInstance().CallAllProduct());
		return "Admin/categoriesMg";
	}

	@PostMapping("/insertCate")
	public String insertCate(@ModelAttribute Category addCate, Model lstCate) {
		long millis = System.currentTimeMillis();
		Date current = new Date(millis);
		addCate.setCreateDate(current);
		addCate.setActive(true);
		ilaProjectCoffeeRepository.getInstance().CallInsertCate(addCate);
		//
		lstCate.addAttribute("listCate", ilaProjectCoffeeRepository.getInstance().CallAllCate());
		lstCate.addAttribute("listProduct", ilaProjectCoffeeRepository.getInstance().CallAllProduct());
		return "Admin/Share/partialCategoryMg";
	}

	@GetMapping("/ADcheckCate")
	@ResponseBody
	public Boolean checkCate(String titleCate) {
		boolean check=false;
		var lstCate = ilaProjectCoffeeRepository.getInstance().CallAllCate();
		for (Category category : lstCate) {
			if(titleCate.equals(category.getTitle())){
				check=true;
			}
		}
		return check;
	}

	@PostMapping("/categories-management/updateCate")
	public String updateCate(@ModelAttribute Category modelUpdate, String titleEdit, int idEdit, String createDateEdit,
			Model lstCate) {
		modelUpdate.setId(idEdit);
		modelUpdate.setTitle(titleEdit);
		modelUpdate.setCreateDate(Date.valueOf(createDateEdit));
		modelUpdate.setActive(true);
		ilaProjectCoffeeRepository.getInstance().CallUpdateCate(modelUpdate);
		//
		lstCate.addAttribute("listCate", ilaProjectCoffeeRepository.getInstance().CallAllCate());
		lstCate.addAttribute("listProduct", ilaProjectCoffeeRepository.getInstance().CallAllProduct());
		return "Admin/Share/partialCategoryMg";
	}

	@PostMapping("/categories-management/activeCate")
	public String deactiveCate(@ModelAttribute Category modelUpdate, int idActive, int isActive) {

		modelUpdate.setId(idActive);
		if (isActive == 0) {
			modelUpdate.setActive(false);
		} else {
			modelUpdate.setActive(true);
		}
		ilaProjectCoffeeRepository.getInstance().CallUpdateActiveCate(modelUpdate);
		return "redirect:/Admin/categories-management";
	}

	@GetMapping("/categories-management/prodOfCate")
	public String productOfCate(Model lstCate, @RequestParam int idCate) {
		lstCate.addAttribute("lstProd", prodOfCate(idCate));
		lstCate.addAttribute("idCate", idCate);
		return "Admin/Share/partialProdOfCate";
	}

	@PostMapping("/categories-management/update-product")
	public String updateProduct(Model lstCate, @RequestParam int idCate, @ModelAttribute ProductView_A updateProd) {
		ilaProjectCoffeeRepository.getInstance().CallUpdateProd(updateProd);
		//
		lstCate.addAttribute("lstProd", prodOfCate(idCate));
		lstCate.addAttribute("idCate", idCate);
		return "Admin/Share/partialProdOfCate";
	}

	public List<ProductView_A> prodOfCate(int idCate) {
		List<ProductView_A> ls = new ArrayList<>();
		List<ProductView_A> lstProd = ilaProjectCoffeeRepository.getInstance().CallAllProduct();
		for (int i = 0; i < lstProd.size(); i++) {
			if (lstProd.get(i).getIdcate() == idCate) {
				ProductView_A prod = new ProductView_A();
				prod.setId(lstProd.get(i).getId());
				prod.setTitle(lstProd.get(i).getTitle());
				if (lstProd.get(i).getDescription().length() > 25) {
					prod.setDescription(lstProd.get(i).getDescription().substring(0, 25).concat("..."));
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
		}
		return ls;
	}
}
