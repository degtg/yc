package com.yc.damai.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.damai.util.DBHelper;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/product.do")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String sql="select * from dm_product where is_hot=1 limit 0,10";
		List<?>list=new DBHelper().query(sql);
		HashMap<String,Object>page=new HashMap<>();
		page.put("list", list);
		print(response,page);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
