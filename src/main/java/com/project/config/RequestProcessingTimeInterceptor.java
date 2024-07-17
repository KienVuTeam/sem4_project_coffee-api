package com.project.config;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.project.controller.Admin.AdminPageController;
import com.project.controller.Admin.BlogMgController;
import com.project.controller.Admin.CategoriesMgController;
import com.project.controller.Admin.CustomerMgController;
import com.project.controller.Admin.DashboardController;
import com.project.controller.Admin.OrderMgController;
import com.project.controller.Admin.ProductMgController;
import com.project.controller.Admin.RevenueMgController;
import com.project.controller.Admin.SupplierMgController;
import com.project.controller.Admin.VoucherMgController;
import com.project.controller.Clients.AccountController;
import com.project.controller.Clients.CartController;
import com.project.controller.Clients.InvoiceController;
import com.project.controller.Clients.ProfileController;
import com.project.controller.Clients.RatingController;
import com.project.controller.Clients.RegisterController;
import com.project.controller.Clients.UsersPageController;
import com.project.controller.Supplier.Dashboard;
import com.project.controller.Supplier.InvoiceManagementController;
import com.project.controller.Supplier.ManagerBlog;
import com.project.controller.Supplier.ManagerDiscount;
import com.project.controller.Supplier.ManagerProduct;
import com.project.controller.Supplier.ManagerRevenue;
import com.project.controller.Supplier.ManagerVoucher;
import com.project.controller.Supplier.Profile;
import com.project.controller.Supplier.SupplierLogin;
import com.project.controller.Supplier.SupplierRegister;
import com.project.others.CheckCookieUsers;
import com.project.utils.MappingUrls;
import com.project.utils.StringValue;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestProcessingTimeInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		/**
		 * @Customer
		 */
		if (handler instanceof HandlerMethod && (
				 ((HandlerMethod) handler).getBean() instanceof CartController     ||
				 ((HandlerMethod) handler).getBean() instanceof AccountController  || 
				 ((HandlerMethod) handler).getBean() instanceof InvoiceController  ||
				 ((HandlerMethod) handler).getBean() instanceof ProfileController  ||
				 ((HandlerMethod) handler).getBean() instanceof RatingController   )) 
		{
			/**
			 * @All Need To Login 
			 */
			var existsCookieU = CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser);
			if (!existsCookieU) {
				response.sendRedirect(StringValue.redirectULogin);
				return false;
			}
			
		} else if ((handler instanceof HandlerMethod &&  ((HandlerMethod) handler).getBean() instanceof RegisterController )) {
			/**
			 * @Prevent After Login Controller
			 */
			var existsCookieU = CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser);
			if (existsCookieU) {
				response.sendRedirect(StringValue.redirectUProfile);
				return false;
			}
		} else if ((handler instanceof HandlerMethod &&  ((HandlerMethod) handler).getBean() instanceof UsersPageController )) {
			/**
			 * @Prevent After Login SinglePage
			 */
			var currentUrls = request.getRequestURI().trim();
			MappingUrls.getInstance();
			if (!MappingUrls.usersAfterLogin.containsKey(currentUrls)) {
				return true;
			} 
			var existsCookieU = CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser);
			if (MappingUrls.usersAfterLogin.get(currentUrls) == existsCookieU) {
				response.sendRedirect(StringValue.baseURL);
				return false;
			}
		}
		
		/**
		 * @Supplier
		 */
		else if (handler instanceof HandlerMethod && (
				 ((HandlerMethod) handler).getBean() instanceof Dashboard                    	||
				 ((HandlerMethod) handler).getBean() instanceof InvoiceManagementController  	|| 
				 ((HandlerMethod) handler).getBean() instanceof ManagerBlog                  	||
				 ((HandlerMethod) handler).getBean() instanceof ManagerDiscount              	||
				 ((HandlerMethod) handler).getBean() instanceof ManagerProduct          	 	||
				 ((HandlerMethod) handler).getBean() instanceof ManagerVoucher               	||
				 ((HandlerMethod) handler).getBean() instanceof Profile               	        ||
			     ((HandlerMethod) handler).getBean() instanceof ManagerRevenue                  )) 
		{
			/**
			 * @All Need To Login 
			 */
			var existsCookieU = CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieSupplier);
			if (!existsCookieU) {
				response.sendRedirect(StringValue.redirectSLogin);
				return false;
			}
		}
		else if (handler instanceof HandlerMethod && (
				 ((HandlerMethod) handler).getBean() instanceof SupplierLogin                   ||
			     ((HandlerMethod) handler).getBean() instanceof SupplierRegister                )) 
		 {
			/**
			 * @Prevent After Login Controller
			 */
			var existsCookieU = CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieSupplier);
			if (existsCookieU) {
				response.sendRedirect(StringValue.dashBoardSupplier);
				return false;
			}
		}
		
		/**
		 * @Admin
		 */
		else if (handler instanceof HandlerMethod && (
				 ((HandlerMethod) handler).getBean() instanceof BlogMgController                    	||
				 ((HandlerMethod) handler).getBean() instanceof CategoriesMgController  				|| 
				 ((HandlerMethod) handler).getBean() instanceof CustomerMgController                  	||
				 ((HandlerMethod) handler).getBean() instanceof DashboardController              		||
				 ((HandlerMethod) handler).getBean() instanceof OrderMgController          	 			||
				 ((HandlerMethod) handler).getBean() instanceof ProductMgController               		||
				 ((HandlerMethod) handler).getBean() instanceof RevenueMgController               	    ||
			     ((HandlerMethod) handler).getBean() instanceof SupplierMgController                  	||
			     ((HandlerMethod) handler).getBean() instanceof VoucherMgController))
		{
			/**
			 * @All Need To Login 
			 */
			var existsCookieU = CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieAdmin);
			if (!existsCookieU) {
				response.sendRedirect(StringValue.redirectALogin);
				return false;
			}
		}
		else if (handler instanceof HandlerMethod && (
				 ((HandlerMethod) handler).getBean() instanceof AdminPageController             )) 
		 {
			/**
			 * @Prevent After Login Controller
			 */
			var existsCookieU = CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieAdmin);
			if (existsCookieU) {
				response.sendRedirect(StringValue.dashBoardAdmin);
				return false;
			}
		}
		
		return true;
	}
	
	
}
