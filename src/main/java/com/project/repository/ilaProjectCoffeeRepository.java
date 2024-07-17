package com.project.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.calling.AccountCalling;
import com.project.calling.AdminCalling;
import com.project.calling.BatchCalling;
import com.project.calling.BlogCalling;
import com.project.calling.CallingBlog_Sup;
import com.project.calling.CallingInvoice_Sup;
import com.project.calling.CartCalling;
import com.project.calling.CategoryCalling;
import com.project.calling.CommentBlogCalling;
import com.project.calling.DiscountCalling;
import com.project.calling.InvoiceAdminCalling;
import com.project.calling.InvoiceCalling;
import com.project.calling.InvoiceDetailsCalling;
import com.project.calling.InvoiceSupplierCalling;
import com.project.calling.ProductsCalling;
import com.project.calling.RecoverCalling;
import com.project.calling.RevenueCalling;
import com.project.calling.ReviewCalling;
import com.project.calling.SupplierCalling;
import com.project.calling.VoucherCalling;
import com.project.calling.WatchListCalling;
import com.project.model.Account;
import com.project.model.Address;
import com.project.model.Admin;
import com.project.model.Blog;
import com.project.model.Category;
import com.project.model.CommentBlog;
import com.project.model.CommentBlogUS;
import com.project.model.HandleInvoiceObject;
import com.project.model.HandleStatusObject;
import com.project.model.InvoiceAdmin;
import com.project.model.InvoiceDetails;
import com.project.model.InvoiceSupplier;
import com.project.model.ObjectMessage;
import com.project.model.Product;
import com.project.model.RecoverInfo;
import com.project.model.Review;
import com.project.model.ReviewViewS;
import com.project.model.SendingMail;
import com.project.model.StatusInvoice;
import com.project.model.SubCommentBlogUS;
import com.project.model.Supplier;
import com.project.model.SupplierRefundV;
import com.project.model.Voucher;
import com.project.model.WorkPDFRevenueM;
import com.project.modelview.BlogView;
import com.project.modelview.CartView;
import com.project.modelview.CommentBlogView;
import com.project.modelview.DiscountView;
import com.project.modelview.InvoiceDetailsU;
import com.project.modelview.InvoiceSupplierView;
import com.project.modelview.InvoiceView;
import com.project.modelview.InvoiceViewU;
import com.project.modelview.MVNewBlog;
import com.project.modelview.ProductView;
import com.project.modelview.ProductView_A;
import com.project.modelview.ReviewView;
import com.project.modelview.VoucherView;
import com.project.modelview.WatchListU;
import com.project.modelview._dllTestInv;
import com.project.others.OutputWorkData;
import com.project.others.SendMail;
import com.project.utils.BaseUrl;

import jakarta.servlet.http.HttpServletResponse;

public class ilaProjectCoffeeRepository {
	private static ilaProjectCoffeeRepository instance = null;

	public static ilaProjectCoffeeRepository getInstance() {
		if (instance == null) {
			instance = new ilaProjectCoffeeRepository();
		}
		return instance;
	}

	/**
	 * @author MaiTran
	 */

	
	// Dashboard
	public int AD_countInvCurrDate() {
		return RevenueCalling.getInstance().AD_countInvCurrDate();
	}
	public BigDecimal AD_profitsCurrDate() {
		return RevenueCalling.getInstance().AD_profitsCurrDate();
	}
	
	
	// Batch
	public boolean AD_autoCheckSupplierNotConfirmA() {
		return BatchCalling.getInstance().AD_autoCheckSupplierNotConfirmA();
	}
	public boolean AD_autoCheckConfirmA() {
		return BatchCalling.getInstance().AD_autoCheckConfirmA();
	}
	// Blog
	public List<Blog> CallAllBlog() {
		return BlogCalling.getInstance().CallAllBlog();
	}

	public List<BlogView> CallAllBlogOfSupp() {
		return BlogCalling.getInstance().CallAllBlogOfSupp();
	}

	public Boolean CallInsertBlog(Blog addBlog) {
		return BlogCalling.getInstance().CallInsertBlog(addBlog);
	}

	public Boolean CallEditBlog(Blog modelUpdate) {
		return BlogCalling.getInstance().CallEditBlog(modelUpdate);
	}

	public Blog CallDetailBlog(int id) {
		return BlogCalling.getInstance().CallDetailBlog(id);
	}

	public Integer CallCountCmt(int idBlog) {
		return BlogCalling.getInstance().CallCountCmt(idBlog);
	}

	public List<CommentBlogView> CallCmtMain(int idBlog) {
		return BlogCalling.getInstance().CallCmtMain(idBlog);
	}

	public List<CommentBlogView> CallCmtSub(int idBlog) {
		return BlogCalling.getInstance().CallCmtSub(idBlog);
	}

	public Boolean CallDeleteCMT(CommentBlog deleteCmt) {
		return BlogCalling.getInstance().CallDeleteCMT(deleteCmt);
	}

	public Boolean CallUpdateCMT(CommentBlog updateCmt) {
		return BlogCalling.getInstance().CallUpdateCMT(updateCmt);
	}

	public Boolean CallInsertCMT(CommentBlog addCmt) {
		return BlogCalling.getInstance().CallDeleteCMT(addCmt);
	}

	public Boolean CallUpdateBlogSupp(Blog modelUpdate) {
		return BlogCalling.getInstance().CallUpdateBlogSupp(modelUpdate);
	}

	// Account
	public List<Account> CallAllAccount() {
		return AccountCalling.getInstance().CallAllAccount();
	}

	public Boolean CallUpdateAccount(Account modelUpdate) {
		return AccountCalling.getInstance().CallUpdateAccount(modelUpdate);
	}

	// Order OfCus
	public List<InvoiceView> AD_CallOrderOfCus(int idAccount) {
		return InvoiceCalling.getInstance().AD_CallOrderOfCus(idAccount);
	}

	public List<InvoiceView> AD_CallProduct_OrderOfCus(int id, int idSupplier) {
		return InvoiceCalling.getInstance().AD_CallProduct_OrderOfCus(id, idSupplier);
	}


	// Category
	public List<Category> CallAllCate() {
		return CategoryCalling.getInstance().CallAllCate();
	}

	public Boolean CallInsertCate(Category addCate) {
		return CategoryCalling.getInstance().CallInsertCate(addCate);
	}

	public Boolean CallUpdateCate(Category modelUpdate) {
		return CategoryCalling.getInstance().CallUpdateCate(modelUpdate);
	}

	public Boolean CallUpdateActiveCate(Category modelUpdate) {
		return CategoryCalling.getInstance().CallUpdateActiveCate(modelUpdate);
	}

	// Product
	public List<ProductView_A> CallAllProduct() {
		return ProductsCalling.getInstance().CallAllProduct();
	}

	public List<ProductView_A> CallSoldOfProduct(int idSupplier) {
		return ProductsCalling.getInstance().CallSoldOfProduct(idSupplier);
	}

	public Boolean CallUpdateProd(ProductView_A modelUpdate) {
		return ProductsCalling.getInstance().CallUpdateProd(modelUpdate);
	}

	public List<ProductView_A> CallProductByActive(int isActive) {
		return ProductsCalling.getInstance().CallProductByActive(isActive);
	}

	public List<ProductView_A> CallProductByIdSupp(int idSupplier) {
		return ProductsCalling.getInstance().CallProductByIdSupp(idSupplier);
	}

	public List<ProductView_A> CallProdByActiveAndSupp(int isActive, int idSupplier) {
		return ProductsCalling.getInstance().CallProdByActiveAndSupp(isActive, idSupplier);
	}

	// Invoice
	public List<InvoiceView> CallGetName_Phone_Date_TotalInvoice() {
		return InvoiceCalling.getInstance().CallGetName_Phone_Date_TotalInvoice();
	}

	public List<InvoiceView> CallTotalPriceInvoiceSupplier(int idInv) {
		return InvoiceCalling.getInstance().CallTotalPriceInvoiceSupplier(idInv);
	}

	public List<InvoiceView> CallTotalPriceProduct_Refund(int idInvoice, int idSupplier) {
		return InvoiceCalling.getInstance().CallTotalPriceProduct_Refund(idInvoice, idSupplier);
	}
	public List<InvoiceAdmin> CallActionOrder (int id){
		return InvoiceAdminCalling.getInstance().CallActionOrder(id);
	}

	// RefundWhenInv9
	public List<SupplierRefundV> AdminGetSupplierRefundByInv(int idInvoice) {
		return InvoiceAdminCalling.getInstance().AdminGetSupplierRefundByInv(idInvoice);
	}
	public List<SupplierRefundV> AdminGetSupplierRefundByInvAndIdSup(int idInvoice, int idSupplier) {
		return InvoiceAdminCalling.getInstance().AdminGetSupplierRefundByInvAndIdSup(idInvoice,idSupplier);
	}
	
	public Boolean AdminRefundToSupp(int idSupplier, int idInvoice) {
		return InvoiceAdminCalling.getInstance().AdminRefundToSupp(idSupplier, idInvoice);
	}

	public Boolean AdminRefundToAllSupp(int idInvoice) {
		return InvoiceAdminCalling.getInstance().AdminRefundToAllSupp(idInvoice);
	}

	public Boolean AdminRefundFeeToCus(int idInvoice) {
		return InvoiceAdminCalling.getInstance().AdminRefundFeeToCus(idInvoice);
	}

	public List<InvoiceViewU> getInvoiceForOutputExcel(int Month, int Year){
		return InvoiceCalling.getInstance().getInvoiceForOutputExcel(Month, Year);
	}


	// OrderOfSupp
	public List<InvoiceView> CallInforCusOrderOfSupp(int idSupplier) {
		return InvoiceAdminCalling.getInstance().CallInforCusOrderOfSupp(idSupplier);
	}

	public List<InvoiceView> CallGetTotalOrderAmountOfSupp(int idSupplier, int idInvoice) {
		return InvoiceAdminCalling.getInstance().CallGetTotalOrderAmountOfSupp(idSupplier, idInvoice);
	}

	public List<InvoiceView> CallGetRefundtoCustomers(int idSupplier, int idInvoice) {
		return InvoiceAdminCalling.getInstance().CallGetRefundtoCustomers(idSupplier, idInvoice);
	}

	public List<InvoiceView> CallGetMoneyReceived(int status, int idInvoice, int idSupplier) {
		return InvoiceAdminCalling.getInstance().CallGetMoneyReceived(status, idInvoice, idSupplier);
	}

	public List<InvoiceSupplier> CallGetStatus(int idSupplier, int idInvoice) {
		return InvoiceAdminCalling.getInstance().CallGetStatus(idSupplier, idInvoice);
	}

	public List<InvoiceView> Call_detailOrdersSupp(int idSupplier, int idInvoice) {
		return InvoiceAdminCalling.getInstance().Call_detailOrdersSupp(idInvoice, idSupplier);
	}

	public int SelectQuantityInvoice_Ad(int id) {
		return InvoiceAdminCalling.getInstance().SelectQuantityInvoice_Ad(id);
	}

	public BigDecimal CallGetPriceVoucherInvoiceSupplier(int idSupplier, String lsVoucherS) {
		return VoucherCalling.getInstance().CallGetPriceVoucherInvoiceSupplier(idSupplier, lsVoucherS);
	}

	public Boolean AdminPaymentForSupp(InvoiceSupplier modelUpdate) {
		return InvoiceAdminCalling.getInstance().AdminPaymentForSupp(modelUpdate);
	}

	public List<_dllTestInv> CallGetInvDetails(int idInvoice) {
		return InvoiceAdminCalling.getInstance().CallGetInvDetails(idInvoice);
	}

	public Boolean AdminRefundToCus(InvoiceDetails modelUpdate) {
		return InvoiceAdminCalling.getInstance().AdminRefundToCus(modelUpdate);
	}

	// Voucher
	public List<Voucher> CallGetUseVoucherILA(String id) {
		return VoucherCalling.getInstance().CallGetUseVoucherILA(id);
	}

	public List<Voucher> CallGetPriceVoucherSupplier(String id, String usercreate) {
		return VoucherCalling.getInstance().CallGetPriceVoucherSupplier(id, usercreate);
	}

	public List<Voucher> CallAllVoucher() {
		return VoucherCalling.getInstance().CallAllVoucher();
	}

	public Boolean CallUpdateActiveVoucher(Voucher modelUpdate) {
		return VoucherCalling.getInstance().CallUpdateActiveVoucher(modelUpdate);
	}

	public Boolean CallDeleteVoucher(Voucher modelDelete) {
		return VoucherCalling.getInstance().CallDeleteVoucher(modelDelete);
	}

	public Boolean CallInsertVou(Voucher addCate) {
		return VoucherCalling.getInstance().CallInsertVou(addCate);
	}

	public List<Voucher> CallFilter1() {
		return VoucherCalling.getInstance().CallFilter1();
	}

	public List<Voucher> CallFilter2() {
		return VoucherCalling.getInstance().CallFilter2();
	}

	public List<Voucher> CallFilter3() {
		return VoucherCalling.getInstance().CallFilter3();
	}

	public Boolean CallUpdateVoucher(Voucher modelUpdate) {
		return VoucherCalling.getInstance().CallUpdateVoucher(modelUpdate);
	}

	public Voucher CallCheckVoucher(String idVoucher) {
		return VoucherCalling.getInstance().CallCheckVoucher(idVoucher);
	}

	// Supplier
	public List<Supplier> CallAllSupp() {
		return SupplierCalling.getInstance().CallAllSupp();
	}

	public Boolean CallUpdateSupplier(Supplier modelUpdate) {
		return SupplierCalling.getInstance().CallUpdateSupplier(modelUpdate);
	}

	public List<Supplier> CallFilterSupplier(int isActive) {
		return SupplierCalling.getInstance().CallFilterSupplier(isActive);
	}

	// Revenue
	public List<InvoiceSupplierView> CallChartByMonth(int month) {
		return RevenueCalling.getInstance().CallChartByMonth(month);
	}

	public List<InvoiceSupplierView> CallRevenueByMonth(int month) {
		return RevenueCalling.getInstance().CallRevenueByMonth(month);
	}

	public List<InvoiceSupplierView> CallChartByYear(int year) {
		return RevenueCalling.getInstance().CallChartByYear(year);
	}

	public List<InvoiceSupplierView> CallRevenueByYear(int year) {
		return RevenueCalling.getInstance().CallChartByYear(year);
	}

	public List<InvoiceSupplierView> CallChartByMonthYear(int month, int year) {
		return RevenueCalling.getInstance().CallChartByMonthYear(month, year);
	}

	public List<InvoiceSupplierView> CallRevenueByMonthYear(int month, int year) {
		return RevenueCalling.getInstance().CallRevenueByMonthYear(month, year);
	}

	/**
	 * @author Thuan
	 */
	// Supplier
	public boolean UpdateProfile2(Supplier modelUpdate) {
		return SupplierCalling.getInstance().CallUpdateSupplier2(modelUpdate);
	}

	public List<Supplier> GetProfileSupplier(int idSupplier) {
		return SupplierCalling.getInstance().CallGetProfileSupplier(idSupplier);
	}

	public String ChangeAvatarSupplier(String avatar, int idSupplier) {
		return SupplierCalling.getInstance().CallChangeAvatarSupplier(avatar, idSupplier);
	}

	public boolean CheckPassword(int idSupplier, String password) {
		return SupplierCalling.getInstance().CallCheckPassword(idSupplier, password);
	}

	public boolean ChangePasswordSupplier(String newPassword, String oldPassword, int idSupplier) {
		return SupplierCalling.getInstance().CallChangePasswordSupplier(newPassword, oldPassword, idSupplier);
	}

	public boolean CheckExistsOrganizationName(String title) {
		return SupplierCalling.getInstance().CallCheckExistsOrganizationName(title);
	}

	public boolean CheckExistsEmail(String email) {
		return SupplierCalling.getInstance().CallCheckExistsEmail(email);
	}

	public boolean CheckExistsUsername(String username) {
		return SupplierCalling.getInstance().CallCheckExistsUsername(username);
	}

	public boolean CheckExistsPhone(String phone) {
		return SupplierCalling.getInstance().CallCheckExistsPhone(phone);
	}

	// ============ Supplier Change Mail ==============
	public ObjectMessage suppCheckEmailForChange(int idSupplier, String newEmail) {
		return SupplierCalling.getInstance().suppCheckEmailForChange(idSupplier, newEmail);
	}

	public RecoverInfo SuppRequestSendNewMail(int idSupplier, String newEmail, int userType) {
		return SupplierCalling.getInstance().suppRequestSendNewMail(idSupplier, newEmail, userType);
	}

	public void T_SendChangeMailRequest(SendingMail mailSend, RecoverInfo recoverInfo) {
		SendMail.getInstance().T_SendChangeMailRequest(mailSend, recoverInfo);
	}

	public boolean SuppVerifyChangeEmail(String codeVerify) {
		return SupplierCalling.getInstance().suppVerifyChangeEmail(codeVerify);
	}

	public void T_SendMailForgotPassword(SendingMail mailSend, RecoverInfo recoverInfo) {
		SendMail.getInstance().T_SendMailForgotPassword(mailSend, recoverInfo);
	}

	// Product
	public List<ProductView> GetProductSupplier(int idSupplier) {
		return ProductsCalling.getInstance().CallGetProductSupplier(idSupplier);
	}

	public List<Category> GetCategory() {
		return ProductsCalling.getInstance().CallGetCategory();
	}

	public List<ProductView> FilterActive(int idSupplier, int isActive) {
		return ProductsCalling.getInstance().CallFilterActive(idSupplier, isActive);
	}

	public Boolean InsertProduct(ProductView d) {
		return ProductsCalling.getInstance().CallInsertProduct(d);
	}

	public Boolean UpdateProduct(Product d) {
		return ProductsCalling.getInstance().CallUpdateProduct(d);
	}

	public Boolean DeleteProduct(int idProduct) {
		return ProductsCalling.getInstance().CallDeleteProduct(idProduct);
	}

	public Boolean ActiveProduct(int idProduct) {
		return ProductsCalling.getInstance().CallActiveProduct(idProduct);
	}

	public List<ProductView> GetAllProductNotHaveDiscount(int idSupplier) {
		return ProductsCalling.getInstance().CallGetAllProductNotHaveDiscount(idSupplier);
	}

	public boolean GetSearchNameProduct(String nameProduct, int idSupplier) {
		return ProductsCalling.getInstance().CallSearchNameProduct(nameProduct, idSupplier);
	}

	// Review
	public List<ReviewViewS> GetDetailReview(int idProduct) {
		return ReviewCalling.getInstance().CallGetDetailReview(idProduct);
	}

	// Voucher
	public List<Voucher> GetAllVoucher(String usercreate) {
		return VoucherCalling.getInstance().CallGetAllVoucher(usercreate);
	}

	public Boolean InsertVoucher(Voucher v) {
		return VoucherCalling.getInstance().CallInsertVoucher(v);
	}

	public Boolean UpdateVoucher(Voucher v) {
		return VoucherCalling.getInstance().CallUpdateVoucher(v);
	}

	public Boolean DeleteVoucher(String id) {
		return VoucherCalling.getInstance().CallDeleteVoucher(id);
	}

	public List<Voucher> FilterVoucher1(String usercreate) {
		return VoucherCalling.getInstance().CallFilterVoucher1(usercreate);
	}

	public List<Voucher> FilterVoucher2(String usercreate) {
		return VoucherCalling.getInstance().CallFilterVoucher2(usercreate);
	}

	public List<Voucher> FilterVoucher3(String usercreate) {
		return VoucherCalling.getInstance().CallFilterVoucher3(usercreate);
	}

	public Boolean CheckDuplicateID(String id) {
		return VoucherCalling.getInstance().CallCheckDuplicateID(id);
	}

	public Boolean EndVoucher(String id) {
		return VoucherCalling.getInstance().CallEndVoucher(id);
	}

	// Discount
	public List<DiscountView> GetAllDiscount(int idSupplier) {
		return DiscountCalling.GetInstance().CallGetAllDiscount(idSupplier);
	}

	public List<DiscountView> FilterDiscountWeb(int idSupplier, int type) {
		return DiscountCalling.GetInstance().CallFilterDiscountWeb(idSupplier, type);
	}

	public Boolean InsertDiscount(List<DiscountView> d) {
		return DiscountCalling.GetInstance().CallInsertDiscount(d);
	}

	public Boolean UpdateDiscount(DiscountView d) {
		return DiscountCalling.GetInstance().CallUpdateDiscount(d);
	}

	public Boolean DeleteDiscount(int indC) {
		return DiscountCalling.GetInstance().CallDeleteDiscount(indC);
	}

	public Boolean RemoveDisOfPro(int idProduct, int indC) {
		return DiscountCalling.GetInstance().RemoveDisOfPro(idProduct, indC);
	}

	public List<DiscountView> DetailDiscount(int indC, int idSupplier) {
		return DiscountCalling.GetInstance().CallDetailDiscount(indC, idSupplier);
	}

	// Revenue
	public List<InvoiceSupplier> Sup_CallRevenue(int idSupplier, int year, int month) {
		return RevenueCalling.getInstance().Sup_CallRevenue(idSupplier, year, month);
	}
	public BigDecimal Sup_CallRevenueToday(int idSupplier) {
		return RevenueCalling.getInstance().Sup_CallRevenueToday(idSupplier);
	}
	public BigDecimal Sup_CallRevenuePreMonth(int idSupplier) {
		return RevenueCalling.getInstance().Sup_CallRevenuePreMonth(idSupplier);
	}
	public List<InvoiceSupplier> Sup_CallTotalRevenueYear(int idSupplier) {
		return RevenueCalling.getInstance().Sup_CallTotalRevenueYear(idSupplier);
	}
	public int Sup_CallCountOrder(int idSupplier) {
		return RevenueCalling.getInstance().Sup_CallCountOrder(idSupplier);
	}

	/**
	 * @author Kien
	 */
	/*
	 * public List<InvoiceView> getNameCus_Address_CreateAt(int idSupplier) { return
	 * InvoiceSupplierCalling.getInstance().getNameCus_Address_CreateAt(idSupplier);
	 * }
	 * 
	 * public List<InvoiceView> getTotalOrderAmount(int idSupplier, int idInvoice) {
	 * return getInstance().getTotalOrderAmount(idSupplier, idInvoice); }
	 * 
	 * public List<InvoiceView> GetTotalAmountOfProduct(int idSupplier) { return
	 * getInstance().GetTotalAmountOfProduct(idSupplier); }
	 * 
	 * public List<InvoiceView> GetRefundtoCustomers(int idSupplier) { return
	 * getInstance().GetRefundtoCustomers(idSupplier); }
	 * 
	 * public List<InvoiceView> GetMoneyReceived(int idSupplier, int idInvoice) {
	 * return getInstance().GetMoneyReceived(idSupplier, idInvoice); }
	 * 
	 * public List<InvoiceView> GetInvoiceDetail(int idSupplier, int id) { return
	 * getInstance().GetInvoiceDetail(idSupplier, id); }
	 * 
	 * public boolean confirmInvoiceDetail(HandleStatusObject object) { return
	 * getInstance().confirmInvoiceDetail(object); }
	 */

	/**
	 * @author Vinh
	 **/
	public List<ProductView> userGetAllProduct() {
		return ProductsCalling.getInstance().userGetAllProduct();
	}

	public List<Category> userGetAllAvalibleCategory() {
		return CategoryCalling.getInstance().userGetAllAvalibleCategory();
	}

	public List<Category> userGetAvalibleCateSupp(int idSupplier) {
		return CategoryCalling.getInstance().userGetAvalibleCateSupp(idSupplier);
	}

	public List<ProductView> getProductforUserbyCate(int idCate) {
		return ProductsCalling.getInstance().getProductforUserbyCate(idCate);
	}

	public List<ProductView> getProductforUserbyPrice(BigDecimal minPrice, BigDecimal maxPrice) {
		return ProductsCalling.getInstance().getProductforUserbyPrice(minPrice, maxPrice);
	}

	public List<ProductView> getProductforUserbyName(String textSearch) {
		return ProductsCalling.getInstance().getProductforUserbyName(textSearch);
	}

	public ProductView getProductforUserByID(int idProduct) {
		return ProductsCalling.getInstance().getProductforUserByID(idProduct);
	}

	public List<ProductView> getProductRandomFilterbyID(int currentID, int amountTake) {
		return ProductsCalling.getInstance().getProductRandomFilterbyID(currentID, amountTake);
	}

	public List<ReviewView> getUserReviewbyProductID(int idProduct) {
		return ReviewCalling.getInstance().getUserReviewbyProductID(idProduct);
	}

	public List<ProductView> userGetAllProductbySupplier(int idSupplier) {
		return ProductsCalling.getInstance().userGetAllProductbySupplier(idSupplier);
	}

	public List<ProductView> getProductforUserbyCateSupplier(int idCate, int idSupplier) {
		return ProductsCalling.getInstance().getProductforUserbyCateSupplier(idCate, idSupplier);
	}

	public List<ProductView> getProductforUserbyPriceSupplier(BigDecimal minPrice, BigDecimal maxPrice,
			int idSupplier) {
		return ProductsCalling.getInstance().getProductforUserbyPriceSupplier(minPrice, maxPrice, idSupplier);
	}

	public List<ProductView> getProductforUserbyNameSupplier(String textSearch, int idSupplier) {
		return ProductsCalling.getInstance().getProductforUserbyNameSupplier(textSearch, idSupplier);
	}

	// Cart
	public List<CartView> getUserCartSupplier(int idAccount) {
		return CartCalling.getIntance().getUserCartSupplier(idAccount);
	}

	public List<CartView> getUserCart(int idAccount) {
		return CartCalling.getIntance().getUserCart(idAccount);
	}

	public BigDecimal updateUserCart(int Amount, BigDecimal Price, int idAccount, int idProduct) {
		return CartCalling.getIntance().updateUserCart(Amount, Price, idAccount, idProduct);
	}

	public boolean removeCart(int idCart) {
		return CartCalling.getIntance().removeCart(idCart);
	}

	public boolean multiRemoveCart(String lsIdCart) {
		return CartCalling.getIntance().multiRemoveCart(lsIdCart);
	}

	public boolean checkCartCheckOut(String lsCart, int idUser, String ckVoucher) {
		return CartCalling.getIntance().checkCartCheckOut(lsCart, idUser, ckVoucher);
	}

	public List<CartView> getListSupplierCheckOut(String lsCart) {
		return CartCalling.getIntance().userGetSupplierCheckOut(lsCart);
	}

	public List<CartView> getListCartCheckOut(String lsCart) {
		return CartCalling.getIntance().userGetLsCartCheckOut(lsCart);
	}

	public ObjectMessage userAddProductIntoCart(CartView modelAdd) {
		return CartCalling.getIntance().userAddProductIntoCart(modelAdd);
	}

	// Voucher
	public List<Voucher> userGetAvalibleVoucher(String voucherType) {
		return VoucherCalling.getInstance().userGetAvalibleVoucher(voucherType);
	}

	public Voucher userGetVoucherDetails(String idVoucher) {
		return VoucherCalling.getInstance().userGetVoucherDetails(idVoucher);
	}

	public List<VoucherView> userGetVoucherSupplierbyID(int idSupplier) {
		return VoucherCalling.getInstance().userGetVoucherSupplierbyID(idSupplier);
	}

	// Account
	public Account getUserAddress(int idUser) {
		return AccountCalling.getInstance().getUserAddress(idUser);
	}

	public Account userGetProfiles(int idAccount) {
		return AccountCalling.getInstance().userGetProfiles(idAccount);
	}

	public boolean userUpdateProfile(Account modelUpdate) {
		return AccountCalling.getInstance().userUpdateProfile(modelUpdate);
	}

	public boolean usersChangePassword(int idUsers, String oldPassword, String newPassword) {
		return AccountCalling.getInstance().usersChangePassword(idUsers, oldPassword, newPassword);
	}

	public boolean usersVerifyOldPass(int idUsers, String currentPassword) {
		return AccountCalling.getInstance().usersVerifyOldPass(idUsers, currentPassword);
	}

	// Address
	public List<Address> userGetAllAddress(int idAccount) {
		return AccountCalling.getInstance().userGetAllAddress(idAccount);
	}

	public Address userGetDetailsAddress(int idAddress) {
		return AccountCalling.getInstance().userGetDetailsAddress(idAddress);
	}

	public boolean userUpdateAddress(Address modelUpdate) {
		return AccountCalling.getInstance().userUpdateAddress(modelUpdate);
	}

	public boolean userInsertAddress(Address modelInsert) {
		return AccountCalling.getInstance().userInsertAddress(modelInsert);
	}

	public boolean userDeleteAddress(int idAddress) {
		return AccountCalling.getInstance().userDeleteAddress(idAddress);
	}

	// Invoice
	public boolean userPaymentInvoice(HandleInvoiceObject invoiceObject) {
		return InvoiceCalling.getInstance().userPaymentInvoice(invoiceObject);
	}

	public List<Supplier> userGetSupplierInvDetails(int idInvoice) {
		return SupplierCalling.getInstance().userGetSupplierInvDetails(idInvoice);
	}

	public List<InvoiceDetailsU> userGetInvoiceDetails(int idAccount, int idInvoice) {
		return InvoiceDetailsCalling.GetInstance().userGetInvoiceDetails(idAccount, idInvoice);
	}

	public InvoiceViewU userGetInfoInvoice(int idInvoice) {
		return InvoiceCalling.getInstance().userGetInfoInvoice(idInvoice);
	}

	public Boolean userConfirmInvoiceDetails(HandleStatusObject objectHandle) {
		return InvoiceCalling.getInstance().userConfirmInvoiceDetails(objectHandle);
	}

	public List<InvoiceViewU> userGetAllInvoice(int idAccount) {
		return InvoiceCalling.getInstance().userGetAllInvoice(idAccount);
	}


	public List<InvoiceViewU> userSearchInvoice(int idAccount, String codeSearch) {
		return InvoiceCalling.getInstance().userSearchInvoice(idAccount, codeSearch);
	}

	// Ratings
	public boolean userRatingProducts(Review objectInsert) {
		return ReviewCalling.getInstance().userRatingProducts(objectInsert);
	}

	// Comment
	public List<CommentBlogUS> userGetCommentBlogs(int idBlog) {
		return CommentBlogCalling.getInstance().userGetCommentBlogs(idBlog);
	}

	public SubCommentBlogUS userInsertNewCommentBlogs(SubCommentBlogUS modelAdd) {
		return CommentBlogCalling.getInstance().userInsertNewCommentBlogs(modelAdd);
	}

	public boolean userDeleteCommentBlog(int idBlog, int idComment) {
		return CommentBlogCalling.getInstance().userDeleteCommentBlog(idBlog, idComment);
	}

	public boolean userEditCommentBlog(int idCM, String content) {
		return CommentBlogCalling.getInstance().userEditCommentBlog(idCM, content);
	}

	// dll
	public List<_dllTestInv> dllAdminInvoice(int idInvoice) {
		return InvoiceCalling.getInstance().dllAdminInvoice(idInvoice);
	}

	public List<_dllTestInv> dllSupplierInvoice(int idInvoice, int idSupplier) {
		return InvoiceCalling.getInstance().dllSupplierInvoice(idInvoice, idSupplier);
	}

	// Blog
	public BlogView userGetBlogDetails(int idBlog) {
		return BlogCalling.getInstance().userGetBlogDetails(idBlog);
	}

	public List<Blog> userGetRandomBlog(int amountTake, int idRBlog) {
		return BlogCalling.getInstance().userGetRandomBlog(amountTake, idRBlog);
	}

	public List<BlogView> userGetAllBlogs() {
		return BlogCalling.getInstance().userGetAllBlogs();
	}

	public List<WatchListU> usersGetWatchList(int idUser, String lsWatchW, String searchProductN, int sortDFlag,
			int sortPFlag) {
		return WatchListCalling.getInstance().usersGetWatchList(idUser, lsWatchW, searchProductN, sortDFlag, sortPFlag);
	}

	public boolean userLoginAddWList(String lsProducts, int idUser) {
		return WatchListCalling.getInstance().userLoginAddWList(lsProducts, idUser);
	}

	public ObjectMessage usersAddWatchList(int idProduct, int idUser) {
		return WatchListCalling.getInstance().usersAddWatchList(idProduct, idUser);
	}

	public boolean usersDeleteWatchList(int idProduct, int idUser) {
		return WatchListCalling.getInstance().usersDeleteWatchList(idProduct, idUser);
	}

	public BigDecimal userGetFeeService(int idInvoice) {
		return InvoiceCalling.getInstance().userGetFeeService(idInvoice);
	}

	// Recover
	public RecoverInfo userRecoverPassword(String emailSendding, int userType) {
		return RecoverCalling.getInstance().userRecoverPassword(emailSendding, userType);
	}

	public boolean userCheckRecoverkeyPassword(String keyRecover) {
		return RecoverCalling.getInstance().userCheckRecoverkeyPassword(keyRecover);
	}

	public boolean userChangePasswordByRecover(String newPassword, String keyRecover) {
		return RecoverCalling.getInstance().userChangePasswordByRecover(newPassword, keyRecover);
	}

	// SendingMail
	public void sendMailForgotPassword(SendingMail mailSend, RecoverInfo recoverInfo) {
		SendMail.getInstance().sendMailForgotPassword(mailSend, recoverInfo);
	}

	public void supplierSendMailVerify(SendingMail mailSend, RecoverInfo recoverInfo) {
		SendMail.getInstance().supplierSendMailVerify(mailSend, recoverInfo);
	}

	// Change Email
	public ObjectMessage userCheckEmailForChange(int idUsers, String newEmail) {
		return AccountCalling.getInstance().userCheckEmailForChange(idUsers, newEmail);
	}

	public RecoverInfo userRequestSendNewMail(int idUser, int userType, String newEmail) {
		return AccountCalling.getInstance().userRequestSendNewMail(idUser, userType, newEmail);
	}

	public void sendChangeMailRequest(SendingMail mailSend, RecoverInfo recoverInfo) {
		SendMail.getInstance().sendChangeMailRequest(mailSend, recoverInfo);
	}

	public boolean userVerifyChangeEmail(String codeVerify) {
		return AccountCalling.getInstance().userVerifyChangeEmail(codeVerify);
	}

	// Register
	public boolean userCheckExistsUserName(String userName) {
		return AccountCalling.getInstance().userCheckExistsUserName(userName);
	}

	public boolean userCheckExistsUserEmail(String userEmail) {
		return AccountCalling.getInstance().userCheckExistsUserEmail(userEmail);
	}

	public RecoverInfo userRegisterAccount(Account modelRegister) {
		return AccountCalling.getInstance().userRegisterAccount(modelRegister);
	}

	public void sendMailRegisterVerification(SendingMail mailSend, RecoverInfo recoverInfo) {
		SendMail.getInstance().sendMailRegisterVerification(mailSend, recoverInfo);
	}

	public boolean userVerifyRegisterAccount(String codeLink) {
		return AccountCalling.getInstance().userVerifyRegisterAccount(codeLink);
	}

	// Login
	public Account userLoginAccount(String userName, String userPassword) {
		return AccountCalling.getInstance().userLoginAccount(userName, userPassword);
	}

	// Batch
	public boolean autoConfirmSupplierInvoiceU(int idInvoice) {
		return BatchCalling.getInstance().autoConfirmSupplierInvoiceU(idInvoice);
	}

	public boolean autoStartVoucherType(String userType) {
		return BatchCalling.getInstance().autoStartVoucherType(userType);
	}

	public boolean autoEndVoucherType(String userType) {
		return BatchCalling.getInstance().autoEndVoucherType(userType);
	}

	public boolean autoStartDiscount(int idSupplier) {
		return BatchCalling.getInstance().autoStartDiscount(idSupplier);
	}

	public boolean autoEndDiscount(int idSupplier) {
		return BatchCalling.getInstance().autoEndDiscount(idSupplier);
	}

	public boolean autoUpdateItemInCart(int idUsers) {
		return BatchCalling.getInstance().autoUpdateItemInCart(idUsers);
	}

	public boolean autoStartEndDiscountUsers() {
		return BatchCalling.getInstance().autoStartEndDiscountUsers();
	}
	
	public boolean autoCheckNotConfirmSupplierU(int idInvoice) {
		return BatchCalling.getInstance().autoCheckNotConfirmSupplierU(idInvoice);
	}

	// Supplier RAG
	public boolean checkExitsTitleOfSupplier(String supplierTitle) {
		return SupplierCalling.getInstance().checkExitsTitleOfSupplier(supplierTitle);
	}

	public boolean checkExistsUserNameOfSupplier(String userName) {
		return SupplierCalling.getInstance().checkExistsUserNameOfSupplier(userName);
	}

	public boolean checkExistsEmailOfSupplier(String supplierEmail) {
		return SupplierCalling.getInstance().checkExistsEmailOfSupplier(supplierEmail);
	}

	public Supplier supplierLoginAccount(String userName, String userPassword) {
		return SupplierCalling.getInstance().supplierLoginAccount(userName, userPassword);
	}

	public RecoverInfo supplierRegisterAccount(Account modelRegister) {
		return SupplierCalling.getInstance().supplierRegisterAccount(modelRegister);
	}

	public boolean supplierVerifyRegisterAccount(String codeLink) {
		return SupplierCalling.getInstance().supplierVerifyRegisterAccount(codeLink);
	}

	public boolean usersCheckStatusAccount(int idUsers) {
		return AccountCalling.getInstance().usersCheckStatusAccount(idUsers);
	}

	// 13/12

	public Admin adminLoginAccount(String userName, String userPassword) {
		return AdminCalling.getInstance().adminLoginAccount(userName, userPassword);
	}

	// OutputData
	public boolean createRevenueExcel(HttpServletResponse response,int Month, int Year, List<InvoiceViewU> lsData) {
		return OutputWorkData.getInstance().createRevenueExcel(response, Month, Year, lsData);
	}
	
	public byte[] outputRevenueInvoicePDF(int Month, int Year) {
		return OutputWorkData.getInstance().outputRevenueInvoicePDF(Month,Year);
	}
	
	public List<WorkPDFRevenueM> getInvoiceForOutputPDF(int Month, int Year){
		return InvoiceCalling.getInstance().getInvoiceForOutputPDF(Month, Year);
	}
	
	public List<InvoiceViewU> getChartsRevenueAdmin(int Month, int Year){
		return InvoiceCalling.getInstance().getChartsRevenueAdmin(Month, Year);
	}
	
	
	
	
	
	
	

	// 13/12

	/**
	 * @author Kien
	 *
	 */
	public List<InvoiceView> getNameCus_Address_CreateAt(int idSupplier) {
		return CallingInvoice_Sup.getInstance().getNameCus_Address_CreateAt(idSupplier);
	}

	public List<InvoiceView> getTotalOrderAmount(int idSupplier, int idInvoice) {
		return CallingInvoice_Sup.getInstance().getTotalOrderAmount(idSupplier, idInvoice);
	}

	public List<InvoiceView> GetTotalAmountOfProduct(int idSupplier, int idInvoice) {
		return CallingInvoice_Sup.getInstance().GetTotalAmountOfProduct(idSupplier, idInvoice);
	}

	public List<InvoiceView> GetRefundtoCustomers(int idSupplier, int idInvoice) {
		return CallingInvoice_Sup.getInstance().GetRefundtoCustomers(idSupplier, idInvoice);
	}

	public List<InvoiceView> GetMoneyReceived(int status, int idSupplier, int idInvoice) {
		return CallingInvoice_Sup.getInstance().GetMoneyReceived(status, idSupplier, idInvoice);
	}

	public List<InvoiceView> GetInvoiceDetail(int idSupplier, int id) {
		return CallingInvoice_Sup.getInstance().GetInvoiceDetail(idSupplier, id);
	}

	public boolean confirmInvoiceDetail(HandleStatusObject object) {
		return CallingInvoice_Sup.getInstance().confirmInvoiceDetail(object);
	}

	// new
	public List<InvoiceSupplier> getActionOrder(int idInvoi, int idSup) {
		return CallingInvoice_Sup.getInstance().getActionOrder(idInvoi, idSup);
	}

	// new 0711
	public BigDecimal getPriceVoucher(int idSupplier, String idVoucher) {
		return CallingInvoice_Sup.getInstance().getPriceVoucher(idSupplier, idVoucher);
	}

	// 0911
	public boolean ConfirmInvoiM(InvoiceSupplier is) {
		return CallingInvoice_Sup.getInstance().ConfirmInvoiM(is);
	}

	//
	// 27-12
	public String prepareInvoice(int idSupplier, int idInvoice, String strLsInvoiD, String strLsStatus) {
		return CallingInvoice_Sup.getInstance().prepareInvoice(idSupplier, idInvoice, strLsInvoiD, strLsStatus);
	}

	public boolean supConfirmRefund(int idInvoice, int idSupp) {
		return CallingInvoice_Sup.getInstance().supConfirmRefund(idInvoice, idSupp);
	}

	public boolean Sup_ConfirmReceivedMoneyOfAd(HandleStatusObject obj) {
		return CallingInvoice_Sup.getInstance().Sup_ConfirmReceivedMoneyOfAd(obj);
	}

	// disable
	public Voucher GetPriceVoucher(String id, String userCreate) {
		return CallingInvoice_Sup.getInstance().GetPriceVoucher(id, userCreate);
	}

	// 7-1
	public BigDecimal Sup_getPriceRefundAdmin(int idSupp, int idInvoice) {
		return CallingInvoice_Sup.getInstance().Sup_getPriceRefundAdmin(idSupp, idInvoice);
	}
	// end

	// Blog

	public List<MVNewBlog> GetAllBlog(int userCreate) {
		return CallingBlog_Sup.getInstance().GetAllBlog(userCreate);
	}

	public boolean InsertNewBlog(Blog blog) {
		return CallingBlog_Sup.getInstance().InsertNewBlog(blog);
	}

	public Blog GetDetailBlog(int id) {
		return CallingBlog_Sup.getInstance().GetDetailBlog(id);
	}

	public Blog EditDetailBlog(int id) {
		return CallingBlog_Sup.getInstance().EditDetailBlog(id);
	}

	public boolean UpdateDetailBlog(Blog blog, int id) {
		return CallingBlog_Sup.getInstance().UpdateDetailBlog(blog, id);
	}
	// ---------------------End

	// 29/11 Tran
	// Comment
	/*
	 * public List<CommentBlogUS> userGetCommentBlogs(int idBlog) { return
	 * CallingBlog_Sup.getInstance().userGetCommentBlogs(idBlog); } public
	 * SubCommentBlogUS userInsertNewCommentBlogs(SubCommentBlogUS modelAdd) {
	 * return CallingBlog_Sup.getInstance().userInsertNewCommentBlogs(modelAdd); }
	 * public boolean userDeleteCommentBlog(int idBlog, int idComment) { return
	 * CallingBlog_Sup.getInstance().userDeleteCommentBlog(idBlog, idComment); }
	 * 
	 * public boolean userEditCommentBlog(int idCM, String content) { return
	 * CallingBlog_Sup.getInstance().userEditCommentBlog(idCM, content); } // them
	 * moi 07/12 public Blog CallDetailBlog(int id) { return
	 * CallingBlog_Sup.getInstance().CallDetailBlog(id); }
	 * 
	 * public Integer CallCountCmt(int idBlog) { return
	 * CallingBlog_Sup.getInstance().CallCountCmt(idBlog); }
	 */

	// End K
}
