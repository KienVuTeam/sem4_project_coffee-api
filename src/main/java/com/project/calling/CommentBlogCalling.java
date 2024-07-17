package com.project.calling;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.Category;
import com.project.model.CommentBlogUS;
import com.project.model.ObjectMessage;
import com.project.model.SubCommentBlogUS;
import com.project.utils.BaseUrl;

public class CommentBlogCalling {
	private static CommentBlogCalling instance = null;
	private RestTemplate restTemplate;
	
	public static CommentBlogCalling getInstance() {
		if (instance == null) {
			instance = new CommentBlogCalling();
		}
		return instance;
	}
	
	/**
	 * @author Vinh
	 */
	
	public List<CommentBlogUS> userGetCommentBlogs(int idBlog){
		List<CommentBlogUS> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();	
			ResponseEntity<List<CommentBlogUS>> responseEntity = restTemplate.exchange(BaseUrl.US_CMTBLOGS.replace("queryParam", String.valueOf(idBlog)),
					HttpMethod.GET, null, new ParameterizedTypeReference<List<CommentBlogUS>>() {
					});
			ls = responseEntity.getBody();	
		} catch (Exception e) {
			System.out.println("ERROR userGetCommentBlogs");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	public SubCommentBlogUS userInsertNewCommentBlogs(SubCommentBlogUS modelAdd) {
		SubCommentBlogUS cmReturn = new SubCommentBlogUS();
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.US_ADDCMTBLOG);										
			ResponseEntity<SubCommentBlogUS> result = restTemplate.postForEntity(uri, modelAdd, SubCommentBlogUS.class);		
			cmReturn = result.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userInsertNewCommentBlogs");
			System.out.println(e.getMessage());
		}
		return cmReturn;
	}
	
	public boolean userDeleteCommentBlog(int idBlog,int idComment) {
		boolean flagDelete = true;
		try {
			restTemplate = new RestTemplate();	
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(BaseUrl.US_DELCMTBLOG.replace("paramIdBlog", String.valueOf(idBlog)).replace("paramIdCMT", String.valueOf(idComment)),
					HttpMethod.GET, null, new ParameterizedTypeReference<Boolean>() {
					});
			flagDelete = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userDeleteCommentBlog");
			System.out.println(e.getMessage());
		}
		return flagDelete;
	}
	
	public boolean userEditCommentBlog(int idCM,String content) {
		boolean flagEdit = true;
		try {
			restTemplate = new RestTemplate();	
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(BaseUrl.US_EDITCMTBLOG.replace("paramQueryID", String.valueOf(idCM)).replace("paramContent", content),
					HttpMethod.GET, null, new ParameterizedTypeReference<Boolean>() {
					});
			flagEdit = responseEntity.getBody();
		} catch (Exception e) {
			System.out.println("ERROR userEditCommentBlog");
			System.out.println(e.getMessage());
		}
		return flagEdit;
	}
	
	
}
