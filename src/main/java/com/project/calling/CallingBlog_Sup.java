package com.project.calling;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.model.Blog;
import com.project.model.CommentBlog;
import com.project.model.CommentBlogUS;
import com.project.model.InvoiceSupplier;
import com.project.model.SubCommentBlogUS;
import com.project.modelview.CommentBlogView;
import com.project.modelview.MVNewBlog;
import com.project.utils.BaseUrl;

public class CallingBlog_Sup {

	RestTemplate restTemplate;
	private static CallingBlog_Sup instance = null;

	private CallingBlog_Sup() {
	}

	public static CallingBlog_Sup getInstance() {

		if (instance == null) {
			instance = new CallingBlog_Sup();
		}
		return instance;
	}
	//-------------------------------------------------------------------------
	//GetAllBlog
	public List<MVNewBlog> GetAllBlog(int userCreate){
		List<MVNewBlog> lBlog = new ArrayList<>();
		// Bien nay can chinh sua lai - Login lay id cua user login
		//int userCreate = 1;
		String urlAPI = BaseUrl.Sup_GetAllBlog.replace("param1", String.valueOf(userCreate) );
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<MVNewBlog>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<MVNewBlog>>() {
					});
			lBlog = resp.getBody();
			//model.addAttribute("lBlog", lBlog);
			if (resp.getStatusCode().is2xxSuccessful()) {
				System.out.println("getAllBlog success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Err: getAllBlog: " + e);
		}
		//
		return lBlog;
	}
	//InsertNewBlog
	public boolean InsertNewBlog(Blog blog) {
		
		restTemplate = new RestTemplate();

		String urlAPI =BaseUrl.Sup_InsertNewBlog;
		try {

			//String UrlApi = "http://localhost:8085/Supplier/InsertBlog";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			// Create HttpEntity with the Book object and headers
			HttpEntity<Blog> requestEntity = new HttpEntity<>(blog, headers);

			// Send the POST request and receive the response
			ResponseEntity<String> response = restTemplate.postForEntity(urlAPI, requestEntity, String.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				//System.out.println("success");
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("err insert blog: " + e);
		}

		return false;
	}
	//GetDetailBlog
	public Blog GetDetailBlog(int id) {
		
		//String urlAPI = "http://localhost:8085/Supplier/DetailBlog?id=" + id;
		String urlAPI =BaseUrl.Sup_GetDetailBlog.replace("param1", String.valueOf(id) );
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Blog> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<Blog>() {
					});

			if (resp.getStatusCode().is2xxSuccessful()) {
				System.out.println("success get blog detail");
				return  resp.getBody();
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("get detail blog failed");
		}

		return null;
	}
	//EditDetailBlog
	public Blog EditDetailBlog(int id) {
		String urlAPI =BaseUrl.Sup_EditDetailBlog.replace("param1", String.valueOf(id) );
		Blog blog = new Blog();
		try {

			restTemplate = new RestTemplate();
			ResponseEntity<Blog> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<Blog>() {
					});

			if (resp.getStatusCode().is2xxSuccessful()) {
				return  resp.getBody();
				//System.out.println("Get blog by id for edit: success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Get data for edit blog : failed: " + e);
		}
		
		return null;
	}
	//UpdateBlog
	public boolean UpdateDetailBlog(Blog blog, int id) {
		String urlAPI = BaseUrl.Sup_UpdateDetailBlog;
		try {
			restTemplate = new RestTemplate();
			// Send the POST request and receive the response
			ResponseEntity<String> response = restTemplate.postForEntity(urlAPI, blog, String.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				System.out.println("success");
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("err update database: " + e);
		}
		
		return false;
	}
	//GetAllCmtBlog
	public List<CommentBlogView> GetAllCmtBlog(int id){
		
		String urlAPI =BaseUrl.Sup_GetAllCmtBlog.replace("param1", String.valueOf(id) );
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<CommentBlogView>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CommentBlogView>>() {
					});

			if (resp.getStatusCode().is2xxSuccessful()) {
				//listBlog = resp.getBody();
				return resp.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Err for getAll cmtBlog: "+e);
		}
		
		return null;
	}
	
	
	
	//New 29/11  Tran -------------------------------------------------------
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
	
	//new 07/12
	public Blog CallDetailBlog(int id) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Blog> res = restTemplate.exchange(BaseUrl.AD_DETAILBLOG.concat("?idBlog=" + id),
					HttpMethod.GET, null, new ParameterizedTypeReference<Blog>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallDetailBlog");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	//
	public Integer CallCountCmt(int idBlog) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Integer> res = restTemplate.exchange(BaseUrl.AD_COUNTCMT.concat("?idBlog=" + idBlog),
					HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallCountCmt");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
}
