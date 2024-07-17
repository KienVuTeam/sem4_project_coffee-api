package com.project.controller.Supplier;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.calling.CallingBlog_Sup;
import com.project.model.Blog;
import com.project.model.CommentBlog;
import com.project.model.CommentBlogUS;
import com.project.model.SubCommentBlogUS;
import com.project.model.Supplier;
import com.project.modelview.CommentBlogView;
import com.project.modelview.MVNewBlog;
import com.project.modelview.MVOrderManagement;
import com.project.others.Encode_Decode;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.BaseUrl;
import com.project.utils.StringValue;

@Controller()
public class ManagerBlog {

	RestTemplate restTemplate;

	@GetMapping("/index-blog")
	public String IndexBlog(Model model) {
		return "Supplier/test";
	}

	// xong 1
	@PostMapping("/Supplier/create-blog")
	@ResponseBody
	public String InsertNewBlog(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, Model model, String blogTitle, String blogImage, String blogDescription) {
		try {
			System.out.println("run here");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
			Date date = new Date();
			Blog b = new Blog();
			b.setTitle(blogTitle);
			b.setImage(blogImage);
			b.setDescription(blogDescription);
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			System.out.println("date sql :" + sqlDate);
			b.setCreateDate(sqlDate);
			b.setUserCreate(idSupplier);
			b.setIdAccount(1);
//			
			boolean checkInsert = false;
			checkInsert= ilaProjectCoffeeRepository.getInstance().InsertNewBlog(b);
			if(checkInsert) {
				return "{'data':'success', 'status' :'true'}";
			}else {
				return"{'status' : false}";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("err supp create new blog: "+e);
		}
		return null;
		
	}

	@GetMapping("Supplier/getAll-blog")
	public String getAllBlog(@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier, 
			@CookieValue(value = "suppCookie", defaultValue = "0") String suppCookie,Model model) {
		
		model.addAttribute("lBlog", ilaProjectCoffeeRepository.getInstance().GetAllBlog(idSupplier));
		
		//============ Thuan Fix Avatar, Ten Cua Supp ============
		String decodeSupp = Encode_Decode.getInstance().decodeString(suppCookie);
		Supplier supplier = (Supplier) Encode_Decode.getInstance().jsonToObject(decodeSupp, Supplier.class);

		model.addAttribute("suppCookie", supplier);	
		return "Supplier/test";
	}
	@GetMapping("Supplier/get-detail-blog")
	@ResponseBody
	public Blog getDetailBlogById(@RequestParam int id, Model model) {

		Blog b = new Blog();
		try {
			b =ilaProjectCoffeeRepository.getInstance().GetDetailBlog(id);
			if(b != null) {			
				model.addAttribute("blogDetail", b);
				return b;
			}
		} catch (Exception e) {
			System.out.println("err get detail blog: "+e);
		}
		return null;

	}
	@GetMapping("/Supplier/edit-blog")
	@ResponseBody
	public Blog EditBlogById(@RequestParam int id, Model model) {
		Blog b = new Blog();

		b =ilaProjectCoffeeRepository.getInstance().EditDetailBlog(id);
		if(b != null) {			
			return b;
		}else {
			return null;
		}
	}
    //xong 5 
	@PostMapping("/Supplier/update-blog")
	@ResponseBody
	public String UpdateBlog(@RequestParam int id, String blogTitle, String blogImg, String blogDescription,@CookieValue(value = StringValue.nameCookieSupplier, defaultValue = "0") int idSupplier) {
		// ====================Blog - model
		Blog b = new Blog();
		b.setId(id);
		b.setTitle(blogTitle);
		b.setImage(blogImg);
		b.setDescription(blogDescription);
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		b.setCreateDate(sqlDate);
		b.setUserCreate(idSupplier);
		b.setIdAccount(1);

		boolean checkUpdateBlog = false;
		checkUpdateBlog = ilaProjectCoffeeRepository.getInstance().UpdateDetailBlog(b, id);
		if(checkUpdateBlog) {			
			return "{status: true}";
		}else {
			return "{status: false}";
		}
	}

	// xong 6 
//	@GetMapping("/Supplier/getall-cmt")
//	@ResponseBody
//	public List<CommentBlogView> GetAllCmtBlog(Model model, @RequestParam int id) {
//		boolean check = false;
//		List<CommentBlogView> listBlog = null;
//
//		listBlog = CallingBlog_Sup.getInstance().GetAllCmtBlog(id);
//		if(listBlog != null) {
//			return listBlog;			
//		}else {
//			return null;
//		}
//	}

	
	
	//  29/11 Of Tran 
	
	//Test
	@GetMapping("/Supplier/test-blog-tran")
	public String test() {
		return "Supplier/Layout/PartialBlogDetail";
		}
	// loadPartial BlogDetail 2
	@GetMapping("/Supplier/blog-management/detailBlogSupp")
	public String detailBlogSupp(Model lstBlog, int id, int statusOfBlogDetail) {
//		System.out.println("data log status: "+statusOfBlogDetail);
		lstBlog.addAttribute("lstBlogDetail", ilaProjectCoffeeRepository.getInstance().CallDetailBlog(id));
		lstBlog.addAttribute("quantityCMT", ilaProjectCoffeeRepository.getInstance().CallCountCmt(id));
		lstBlog.addAttribute("idBlog",id);
		lstBlog.addAttribute("statusOfBlogDetail", statusOfBlogDetail);
		return "Supplier/Layout/partialBlogDetail";
	}
	//
	@GetMapping("/Supplier/blog-management/loadCommentBlog")
	public String loadCommentOfBlog(int idBlog ,Model model) {
		int idUser = 1; //4
		model.addAttribute("lsComments",ilaProjectCoffeeRepository.getInstance().userGetCommentBlogs(idBlog));
		model.addAttribute("crrUser",idUser);
	
		return "Supplier/Layout/PartialComment";//Supplier/Layout/PartialComment
	}
	
	@GetMapping("/Supplier/blog-management/addSubComment")
	public String addSubComment(int idBlog,int idReply,String content,Model model,int idMainB) {
		int idUser = 1;
		SubCommentBlogUS subC = new SubCommentBlogUS();
		subC.setIdAccount(idUser);
		subC.setIdBlog(idBlog);
		subC.setComment(content);
		subC.setIdReply(idReply);
		subC.setIdMainC(idMainB);
		subC.setUserType(1);
		subC = ilaProjectCoffeeRepository.getInstance().userInsertNewCommentBlogs(subC);
		//
		model.addAttribute("subC",subC);
		model.addAttribute("idMainB",idMainB);
		model.addAttribute("crrUser",idUser);
		return "/Supplier/Layout/PartialSubComment";
	}
	
	
	@GetMapping("/Supplier/blog-management/addMainComment")
	public String addMainComment(int idBlog,String content,Model model) {
		int idUser = 1;
		SubCommentBlogUS subC = new SubCommentBlogUS();
		subC.setIdBlog(idBlog);
		subC.setIdAccount(idUser);
		subC.setComment(content);
		subC.setUserType(1);
		subC = ilaProjectCoffeeRepository.getInstance().userInsertNewCommentBlogs(subC);
		model.addAttribute("subC",subC);
		return "/Supplier/Layout/PartialMainComment";
	}
	
	@GetMapping("/Supplier/blog-management/deleteComment")
	@ResponseBody
	public boolean userDeleteCommentBlog(int idBlog,int idComment) {
		return ilaProjectCoffeeRepository.getInstance().userDeleteCommentBlog(idBlog, idComment);
	}
	
	@GetMapping("/Supplier/blog-management/editComment")
	@ResponseBody
	public void userEditCommentBlog(int idCM,String content) {
		ilaProjectCoffeeRepository.getInstance().userEditCommentBlog(idCM, content);
	}
}
