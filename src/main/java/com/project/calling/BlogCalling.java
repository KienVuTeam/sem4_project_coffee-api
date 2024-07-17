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
import com.project.model.Category;
import com.project.model.CommentBlog;
import com.project.modelview.BlogView;
import com.project.modelview.CommentBlogView;
import com.project.modelview.MVNewBlog;
import com.project.utils.BaseUrl;

public class BlogCalling {
	private static BlogCalling instance = null;
	private RestTemplate restTemplate;

	public static BlogCalling getInstance() {
		if (instance == null) {
			instance = new BlogCalling();
		}
		return instance;
	}

	/**
	 * @author MaiTran
	 */

	public Boolean CallInsertBlog(Blog addBlog) {
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_ADDBLOG);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, addBlog, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallInsertBlog");
			return false;
		}
	}

	public Boolean CallEditBlog(Blog modelUpdate) {
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_UPDATEBLOGADMIN);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallEditBlog");
			return false;
		}
	}

	public List<Blog> CallAllBlog() {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<Blog>> res = restTemplate.exchange(BaseUrl.AD_ALLBLOG, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Blog>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallAllBlog");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<BlogView> CallAllBlogOfSupp() {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<BlogView>> res = restTemplate.exchange(BaseUrl.AD_ALLBLOGSUPP, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<BlogView>>() {
					});
			return res.getBody();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallAllBlogOfSupp");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Boolean CallUpdateBlogSupp(Blog modelUpdate) {
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_UPDATEBLOGSUPP);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, modelUpdate, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallUpdateBlogSupp");
			return false;
		}
	}

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

	public List<CommentBlogView> CallCmtMain(int idBlog) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<CommentBlogView>> res = restTemplate.exchange(
					BaseUrl.AD_CMTMAIN.concat("?idBlog=" + idBlog), HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CommentBlogView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallCmtMain");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<CommentBlogView> CallCmtSub(int idBlog) {
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<CommentBlogView>> res = restTemplate.exchange(
					BaseUrl.AD_CMTSUB.concat("?idBlog=" + idBlog), HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CommentBlogView>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR CallCmtSub");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Boolean CallInsertCMT(CommentBlog addCmt) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_INSERTCMT);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, addCmt, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallInsertCMT");
			return false;
		}
	}

	public Boolean CallUpdateCMT(CommentBlog updateCmt) {
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_UPDATECMT);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, updateCmt, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallUpdateCMT");
			return false;
		}
	}

	public Boolean CallDeleteCMT(CommentBlog deleteCmt) {
		try {
			restTemplate = new RestTemplate();
			URI uri = new URI(BaseUrl.AD_DELETECMT);
			ResponseEntity<Boolean> result = restTemplate.postForEntity(uri, deleteCmt, Boolean.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR CallDeleteCMT");
			return false;
		}
	}

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

	/**
	 * @author Kien
	 */
	// -------------------------------------------------------------------------
	// GetAllBlog
	public List<MVNewBlog> GetAllBlog(int userCreate) {
		List<MVNewBlog> lBlog = new ArrayList<>();
		// Bien nay can chinh sua lai - Login lay id cua user login
		// int userCreate = 1;
		String urlAPI = BaseUrl.Sup_GetAllBlog.replace("queryParam", String.valueOf(userCreate));
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<MVNewBlog>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<MVNewBlog>>() {
					});
			lBlog = resp.getBody();
			// model.addAttribute("lBlog", lBlog);
			if (resp.getStatusCode().is2xxSuccessful()) {
				System.out.println("getAllBlog success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Err: getAllBlog: " + e);
			System.out.println(e.getMessage());
		}
		//
		return lBlog;
	}

	// InsertNewBlog
	public boolean InsertNewBlog(Blog blog) {

		restTemplate = new RestTemplate();

		String urlAPI = BaseUrl.Sup_InsertNewBlog;
		try {

			// String UrlApi = "http://localhost:8085/Supplier/InsertBlog";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			// Create HttpEntity with the Book object and headers
			HttpEntity<Blog> requestEntity = new HttpEntity<>(blog, headers);

			// Send the POST request and receive the response
			ResponseEntity<String> response = restTemplate.postForEntity(urlAPI, requestEntity, String.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				// System.out.println("success");
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println("err insert blog: " + e);
		}

		return false;
	}

	// GetDetailBlog
	public Blog GetDetailBlog(int id) {

		// String urlAPI = "http://localhost:8085/Supplier/DetailBlog?id=" + id;
		String urlAPI = BaseUrl.Sup_GetDetailBlog.replace("param1", String.valueOf(id));
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<Blog> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<Blog>() {
					});

			if (resp.getStatusCode().is2xxSuccessful()) {
				System.out.println("success get blog detail");
				return resp.getBody();
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println("get detail blog failed");
		}

		return null;
	}

	// EditDetailBlog
	public Blog EditDetailBlog(int id) {
		String urlAPI = BaseUrl.Sup_EditDetailBlog.replace("param1", String.valueOf(id));
		Blog blog = new Blog();
		try {

			restTemplate = new RestTemplate();
			ResponseEntity<Blog> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<Blog>() {
					});

			if (resp.getStatusCode().is2xxSuccessful()) {
				return resp.getBody();
				// System.out.println("Get blog by id for edit: success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println("Get data for edit blog : failed: " + e);
		}

		return null;
	}

	// UpdateBlog
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
			System.out.println(e.getMessage());
			System.out.println("err update database: " + e);
		}

		return false;
	}

	// GetAllCmtBlog
	public List<CommentBlogView> GetAllCmtBlog(int id) {

		String urlAPI = BaseUrl.Sup_GetAllCmtBlog.replace("param1", String.valueOf(id));
		try {
			restTemplate = new RestTemplate();
			ResponseEntity<List<CommentBlogView>> resp = restTemplate.exchange(urlAPI, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CommentBlogView>>() {
					});

			if (resp.getStatusCode().is2xxSuccessful()) {
				// listBlog = resp.getBody();
				return resp.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println("Err for getAll cmtBlog: " + e);
		}

		return null;
	}
	
	
	/**
	 * @author Vinh
	 */
	public BlogView userGetBlogDetails(int idBlog) {
		BlogView userBlog = new BlogView();
		try {
			restTemplate = new RestTemplate();	
			ResponseEntity<BlogView> responseEntity = restTemplate.exchange(BaseUrl.US_BLOGDETAILS.replace("queryParam", String.valueOf(idBlog)),
					HttpMethod.GET, null, new ParameterizedTypeReference<BlogView>() {
					});
			userBlog = responseEntity.getBody();	
		} catch (Exception e) {
			System.out.println("ERROR userGetBlogDetails");
			System.out.println(e.getMessage());
		}
		return userBlog;
	}
	
	public List<Blog> userGetRandomBlog(int amountTake,int idRBlog){
		List<Blog> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();	
			String urlCalling = BaseUrl.US_RANDOMBLOGS.replace("queryIdR", String.valueOf(idRBlog)).replace("queryTake",String.valueOf(amountTake)) ;
			ResponseEntity<List<Blog>> responseEntity = restTemplate.exchange(urlCalling, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Blog>>() {
					});
			ls = responseEntity.getBody();	
		} catch (Exception e) {
			System.out.println("ERROR userGetRandomBlog");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	public List<BlogView> userGetAllBlogs(){
		List<BlogView> ls = new ArrayList<>();
		try {
			restTemplate = new RestTemplate();	
			ResponseEntity<List<BlogView>> responseEntity = restTemplate.exchange(BaseUrl.US_GETALLBLOGS, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<BlogView>>() {
					});
			ls = responseEntity.getBody();	
		} catch (Exception e) {
			System.out.println("ERROR userGetAllBlogs");
			System.out.println(e.getMessage());
		}
		return ls;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
