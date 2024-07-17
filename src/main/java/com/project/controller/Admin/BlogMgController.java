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

import com.project.model.Account;
import com.project.model.Blog;
import com.project.model.CommentBlog;
import com.project.model.SubCommentBlogUS;
import com.project.modelview.CommentBlogView;
import com.project.repository.ilaProjectCoffeeRepository;

@Controller
@RequestMapping("/Admin")
public class BlogMgController {
	@GetMapping("/blog-management")
	public String blog(Model lstBlog) {
		lstBlog.addAttribute("lstBlog", ilaProjectCoffeeRepository.getInstance().CallAllBlog());
		lstBlog.addAttribute("lstBlogSupp", ilaProjectCoffeeRepository.getInstance().CallAllBlogOfSupp());
		return "Admin/blogMg";
	}

	@PostMapping("/blog-management/insertBlog")
	public String insertBlog(@ModelAttribute Blog modelBlog, Model lstBlog) {
		long millis = System.currentTimeMillis();
		Date current = new Date(millis);
		modelBlog.setUserCreate(0);
		modelBlog.setCreateDate(current);
		modelBlog.setIsStatus(1);
		//insert Blog
		ilaProjectCoffeeRepository.getInstance().CallInsertBlog(modelBlog);
		lstBlog.addAttribute("lstBlog", ilaProjectCoffeeRepository.getInstance().CallAllBlog());
		return "Admin/Share/partialBlogMg";
	}

	@PostMapping("/blog-management/editBlog")
	public String editBlog(@ModelAttribute Blog modelBlog, Model lstBlog) {

		modelBlog.setUserCreate(0);
		modelBlog.setIsStatus(1);
		//update Blog
		ilaProjectCoffeeRepository.getInstance().CallEditBlog(modelBlog);
		lstBlog.addAttribute("lstBlog",ilaProjectCoffeeRepository.getInstance().CallAllBlog());
		return "Admin/Share/partialBlogMg";
	}

	@GetMapping("/blog-management/detailBlog")
	public String detailBlog(Model lstBlog,@RequestParam int id, int sttBlog) {
		lstBlog.addAttribute("lstBlogDetail", ilaProjectCoffeeRepository.getInstance().CallDetailBlog(id));
		lstBlog.addAttribute("quantityCMT", ilaProjectCoffeeRepository.getInstance().CallCountCmt(id));
		lstBlog.addAttribute("idBlog",id);
		lstBlog.addAttribute("sttBlog",sttBlog);
		return "Admin/Share/partialBlogDetail";
	}
	
	@GetMapping("/blog-management/detailBlogSupp")
	public String detailBlogSupp(Model lstBlog, @RequestParam int id, int sttBlog) {
		lstBlog.addAttribute("lstBlogDetail", ilaProjectCoffeeRepository.getInstance().CallDetailBlog(id));
		lstBlog.addAttribute("quantityCMT", ilaProjectCoffeeRepository.getInstance().CallCountCmt(id));
		lstBlog.addAttribute("idBlog",id);
		lstBlog.addAttribute("sttBlog",sttBlog);
		return "Admin/Share/partialBlogDetailSupp";
	}
	
	@PostMapping("/blog-management/inactiveBlogAdmin")
	public String inactiveBlogAdmin(@ModelAttribute Blog modelBlog,Model lstBlog) {
		
		ilaProjectCoffeeRepository.getInstance().CallUpdateBlogSupp(modelBlog);
		
		lstBlog.addAttribute("lstBlog", ilaProjectCoffeeRepository.getInstance().CallAllBlog());
		return "Admin/Share/partialBlogMg";
	}
	
	@PostMapping("/blog-management/updateBlogSupp")
	public String updateBlogSupp(@ModelAttribute Blog modelBlog,Model lstBlog) {
		
		ilaProjectCoffeeRepository.getInstance().CallUpdateBlogSupp(modelBlog);
		lstBlog.addAttribute("lstBlogSupp", ilaProjectCoffeeRepository.getInstance().CallAllBlogOfSupp());
		return "Admin/Share/partialBlogMgTabSupp";
	}
	// new
	@GetMapping("/blog-management/loadCommentBlog")
	public String loadCommentOfBlog(int idBlog,Model model) {
		int idUser = 0;
		model.addAttribute("lsComments",ilaProjectCoffeeRepository.getInstance().userGetCommentBlogs(idBlog));
		model.addAttribute("crrUser",idUser);
		return "/Admin/Share/partialComments";
	}
	
	@GetMapping("/blog-management/addSubComment")
	public String addSubComment(int idBlog,int idReply,String content,Model model,int idMainB) {
		int idUser = 0;
		SubCommentBlogUS subC = new SubCommentBlogUS();
		subC.setIdAccount(idUser);
		subC.setIdBlog(idBlog);
		subC.setComment(content);
		subC.setIdReply(idReply);
		subC.setIdMainC(idMainB);
		subC.setUserType(0);
		subC = ilaProjectCoffeeRepository.getInstance().userInsertNewCommentBlogs(subC);
		//
		model.addAttribute("subC",subC);
		model.addAttribute("idMainB",idMainB);
		model.addAttribute("crrUser",idUser);
		return "/Admin/Share/partialSubComment";
	}
	
	@GetMapping("/blog-management/addMainComment")
	public String addMainComment(int idBlog,String content,Model model) {
		int idUser = 0;
		SubCommentBlogUS subC = new SubCommentBlogUS();
		subC.setIdBlog(idBlog);
		subC.setIdAccount(idUser);
		subC.setComment(content);
		subC.setUserType(0);
		subC = ilaProjectCoffeeRepository.getInstance().userInsertNewCommentBlogs(subC);
		//
		model.addAttribute("subC",subC);
		return "/Admin/Share/partialMainComment";
	}
	
	@GetMapping("/blog-management/deleteComment")
	@ResponseBody
	public boolean userDeleteCommentBlog(int idBlog,int idComment) {
		return ilaProjectCoffeeRepository.getInstance().userDeleteCommentBlog(idBlog, idComment);
	}
	
	@GetMapping("/blog-management/editComment")
	@ResponseBody
	public void userEditCommentBlog(int idCM,String content) {
		ilaProjectCoffeeRepository.getInstance().userEditCommentBlog(idCM, content);
	}
	//================= old
	@PostMapping("/blog-management/insertCmtTabSupp")
	public String insertCmtTabSupp(@ModelAttribute CommentBlog modelBlog, int idCMT,Model lstBlog) {
		long millis = System.currentTimeMillis();
		Date current = new Date(millis);
		var lstMain = ilaProjectCoffeeRepository.getInstance().CallCmtMain(modelBlog.getIdBlog());
		var lstSub = ilaProjectCoffeeRepository.getInstance().CallCmtSub(modelBlog.getIdBlog());

		modelBlog.setIdAccount("iLA Website"); 
		modelBlog.setDateCreate(current);
		if (idCMT == 0) {
			modelBlog.setIndC(0);
		} else {
			modelBlog.setIndC(Admin_getIndCMax(modelBlog.getIdBlog(), lstMain, lstSub, idCMT) + 1);
		}
		modelBlog.setMnC(idCMT);
		modelBlog.setStatus(0);
		ilaProjectCoffeeRepository.getInstance().CallInsertCMT(modelBlog);
		//
		//
		var lstMain1 = ilaProjectCoffeeRepository.getInstance().CallCmtMain(modelBlog.getIdBlog());
		var lstSub1 = ilaProjectCoffeeRepository.getInstance().CallCmtSub(modelBlog.getIdBlog());
		lstBlog.addAttribute("lstBlogDetail", ilaProjectCoffeeRepository.getInstance().CallDetailBlog(modelBlog.getIdBlog()));
		lstBlog.addAttribute("quantityCMT", ilaProjectCoffeeRepository.getInstance().CallCountCmt(modelBlog.getIdBlog()));
		if(lstMain1!=null&&lstSub1!=null) {
			var detailCmtBlog = Admin_getCommentBlogDetail(modelBlog.getIdBlog(), lstMain1, lstSub1);
			lstBlog.addAttribute("lstCmt", Admin_getCommentBlogDetail(modelBlog.getIdBlog(), lstMain1, lstSub1));
			lstBlog.addAttribute("lstSub", lstSub1);
		}
		return "Admin/Share/partialBlogDetailSupp";
	}
	
	@PostMapping("/blog-management/editCmtTabSupp")
	public String editCmtTabSupp(@ModelAttribute CommentBlog modelBlog,@RequestParam String commentEdit,Model lstBlog) {
		modelBlog.setComment(commentEdit);
		ilaProjectCoffeeRepository.getInstance().CallUpdateCMT(modelBlog);
		//
		//
		var lstMain1 = ilaProjectCoffeeRepository.getInstance().CallCmtMain(modelBlog.getIdBlog());
		var lstSub1 = ilaProjectCoffeeRepository.getInstance().CallCmtSub(modelBlog.getIdBlog());
		lstBlog.addAttribute("lstBlogDetail", ilaProjectCoffeeRepository.getInstance().CallDetailBlog(modelBlog.getIdBlog()));
		lstBlog.addAttribute("quantityCMT", ilaProjectCoffeeRepository.getInstance().CallCountCmt(modelBlog.getIdBlog()));
		if(lstMain1!=null&&lstSub1!=null) {
			var detailCmtBlog = Admin_getCommentBlogDetail(modelBlog.getIdBlog(), lstMain1, lstSub1);
			lstBlog.addAttribute("lstCmt", Admin_getCommentBlogDetail(modelBlog.getIdBlog(), lstMain1, lstSub1));
			lstBlog.addAttribute("lstSub", lstSub1);
		}
		return "Admin/Share/partialBlogDetailSupp";
	}
	
	@PostMapping("/blog-management/deleteCmtTabSupp")
	public String deleteCmtTabSupp(@ModelAttribute CommentBlog modelBlog,Model lstBlog) {
		ilaProjectCoffeeRepository.getInstance().CallDeleteCMT(modelBlog);
		//
		var lstMain1 = ilaProjectCoffeeRepository.getInstance().CallCmtMain(modelBlog.getIdBlog());
		var lstSub1 = ilaProjectCoffeeRepository.getInstance().CallCmtSub(modelBlog.getIdBlog());
		lstBlog.addAttribute("lstBlogDetail", ilaProjectCoffeeRepository.getInstance().CallDetailBlog(modelBlog.getIdBlog()));
		lstBlog.addAttribute("quantityCMT", ilaProjectCoffeeRepository.getInstance().CallCountCmt(modelBlog.getIdBlog()));
		if(lstMain1!=null&&lstSub1!=null) {
			var detailCmtBlog = Admin_getCommentBlogDetail(modelBlog.getIdBlog(), lstMain1, lstSub1);
			lstBlog.addAttribute("lstCmt", Admin_getCommentBlogDetail(modelBlog.getIdBlog(), lstMain1, lstSub1));
			lstBlog.addAttribute("lstSub", lstSub1);
		}
		return "Admin/Share/partialBlogDetailSupp";
	}
	
	public int Admin_getIndCMax(int idBlog, List<CommentBlogView> cmtMain, List<CommentBlogView> cmtSub, int idCMT) {
		int indCMax = 0;
		//
		try {
			List<CommentBlogView> lsMain = cmtMain;
			List<CommentBlogView> lsSub = cmtSub;
			//
			for (int i = 0; i < lsMain.size(); i++) {
				List<CommentBlogView> lsInsert = new ArrayList<>();
				//
				CommentBlogView insertCmtBlog = new CommentBlogView();
				insertCmtBlog.setId(lsMain.get(i).getId());
				insertCmtBlog.setIndC(lsMain.get(i).getIndC());
				lsInsert.add(insertCmtBlog);
				for (int j = 0; j < lsSub.size(); j++) {
					CommentBlogView insertTemp = new CommentBlogView();
					if (lsSub.get(j).getMnC() == lsMain.get(i).getId()) {
						insertTemp.setId(lsSub.get(j).getId());
						insertTemp.setIndC(lsSub.get(j).getIndC());
						lsInsert.add(insertTemp);
					} else {
						for (int x = 0; x < lsInsert.size(); x++) {
							if (lsSub.get(j).getMnC() == lsInsert.get(x).getId()) {
								insertTemp.setId(lsSub.get(j).getId());
								insertTemp.setIndC(lsSub.get(j).getIndC());
								lsInsert.add(insertTemp);
							}
						}
					}
				}
				//
				for (int jTemp = 0; jTemp < lsInsert.size(); jTemp++) {
					if (idCMT == lsInsert.get(jTemp).getId()) {
						for (int j = 0; j < lsInsert.size(); j++) {
							if (indCMax < lsInsert.get(j).getIndC()) {
								indCMax = lsInsert.get(j).getIndC();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR Admin_getCommentBlogDetail");
			System.out.println(e.getMessage());
		}
		System.out.println("max: " + indCMax);
		return indCMax;
	}

	public List<CommentBlogView> Admin_getCommentBlogDetail(int idBlog, List<CommentBlogView> cmtMain,
			List<CommentBlogView> cmtSub) {
		List<CommentBlogView> ls = new ArrayList<>();
		try {
			List<CommentBlogView> lsMain = cmtMain;
			List<CommentBlogView> lsSub = cmtSub;
			//
			for (int i = 0; i < lsMain.size(); i++) {
				List<CommentBlogView> lsInsert = new ArrayList<>();
				//
				CommentBlogView insertCmtBlog = new CommentBlogView();
				insertCmtBlog.setId(lsMain.get(i).getId());
				insertCmtBlog.setIdBlog(lsMain.get(i).getIdBlog());
				insertCmtBlog.setIdAccount(lsMain.get(i).getIdAccount());
				insertCmtBlog.setComment(lsMain.get(i).getComment());
				List<Account> lsAccount = ilaProjectCoffeeRepository.getInstance().CallAllAccount();
				for (int k = 0; k < lsAccount.size(); k++) {
					if (insertCmtBlog.getIdAccount().equals(String.valueOf(lsAccount.get(k).getId()))) {
						insertCmtBlog.setUserName(lsAccount.get(k).getUsername());
						break;
					} else {
						insertCmtBlog.setUserName(lsMain.get(i).getIdAccount());
					}
				}
				insertCmtBlog.setDateCreate(lsMain.get(i).getDateCreate());
				insertCmtBlog.setMnC(lsMain.get(i).getMnC());
				for (int j = 0; j < lsSub.size(); j++) {
					CommentBlogView insertTemp = new CommentBlogView();
					if (lsSub.get(j).getMnC() == lsMain.get(i).getId()) {
						insertTemp.setId(lsSub.get(j).getId());
						insertTemp.setIdBlog(lsSub.get(j).getIdBlog());
						insertTemp.setIdAccount(lsSub.get(j).getIdAccount()); // note idAccount la varchar
						for (int k = 0; k < lsAccount.size(); k++) {
							if (insertTemp.getIdAccount().equals(String.valueOf(lsAccount.get(k).getId()))) {
								insertTemp.setUserName(lsAccount.get(k).getUsername());
								break;
							} else {
								insertTemp.setUserName(lsSub.get(j).getIdAccount());
							}
						}
						for (int k = 0; k < lsAccount.size(); k++) {
							if (lsMain.get(i).getIdAccount().equals(String.valueOf(lsAccount.get(k).getId()))) {
								insertTemp.setUserNameRep(lsAccount.get(k).getUsername());
								break;
							} else {
								insertTemp.setUserNameRep(lsMain.get(i).getIdAccount());
							}
						}
						insertTemp.setComment(lsSub.get(j).getComment());
						insertTemp.setDateCreate(lsSub.get(j).getDateCreate());
						insertTemp.setMnC(lsSub.get(j).getMnC());
						lsInsert.add(insertTemp);
					} else {
						for (int x = 0; x < lsInsert.size(); x++) {
							if (lsSub.get(j).getMnC() == lsInsert.get(x).getId()) {
								insertTemp.setId(lsSub.get(j).getId());
								insertTemp.setIdBlog(lsSub.get(j).getIdBlog());
								insertTemp.setIdAccount(lsSub.get(j).getIdAccount());
								for (int k = 0; k < lsAccount.size(); k++) {
									if (insertTemp.getIdAccount().equals(String.valueOf(lsAccount.get(k).getId()))) {
										insertTemp.setUserName(lsAccount.get(k).getUsername());
										break;
									} else {
										insertTemp.setUserName(lsSub.get(j).getIdAccount());
									}
								}
								for (int k = 0; k < lsAccount.size(); k++) {
									if (lsInsert.get(x).getIdAccount()
											.equals(String.valueOf(lsAccount.get(k).getId()))) {
										insertTemp.setUserNameRep(lsAccount.get(k).getUsername());
										break;
									} else {
										insertTemp.setUserNameRep(lsInsert.get(x).getIdAccount());
									}
								}
								insertTemp.setComment(lsSub.get(j).getComment());
								insertTemp.setDateCreate(lsSub.get(j).getDateCreate());
								insertTemp.setMnC(lsSub.get(j).getMnC());
								lsInsert.add(insertTemp);
							}
						}
					}
				}
				//
				insertCmtBlog.subComment = lsInsert;
				ls.add(insertCmtBlog);
			}
		} catch (Exception e) {
			System.out.println("ERROR Admin_getCommentBlogDetail");
			System.out.println(e.getMessage());
		}
		return ls;
	}

}
