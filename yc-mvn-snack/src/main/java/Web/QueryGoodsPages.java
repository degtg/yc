package Web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.ProductDao;




@WebServlet("/QueryGoodsPages.do")
public class QueryGoodsPages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao pdao=new ProductDao();    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if("gpage".equals(op)) {
			doPage(request,response);
			
		}else if("gpages".equals(op)) {
			doPages(request,response);
		}
	}

	/**
	 * 根据类型名和商品名分页查询
	 * @param request 
	 * @param response
	 */
	private void doPages(HttpServletRequest request, HttpServletResponse response) {
		String tname=request.getParameter("tname");
		String gname=request.getParameter("gname");
		System.out.println(tname);
		System.out.println(gname);
		String page=request.getParameter("page");
		int iPage=1;
		if(page!=null&&page.trim().isEmpty()==false) {
		iPage=Integer.valueOf(page);
		}
		List<Map<String,Object>>list=new ProductDao().queryPage(iPage, 5,tname,gname);
		int pages=pdao.countPages(5,tname,gname);
		Map<String,Object>data=new HashMap<String,Object>();
		data.put("list",list);
		data.put("pages",pages);
		Gson gson=new Gson();
		String json=gson.toJson(data);
		try {
			response.getWriter().append(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 分页查询
	 * @param request
	 * @param response
	 */
	private void doPage(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		int iPage=1;
		if(page!=null&&page.trim().isEmpty()==false) {
		iPage=Integer.valueOf(page);
		}
		List<Map<String,Object>>list=new ProductDao().queryPage(iPage, 5);
		int pages=pdao.countPages(5);
		Map<String,Object>data=new HashMap<String,Object>();
		data.put("list",list);
		data.put("pages",pages);
		Gson gson=new Gson();
		String json=gson.toJson(data);
		try {
			response.getWriter().append(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
