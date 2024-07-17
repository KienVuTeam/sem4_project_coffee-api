package com.project.utils;

public class BaseUrl {
	/**
	 * @author MaiTran
	 **/
	// ADMIN
	public static final String AD_CHECKLOGIN = "http://localhost:8081/Admin/Login";
	public static final String AD_CHECKEMAIL = "http://localhost:8081/Admin/CheckEmailPwd";
	// dashboard
	public static final String AD_countInvCurrDate ="http://localhost:8081/api/Invoice/countInvoiceCurrentDate";
	public static final String AD_profitsCurrDate="http://localhost:8081/api/Invoice/countCurrentProfitsInCurrentDate";
	// quan ly cus - account
	public static final String AD_ALLACCOUNT = "http://localhost:8081/api/Account/adminGetAllAccountUser";
	public static final String AD_UPDATEACCOUNT = "http://localhost:8081/api/Account/adminChangeStatusAccount";
	// quan ly order of Cus
	public static final String AD_DETAILORDEROFCUS="http://localhost:8081/api/InvoiceDetails/userGetDetailsofInvoice";
	public static final String AD_ORDEROFCUS="http://localhost:8081/api/Invoice/Admin_MgOrder_Cus";
	public static final String AD_PRODUCT_ORDEROFCUS="http://localhost:8081/api/Invoice/Admin_MgProdOrder_Cus";
	// quan ly supplier
	public static final String AD_ALLSUPPLIER = "http://localhost:8081/api/Supplier/adminGetAllSupplier";
	public static final String AD_UPDATESUPPLIER = "http://localhost:8081/api/Supplier/changeStatusSupplier";
	public static final String AD_FILTERSUPPLIER = "http://localhost:8081/api/Supplier/FilterSupp";
	// quan ly category
	public static final String AD_ALLCATE = "http://localhost:8081/api/Category/GetAllCate";
	public static final String AD_INSERTCATE = "http://localhost:8081/api/Category/InsertCate";
	public static final String AD_GETBYIDCATE = "http://localhost:8081/api/Category/GetIDCate";
	public static final String AD_UPDATECATE = "http://localhost:8081/api/Category/UpdateCate";
	public static final String AD_UPDATEACTIVECATE = "http://localhost:8081/api/Category/UpdateActiveCate";
	public static final String AD_FILTERCATE = "http://localhost:8081/api/Category/FilterCate";
	// quan ly product
	public static final String AD_ALLPRODUCT = "http://localhost:8081/api/Supplier/Products/GetAllProduct";
	public static final String AD_SOLDPRODUCT = "http://localhost:8081/api/Supplier/Products/Supplier_List_Sell_Many";
	public static final String AD_UPDATEPRODUCT = "http://localhost:8081/api/Supplier/Products/changeStatusProductofSupplier";
	public static final String AD_FILTERACTIVEPROD = "http://localhost:8081/api/Supplier/Products/FilterActiveProd";
	public static final String AD_FILTERPRODSUPP = "http://localhost:8081/api/Supplier/Products/FilterProdSupp";
	public static final String AD_FTACTIVEPRODSUPP = "http://localhost:8081/api/Supplier/Products/FilterActiveProdOfSup";
	// quan ly blog cua admin
	public static final String AD_ADDBLOG = "http://localhost:8081/api/Blog/addNewBlog";
	public static final String AD_ALLBLOG = "http://localhost:8081/api/Blog/adminGetBlog";
	public static final String AD_DETAILBLOG = "http://localhost:8081/api/Blog/viewBlogDetails";
	public static final String AD_UPDATEBLOGADMIN = "http://localhost:8081/api/Blog/updateBlog";
	public static final String AD_FILTERBLOG = "http://localhost:8081/api/Blog/adminFilterBlog";
	// quan ly blog cua supplier
	public static final String AD_ALLBLOGSUPP = "http://localhost:8081/api/Blog/getListBlogsSupplier";
	public static final String AD_UPDATEBLOGSUPP = "http://localhost:8081/api/Blog/updateBlogOfSupplier";
	// quan ly comment
	public static final String AD_CMTMAIN = "http://localhost:8081/api/Comment/Admin_GetCMT_Main";
	public static final String AD_CMTSUB = "http://localhost:8081/api/Comment/Admin_GetCMT_Sub";
	public static final String AD_INSERTCMT = "http://localhost:8081/api/Comment/InsertCMT";
	public static final String AD_UPDATECMT = "http://localhost:8081/api/Comment/UpdateCMT";
	public static final String AD_DELETECMT = "http://localhost:8081/api/Comment/DeleteCMT";
	public static final String AD_COUNTCMT = "http://localhost:8081/api/Comment/CountCMT";
	// quan ly voucher
	public static final String AD_ALLVOUCHER = "http://localhost:8081/api/Voucher/adminGetAllVoucher";
	public static final String AD_INSERTVOUCHER = "http://localhost:8081/api/Voucher/insertNewVoucher";
	// public static final String AD_CHECKVOUCHER =
	// "http://localhost:8081/api/Voucher/adminCheckExistsVoucher";
	public static final String AD_FILTER1 = "http://localhost:8081/api/Voucher/adminFilterVoucher00";
	public static final String AD_FILTER2 = "http://localhost:8081/api/Voucher/adminFilterVoucher01";
	public static final String AD_FILTER3 = "http://localhost:8081/api/Voucher/adminFilterVoucher03";
	public static final String AD_UPDATEVOUCHER = "http://localhost:8081/api/Voucher/updateVoucher";
	public static final String AD_DELETEVOUCHER = "http://localhost:8081/api/Voucher/deleteVoucher";
	public static final String AD_CHECKVOUCHER = "http://localhost:8081/api/Voucher/GetIDVoucher";
	// quan ly doanh thu
	public static final String AD_GETCHARTBYMONTH = "http://localhost:8081/api/Invoice/Chart30Days";
	public static final String AD_GETCHARTBYYEAR = "http://localhost:8081/api/Invoice/Chart12Months";
	public static final String AD_GETCHARTBYMONTHYEAR = "http://localhost:8081/api/Invoice/Chart30DaysYear";

	public static final String AD_GETREVENUEBYMONTH = "http://localhost:8081/api/Invoice/Total30Days";
	public static final String AD_GETREVENUEBYYEAR = "http://localhost:8081/api/Invoice/Total12Months";
	public static final String AD_GETREVENUEBYMONTHYEAR = "http://localhost:8081/api/Invoice/Total30DaysYear";

	// ad qly don hang of supp
	public static final String AD_INFORCUS_ORDEROFSUPP = "http://localhost:8081/api/InvoiceSupplier/GetNameAddDate";
	public static final String AD_TOTALAMOUNT_ORDEROFSUPP = "http://localhost:8081/api/InvoiceSupplier/GetTotalOrderAmount";
	public static final String AD_REFUNDCUS_ORDEROFSUPP = "http://localhost:8081/api/InvoiceSupplier/GetRefundtoCustomers";
	public static final String AD_GETMONEYRECEIVED_ORDEROFSUPP = "http://localhost:8081/api/InvoiceSupplier/GetMoneyReceived";
	public static final String AD_STATUSORDER_ORDEROFSUPP = "http://localhost:8081/api/InvoiceSupplier/ActionOrder";
	public static final String AD_DETAILORDER_ORDEROFSUPP = "http://localhost:8081/api/InvoiceSupplier/getDetailOrder";
	// get price voucher trong don hang cua supp
	public static final String AD_PRICEVOUCHER_ORDEROFSUPP = "http://localhost:8081/api/Voucher/getPriceVoucherInSupplierInvoice";
	
	// quan ly don hang of admin
	public static final String AD_GETNAME_PHONE_DATE_TOTALINVOICE = "http://localhost:8081/api/Invoice/Admin_GetName_Phone_Date_TotalInvoice";
	public static final String AD_GETUSEVOUCHERILA = "http://localhost:8081/api/Invoice/Admin_GetUseVoucherILA";
	public static final String AD_GETTOTALPRICE_INVSUPP = "http://localhost:8081/api/Invoice/Admin_Get_TotalPriceInvoiceSupplier";
	public static final String AD_GETTOTALPRICE_PROD_REFUND = "http://localhost:8081/api/Invoice/Admin_Get_TotalPriceProductofSupplier_Refundtocustomers";
	public static final String AD_GETPRICEVOUCHERSUPPLIER = "http://localhost:8081/api/Invoice/Admin_GetPriceVoucherSupplier";
	public static final String AD_ACTIONORDER = "http://localhost:8081/api/Invoice/Admin_ActionOrder";
	public static final String AD_GETDETAILORDERSUPP = "http://localhost:8081/api/Invoice/Admin_GetDetailOrderSup";
	public static final String AD_COUNTPROD_OFINV = "http://localhost:8081/api/Invoice/countQuantityInvoice";
	
	//adminPaymentForSupp
	public static final String AD_PAYMENTFORSUPP = "http://localhost:8081/api/InvoiceSupplier/ConfirmInv";
	public static final String AD_REFUNDTOCUS = "http://localhost:8081/api/InvoiceSupplier/ConfirmInvDetail";
	public static final String AD_GETINVOICEDETAILS = "http://localhost:8081/api/Invoice/dllAdminInvoice";
	//adminRefund
	public static final String AD_REFUND_ALLSUPP = "http://localhost:8081/api/Invoice/adminRefundAllSupplier";
	public static final String AD_REFUND_TOSUPP = "http://localhost:8081/api/Invoice/adminRefundToSupplier";
	public static final String AD_REFUND_FEETOCUS = "http://localhost:8081/api/Invoice/adminRefundFeeService";
	//check sttRefundVoucherSupp
	public static final String AD_getSupplierRefundByInv ="http://localhost:8081/api/Supplier/RefundV/getSupplierRefundByInv";
	public static final String AD_getSupplierRefundByInvAndIdSup ="http://localhost:8081/api/Supplier/RefundV/getSupplierRefundByInvAndIdSup";
	
	//autoBatch Admin
	public static final String AD_autoCheckSupplierNotConfirmA ="http://localhost:8081/api/Batch/autoCheckSupplierNotConfirmA";
	public static final String AD_autoCheckConfirmA ="http://localhost:8081/api/Batch/autoCheckConfirmA";
	
	/**
	 * @author VINH
	 **/
	public static final String US_GETPRODUCTS = "http://localhost:8081/api/Products/userGetAllProducts";
	public static final String US_GETAVALIBLE_CATE = "http://localhost:8081/api/Category/userGetAllAvalibleCategory";
	public static final String US_GETAVALIBLE_CATESUPP="http://localhost:8081/api/Category/userGetCategorybySupplier?idSupplier=queryParam";
	public static final String US_GETPRODUCTS_BYCATE = "http://localhost:8081/api/Products/getProductforUserbyCate?idCate=cateReplace";
	public static final String US_GETPORUDCTS_BYPRICE = "http://localhost:8081/api/Products/getProductforUserbyPrice?minPrice=paramPriceMin&maxPrice=paramPriceMax";
	public static final String US_GETPRODUCTS_SEARCH = "http://localhost:8081/api/Products/userSearchProductbyName?textSearch=queryParam";
	public static final String US_GETPRODUCTS_BYID = "http://localhost:8081/api/Products/userSearchProductbyID?idProduct=queryParam";
	public static final String US_GETPRODUCTS_RANDOM = "http://localhost:8081/api/Products/userRandomProductfilter?currentID=paramID&amountTake=paramAmount";
	public static final String US_GETREVIEW_BYPRODUCT = "http://localhost:8081/api/Review/getReviewbyIdProduct?idProduct=queryParam";
	// Product Supplier
	public static final String US_GETPRODUCTS_SUPPLIER = "http://localhost:8081/api/Products/userProductbySupplier?idSupplier=queryParam";
	public static final String US_GETPRODUCTS_BYCATE_SUPPLIER = "http://localhost:8081/api/Products/getProductforUserbyCateSupplier?idCate=cateReplace&idSupplier=queryParam";
	public static final String US_GETPORUDCTS_BYPRICE_SUPPLIER = "http://localhost:8081/api/Products/getProductforUserbyPriceSupplier?minPrice=paramPriceMin&maxPrice=paramPriceMax&idSupplier=paramSupplier";
	public static final String US_GETPRODUCTS_SEARCH_SUPPLIER = "http://localhost:8081/api/Products/userSearchProductbyNameSupplier?nameSearch=queryParam&idSupplier=paramSupplier";
	// Cart
	public static final String US_GET_CART_SUPPLIER = "http://localhost:8081/api/Cart/userListSupplierCart?idAccount=queryParam";
	public static final String US_GET_CART = "http://localhost:8081/api/Cart/getUserCart?idAccount=queryParam";
	public static final String US_UPDATE_CART = "http://localhost:8081/api/Cart/userChangeCart?idAccount=paramAccount&idProduct=paramProduct&Price=paramPrice&Amount=paramAmount";
	public static final String US_REMOVE_CART = "http://localhost:8081/api/Cart/userRemoveCart?idCart=queryParam";
	public static final String US_MULTI_REMOVE_CART = "http://localhost:8081/api/Cart/userRemoveMultiCart?lsIdCart=queryParam";
	public static final String US_CHECKLS_CART = "http://localhost:8081/api/Cart/userCheckListCart?lsCart=queryList&idUser=queryUser&ckVoucher=queryVoucher";
	public static final String US_ADD_CARTS = "http://localhost:8081/api/Cart/userInsertCart";
	// CheckOut
	public static final String US_GET_SUPPLIER_CKOU = "http://localhost:8081/api/Cart/userGetSupplierCheckOut?lsCart=queryParam";
	public static final String US_GET_LSCART_CKOU = "http://localhost:8081/api/Cart/userGetCartCheckOut?lsCart=queryParam";
	// Account
	public static final String US_GET_ADDRESS = "http://localhost:8081/api/Account/userGetAddress?idAccount=queryParam";
	public static final String US_GETPROFILE = "http://localhost:8081/api/Account/userGetProfile?idAccount=queryParam";
	public static final String US_UPDATEPROFILE ="http://localhost:8081/api/Account/updateUserProfile";
	public static final String US_VERIFYPASS = "http://localhost:8081/api/Account/usersVerifyPass?idUser=queryUser&currentPass=queryPass";
	public static final String US_CHANGEPASS = "http://localhost:8081/api/Account/usersChangePass?idUser=queryUser&oldPassword=queryOldPass&newPassword=queryNewPass";
	// Address
	public static final String US_GET_ALL_ADDRESS = "http://localhost:8081/api/Account/userGetAllAddress?idAccount=queryParam";
	public static final String US_GET_DETAIS_ADDRESS = "http://localhost:8081/api/Account/userDeitalsAddress?idAddress=queryParam";
	public static final String US_UPDATE_ADDRESS = "http://localhost:8081/api/Account/userUpdateAddress";
	public static final String US_INSERT_ADDRESS = "http://localhost:8081/api/Account/userInsertAddress";
	public static final String US_DELETE_ADDRESS = "http://localhost:8081/api/Account/userDeleteAddress?idAddress=queryParam";
	// Voucher
	public static final String US_VOUCHER = "http://localhost:8081/api/Voucher/getVoucherForUser?voucherType=queryParam";
	public static final String US_VOUCHER_INFO = "http://localhost:8081/api/Voucher/getVoucherDetails?idVoucher=queryParam";
	public static final String US_GET_VOUCHER_SUPPLIER = "http://localhost:8081/api/Voucher/getLsVoucherSupplier?idSupplier=queryParam";

	// Invoice
	public static final String US_PAYMENT_INV = "http://localhost:8081/api/Invoice/userPaymentCart";
	public static final String US_GETALL_INV="http://localhost:8081/api/Invoice/userGetAllInvoice?idAccount=queryParam";
	public static final String US_SEARCH_INV="http://localhost:8081/api/Invoice/userSearchInvoiceCode?idAccount=queryId&searchText=querySearch";
	public static final String US_GETFEE_IV = "http://localhost:8081/api/Invoice/usersGetFreeService?idInvoice=queryInvoice";
	
	
	// InvoiceDetails
	public static final String US_INVOICE_DETAILS = "http://localhost:8081/api/InvoiceDetails/userGetDetailsofInvoice?idInvoice=paramInvoice&idAccount=paramAccount";
	public static final String US_SUPPLIER_INVDS = "http://localhost:8081/api/Supplier/getSupplierInvoiceDetails?idInvoice=queryParam";
	public static final String US_GETINFO_INV = "http://localhost:8081/api/Invoice/userDetailsInvoice?idInvoice=queryParam";
	public static final String US_CONFIRM_INVD = "http://localhost:8081/api/Invoice/statusOfInvoice";

	// Review
	public static final String US_RATINGS_PRODUCT = "http://localhost:8081/api/Review/userRatingProduct";

	// CommentBlog
	public static final String US_CMTBLOGS = "http://localhost:8081/api/Comment/userViewAllCommentBlog?idBlog=queryParam";
	public static final String US_ADDCMTBLOG = "http://localhost:8081/api/Comment/userAddNewComment";
	public static final String US_DELCMTBLOG = "http://localhost:8081/api/Comment/userDeleteCommentBlog?idComment=paramIdCMT&idBlog=paramIdBlog";
	public static final String US_EDITCMTBLOG = "http://localhost:8081/api/Comment/userEditCommentBlog?idCM=paramQueryID&content=paramContent";

	// Test
	public static final String INVOICEDLL_ADMIN = "http://localhost:8081/api/Invoice/dllAdminInvoice?idInvoice=paramInvoice";
	public static final String INVOICEDLL_SUPP = "http://localhost:8081/api/Invoice/dllSupplierInvoice?idInvoice=paramInvoice&idSupplier=paramSupp";
	
	// Blog
	public static final String US_BLOGDETAILS="http://localhost:8081/api/Blog/userBlogDetails?idBlog=queryParam";
	public static final String US_RANDOMBLOGS="http://localhost:8081/api/Blog/userRandomBlog?amountTake=queryTake&idRBlog=queryIdR";
	public static final String US_GETALLBLOGS="http://localhost:8081/api/Blog/userGetAllBlogs";
	
	// WatchList
	public static final String US_GETWATCHLS="http://localhost:8081/api/WatchList/usersWatchList?idUser=queryIdU&lsWatchW=queryLs&searchProductN=querySearchN&sortDFlag=querySortD&sortPFlag=querySortP";
	public static final String US_LOGADDW = "http://localhost:8081/api/WatchList/usersLoginAddWatchList?lsProducts=queryLsProducts&idUser=queryIdUser";
	public static final String US_ADDWATCHLIST="http://localhost:8081/api/WatchList/usersAddWatchList?idProduct=queryProduct&idUser=queryIdUser";
	public static final String US_DELWALIST = "http://localhost:8081/api/WatchList/usersDeleteWatchList?idProduct=queryProduct&idUser=queryIdUser";
	
	// Recover
	public static final String US_RECOVERPASS ="http://localhost:8081/api/Recover/RecoverPassword?emailSendding=queryEmail&userType=queryTypeU";
	public static final String US_CHKKEYREPASS ="http://localhost:8081/api/Recover/checkRecoverKeyPassword?reciveKey=queryKey";
	public static final String US_CHGPASS_BYREC = "http://localhost:8081/api/Recover/userChangePasswordbyRecover?newPassword=queryNewPass&keyRecover=queryKeyRecover";
	
	// ChangeEmail
	public static final String US_CHKEMAIL_CHG ="http://localhost:8081/api/Account/userCheckEmailForChange?idAccount=queryIdUser&newEmail=queryEmail";
	public static final String US_SENDRCH_MAIL = "http://localhost:8081/api/Account/userSendMailChange?idAccount=queryId&userType=queryUser&newEmail=queryEmail";
	public static final String US_VERIFY_CHMAIL = "http://localhost:8081/api/Account/userVerifyChangeEmail?codeVerify=queryCode";
	
	// Register
	public static final String US_CHK_USNAME = "http://localhost:8081/api/Account/userCheckExUserName?userName=queryCheck";
	public static final String US_CHK_UEMAIL = "http://localhost:8081/api/Account/userCheckExEmail?userEmail=queryEmail";
	public static final String US_REGISTER = "http://localhost:8081/api/Account/userRegisterAccount";
	public static final String US_VERIFY_REGISTER = "http://localhost:8081/api/Account/userVerifyRegister?keyVerify=queryCode";
	
	// Login
	public static final String US_LOGIN_ACCO = "http://localhost:8081/api/Account/usersLogin?userName=queryUName&userPassword=queryUPass";
	public static final String US_CHECK_STATUS = "http://localhost:8081/api/Account/getStatusOfAccount?idUsers=queryParam";
	
	// Batch
	public static final String AUTO_CONF_INVOICED = "http://localhost:8081/api/Batch/autoCheckConfirmU?idInvoice=queryId";
	public static final String AUTO_START_V = "http://localhost:8081/api/Batch/autoStartVoucher?userType=queryUser";
	public static final String AUTO_END_V = "http://localhost:8081/api/Batch/autoEndVoucher?userType=queryUser";
	public static final String AUTO_START_D = "http://localhost:8081/api/Batch/autoStartDiscount?idSupplier=querySupp";
	public static final String AUTO_END_D = "http://localhost:8081/api/Batch/autoEndDiscount?idSupplier=querySupp"; 
	public static final String AUTO_STARTEND_DU = "http://localhost:8081/api/Batch/autoStartEndDiscountUsers";
	public static final String AUTO_UPDATE_C = "http://localhost:8081/api/Batch/autoUpdateItemInCart?idUsers=queryId";
	public static final String AUTO_CHK_UCONF_S = "http://localhost:8081/api/Batch/autoCheckSupplierNotConfirmU?idInvoice=queryParam";
	//
	public static final String Supp_AutoCheckConfirmS ="http://localhost:8081/api/Batch/autoCheckConfirmS?idSupplier=param1";
	public static final String Supp_AutoCheckSupplierNotConfirmS ="http://localhost:8081/api/Batch/autoCheckSupplierNotConfirmS?idSupplier=param1";
	
	// Supplier RAG
	public static final String SUPP_CKEX_TITLE = "http://localhost:8081/api/Supplier/checkExistsSupplierTitle?supplierTitle=queryParam"; 
	public static final String SUPP_CHEX_UNAME = "http://localhost:8081/api/Supplier/checkExistsSupplierUserName?userName=queryParam";
	public static final String SUPP_CHEX_EMAIL = "http://localhost:8081/api/Supplier/checkExistsSupplierEmail?supplierEmail=queryParam";
	public static final String SUPP_LOGIN = "http://localhost:8081/api/Supplier/supplierLogin?userName=queryUName&userPassword=queryUPass";
	public static final String SUPP_REGISTER = "http://localhost:8081/api/Supplier/workRegister";
	public static final String SUPP_VERIFY_REGISTER = "http://localhost:8081/api/Supplier/verifyRegister?keyVerify=queryParam";
	
	// Admin
	public static final String AD_LOGIN_ACC = "http://localhost:8081/api/Admin/adminLogin?userName=queryUName&userPassword=queryUPass";
	public static final String GET_DATA_OUEXCEL = "http://localhost:8081/api/Invoice/getDataOutputWorkEx?Month=queryParam00&Years=queryParam01";
	public static final String GET_DATA_OUTPDF = "http://localhost:8081/api/Invoice/getDataOutputWorkPDF?Month=queryParam00&Years=queryParam01";
	
	
	// Charts
	public static final String GET_CHARTS_ADMIN = "http://localhost:8081/api/Invoice/getDataChartsRevenueAdmin?Month=queryParam00&Years=queryParam01";
	
	/**
	 * @author Kien
	 **/
		
		// 0911 new
		public static final String Sup_GetAllBlog = "http://localhost:8081/api/Blog/userGetListBlog?idUser=param1";
		public static final String Sup_InsertNewBlog = "http://localhost:8081/api/Blog/addNewBlog";
		public static final String Sup_GetDetailBlog = "http://localhost:8081/api/Blog/viewBlogDetails?idBlog=param1";
		public static final String Sup_EditDetailBlog = "http://localhost:8081/api/Blog/viewBlogDetails?idBlog=param1";
		public static final String Sup_UpdateDetailBlog = "http://localhost:8081/api/Blog/updateBlog";
		public static final String Sup_GetAllCmtBlog = "http://localhost:8081/Supplier/GetDetailCMTBlog?idBlog=param1";

		// 29/11 Tran
		// CommentBlog
			//public static final String US_CMTBLOGS = "http://localhost:8081/api/Comment/userViewAllCommentBlog?idBlog=queryParam";
			//public static final String US_ADDCMTBLOG = "http://localhost:8081/api/Comment/userAddNewComment";
			//public static final String US_DELCMTBLOG = "http://localhost:8081/api/Comment/userDeleteCommentBlog?idComment=paramIdCMT&idBlog=paramIdBlog";
			//public static final String US_EDITCMTBLOG = "http://localhost:8081/api/Comment/userEditCommentBlog?idCM=paramQueryID&content=paramContent";
			
			//new 
			//public static final String AD_DETAILBLOG = "http://localhost:8081/api/Blog/viewBlogDetails";
			//public static final String AD_COUNTCMT = "http://localhost:8081/api/Comment/CountCMT";
		// End

		// Invoice 08-10 -/api/InvoiceSupplier
		public static final String Sup_NameCus_Address_CreateAt = "http://localhost:8081/api/InvoiceSupplier/GetNameAddDate?idSupplier=param1";
		public static final String Sup_GetTotalOrderAmount = "http://localhost:8081/api/InvoiceSupplier/GetTotalOrderAmount?idSupplier=param1&idInvoice=param2";
		public static final String Sup_GetTotalAmountOfProduct = "http://localhost:8081/api/InvoiceSupplier/GetTotalAmountOfProduct?idSupplier=param1&idInvoice=param2";
		public static final String Sup_GetRefundtoCustomers = "http://localhost:8081/api/InvoiceSupplier/GetRefundtoCustomers?idSupplier=param1&idInvoice=param2";
		public static final String Sup_GetMoneyReceived = "http://localhost:8081/api/InvoiceSupplier/GetMoneyReceived?status=param1&idInvoice=param2&idSupplier=param3";
		public static final String Sup_GetPriceVoucherNew = "http://localhost:8081/api/Voucher/getPriceVoucherInSupplierInvoice?idSupplier=param1&lsVoucherS=param2";
		public static final String Sup_GetXacNhanDaNhanTienSupp = "http://localhost:8081/Supplier/GetXacNhanDaNhanTienSupp"; // -post
		public static final String Sup_ActionOrder = "http://localhost:8081/Supplier/ActionOrder?isStatus=param1&status=param2"; // ???
		public static final String Sup_GetActionOrder = "http://localhost:8081/api/InvoiceSupplier/ActionOrder?id=param1&idSupplier=param2";

		//public static final String Sup_getInvoiceDetail = "http://localhost:8081/api/InvoiceSupplier/getDetailOrder?idSupplier=param1&id=param2";
		public static final String Sup_getInvoiceDetail = "http://localhost:8081/api/InvoiceSupplier/getDetailOrder?id=param1&idSupplier=param2";

		//new 27-12
		public static final String Sup_prepareInvoice ="http://localhost:8081/api/Invoice/supplierPrepareInvoice?idSupplier=param1&idInvoice=param2&strLsInvoiceD=param3&strLsStatus=param4";
		public static final String Sup_ConfirmRefund ="http://localhost:8081/api/Invoice/supplierConfirmRefund?idInvoice=param1&idSupplier=param2";
		public static final String Sup_GetPriceVoucherOld ="http://localhost:8081/api/InvoiceSupplier/GetPriceVoucher?id=param1&usercreate=param2"; //disable
		public static final String Sup_ConfirmReceivedMoneyOfAd ="http://localhost:8081/api/Invoice/statusOfInvoice";
		//end
		//new 7-1
		public static final String Sup_getPriceRefundAdmin ="http://localhost:8081/api/InvoiceSupplier/getPriceRefundAdmin?idSupplier=param1&idInvoice=param2";
		//end
		public static final String Sup_confirmInvoiceDetail = "http://localhost:8081/api/Invoice/statusOfInvoice"; //disable
		public static final String Sup_confirmInvoiceM = "http://localhost:8081/api/InvoiceSupplier/ConfirmInv";//disable

		// ---end 2110

	/**
	 * @author Thuan
	 **/
	// ====== Supplier ======

	// Account
	public static final String Sup_UpdateProfile2 = "http://localhost:8081/api/Supplier/UpdateProfileSupp2";
	public static final String Sup_GetProfileSupplier = "http://localhost:8081/api/Supplier/ProfileSupplier";
	public static final String Sup_ChangeAvatarSupplier = "http://localhost:8081/api/Supplier/ChangeAvatar";
	public static final String Sup_CheckPassword = "http://localhost:8081/api/Supplier/SupVerifyPass";
	public static final String Sup_ChangePasswordSupplier = "http://localhost:8081/api/Supplier/SupChangePass";
	public static final String Sup_CheckExistsOrganizationName = "http://localhost:8081/api/Supplier/checkExistsOrganizationName";
	public static final String Sup_CheckExistsEmail = "http://localhost:8081/api/Supplier/checkExistsEmail";
	public static final String Sup_CheckExistsUsername = "http://localhost:8081/api/Supplier/checkExistsUsername";
	public static final String Sup_CheckExistsPhone = "http://localhost:8081/api/Supplier/checkExistsPhone";
	public static final String Sup_CheckEmailForChange = "http://localhost:8081/api/Supplier/suppCheckEmailForChange";
	public static final String Sup_SendMailChange = "http://localhost:8081/api/Supplier/suppSendMailChange";
	public static final String Sup_VerifyChangeEmail = "http://localhost:8081/api/Supplier/suppVerifyChangeEmail";

	// Revenue
	public static final String Sup_GetRevenue = "http://localhost:8081/api/InvoiceSupplier/getSupplier_Revenue";
	public static final String Sup_GetRevenueToday = "http://localhost:8081/api/InvoiceSupplier/getSupplier_RevenueToday";
	public static final String Sup_GetRevenuePreMonth = "http://localhost:8081/api/InvoiceSupplier/getSupplier_RevenuePreMonth";
	public static final String Sup_GetTotalRevenueYear = "http://localhost:8081/api/InvoiceSupplier/getSupplier_TotalYearRevenue";
	public static final String Sup_GetCountOrder = "http://localhost:8081/api/InvoiceSupplier/getSupplier_CountOrder";

	// Discount
	public static final String Sup_GetAllDiscount = "http://localhost:8081/api/Discount/GetAlldisWeb";
	public static final String Sup_GetFilterDiscountWeb = "http://localhost:8081/api/Discount/Supplier_FilterDiscountWeb";
	public static final String Sup_InsertDiscount = "http://localhost:8081/api/Discount/insertDis";
	public static final String Sup_UpdateDiscount = "http://localhost:8081/api/Discount/UpdateDiscount";
	public static final String Sup_DeleteDiscount = "http://localhost:8081/api/Discount/DeleteDiscountNew";
	public static final String Sup_RemoveDiscountOfPro = "http://localhost:8081/api/Discount/RemoveDiscountOfProWeb";
	public static final String Sup_DetailDiscount = "http://localhost:8081/api/Discount/GetProdOfDisc";

	// Voucher
	public static final String Sup_GetAllVoucher = "http://localhost:8081/api/Voucher/supplierGetAllVoucher";
	public static final String Sup_GetFilterVoucher1 = "http://localhost:8081/api/Voucher/supplierFilterVoucher00";
	public static final String Sup_GetFilterVoucher2 = "http://localhost:8081/api/Voucher/supplierFilterVoucher01";
	public static final String Sup_GetFilterVoucher3 = "http://localhost:8081/api/Voucher/supplierFilterVoucher02";
	public static final String Sup_InsertVoucher = "http://localhost:8081/api/Voucher/insertNewVoucher";
	public static final String Sup_UpdateVoucher = "http://localhost:8081/api/Voucher/updateVoucher";
	public static final String Sup_DeleteVoucher = "http://localhost:8081/api/Voucher/deleteVoucher";
	public static final String Sup_CheckDuplicateIDVoucher = "http://localhost:8081/api/Voucher/CheckDuplicateIDVoucher";
	public static final String Sup_EndVoucher = "http://localhost:8081/api/Voucher/EndVoucher";

	// Product
	public static final String Sup_CallGetAllProductNotHaveDiscount = "http://localhost:8081/api/Supplier/Products/GetAllProdNotHaveDiscount";
	public static final String Sup_CallGetProductSupplier = "http://localhost:8081/api/Supplier/Products/GetProductSupplier";
	public static final String Sup_CallFilterActive = "http://localhost:8081/api/Supplier/Products/FilterActive";
	public static final String Sup_InsertProduct = "http://localhost:8081/api/Supplier/Products/insertNewProduct";
	public static final String Sup_UpdateProduct = "http://localhost:8081/api/Supplier/Products/supplierUpdateProduct";
	public static final String Sup_Category = "http://localhost:8081/api/Category/cateNew";
	public static final String Sup_DeleteProduct = "http://localhost:8081/api/Supplier/Products/disableProduct";
	public static final String Sup_ActiveProduct = "http://localhost:8081/api/Supplier/Products/activeProduct";
	public static final String Sup_SearchNameProduct = "http://localhost:8081/api/Products/userSearchProductbyNameSupplier";

	// Review
	public static final String Sup_GetDetailReview = "http://localhost:8081/api/Supplier/Review/GetDetailReview";
		
	
	
}
