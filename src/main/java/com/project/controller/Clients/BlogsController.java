package com.project.controller.Clients;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.SubCommentBlogUS;
import com.project.model.menuUsers;
import com.project.modelview.BlogView;
import com.project.others.CheckCookieUsers;
import com.project.repository.ilaProjectCoffeeRepository;
import com.project.utils.StringValue;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Blogs")
public class BlogsController {
	
	@GetMapping("/")
	public String userAllBlogs(HttpServletRequest request ,Model model) {
		menuUsers mU = new menuUsers();
		mU.setBlogPage(true);
		mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
		//
		model.addAttribute("menuUsers", mU);
		model.addAttribute("lsBlogs", ilaProjectCoffeeRepository.getInstance().userGetAllBlogs());
		return "/Users/UserListBlogs";
	}
	
	@GetMapping("/BlogDetails")
	public String viewBlogDetails(HttpServletRequest request,int idBlog,Model model) {
		BlogView userViewB = ilaProjectCoffeeRepository.getInstance().userGetBlogDetails(idBlog);
		if (userViewB.getId() == 0) {
			return "redirect:/Blogs/";
		}
		menuUsers mU = new menuUsers();
		mU.setBlogPage(true);
		mU.setFlagLogin(CheckCookieUsers.getInstance().checkCookieExists(request, StringValue.nameCookieUser));
		//
		model.addAttribute("menuUsers", mU);
		model.addAttribute("viewBlog",userViewB);
		model.addAttribute("lsBlogR",ilaProjectCoffeeRepository.getInstance().userGetRandomBlog(5, idBlog));
		return "/Users/UsersBlog";
	}
	
	@GetMapping("/ajax/loadCommentBlog")
	public String loadCommentOfBlog(@CookieValue(value = StringValue.nameCookieUser, defaultValue = "0") int idUsers ,int idBlog,Model model) {
		model.addAttribute("lsComments",ilaProjectCoffeeRepository.getInstance().userGetCommentBlogs(idBlog));
		model.addAttribute("crrUser", idUsers);
		return "/AjaxView/UserComments";
	}
	
	@GetMapping("/ajax/addSubComment")
	public String addSubComment(@CookieValue(value = StringValue.nameCookieUser, defaultValue = "0") int idUsers ,int idBlog,int idReply,String content,Model model,int idMainB) {	
		SubCommentBlogUS subC = new SubCommentBlogUS();
		subC.setIdAccount(idUsers);
		subC.setIdBlog(idBlog);
		subC.setComment(content);
		subC.setIdReply(idReply);
		subC.setIdMainC(idMainB);
		subC.setUserType(2);
		subC = ilaProjectCoffeeRepository.getInstance().userInsertNewCommentBlogs(subC);
		//
		model.addAttribute("subC",subC);
		model.addAttribute("idMainB",idMainB);
		model.addAttribute("crrUser",idUsers);
		return "/AjaxView/subComment";
	}
	
	@GetMapping("/ajax/addMainComment")
	public String addMainComment(@CookieValue(value = StringValue.nameCookieUser, defaultValue = "0") int idUsers ,int idBlog,String content,Model model) {
		SubCommentBlogUS subC = new SubCommentBlogUS();
		subC.setIdBlog(idBlog);
		subC.setIdAccount(idUsers);
		subC.setComment(content);
		subC.setUserType(2);
		subC = ilaProjectCoffeeRepository.getInstance().userInsertNewCommentBlogs(subC);
		//
		model.addAttribute("subC",subC);
		return "/AjaxView/mainComment";
	}
	
	@GetMapping("/ajax/deleteComment")
	@ResponseBody
	public boolean userDeleteCommentBlog(int idBlog,int idComment) {
		return ilaProjectCoffeeRepository.getInstance().userDeleteCommentBlog(idBlog, idComment);
	}
	
	@GetMapping("/ajax/editComment")
	@ResponseBody
	public void userEditCommentBlog(int idCM,String content) {
		ilaProjectCoffeeRepository.getInstance().userEditCommentBlog(idCM, content);
	}
}
