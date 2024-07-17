package com.project.controller.Clients;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.utils.StringValue;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandleController implements ErrorController {
	@RequestMapping("/error")
	public String handleError(
			HttpServletRequest request, 
			@CookieValue(value = StringValue.nameCookieUser, defaultValue = "0") int idUser,
			@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier,
			@CookieValue(value = StringValue.nameCookieAdmin, defaultValue = "0") int ckAdmin
			) 
	{
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    String redirectPage = "redirect:";
	    if (ckAdmin != 0) {
			redirectPage += StringValue.dashBoardAdmin;
		} else if (idSupplier != 0) {
			redirectPage += StringValue.dashBoardSupplier;
		} else {
			redirectPage += StringValue.baseURL;
		}
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return redirectPage;
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	return redirectPage;
	        }
	        else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
	        	return redirectPage;
	        }
	    }
	    return "";
	}
	
	
	
}
