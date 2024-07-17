package com.project.controller.Clients;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.Review;
import com.project.others.StringHandle;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

@Controller
@RequestMapping("/Rating")
public class RatingController {

	@GetMapping("/ajax/ratingInvoiceView")
	public String viewRatingInvoice(Model model, int idInvoiceD, int productId, String productName) {
		model.addAttribute("idInvoiceD", idInvoiceD);
		model.addAttribute("productId", productId);
		model.addAttribute("productName", StringHandle.getInstance().leftWid(productName, 35) + "...");
		model.addAttribute("dfProductName",productName);
		return "/AjaxView/UserRatings";
	}

	@GetMapping("/ajax/userRatingProducts")
	@ResponseBody
	public boolean userRatingProducts(@CookieValue(value = StringValue.nameCookieUser) int idUsers ,int idProduct, int idInvoiceD, int idRatingS) {
		Review objectInsert = new Review();
		objectInsert.setIdAccount(idUsers);
		objectInsert.setIdProduct(idProduct);
		objectInsert.setIdInvoice(idInvoiceD);
		objectInsert.setReview(idRatingS);
		//
		return ilaProjectCoffeeRepository.getInstance().userRatingProducts(objectInsert);
	}
}
