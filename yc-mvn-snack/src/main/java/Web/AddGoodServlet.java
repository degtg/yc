package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.ProductDao;


@WebServlet("/AddGoodServlet.do")
public class AddGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao pdao=new ProductDao(); 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	String gname=request.getParameter("gname");
	String tno=request.getParameter("tno");
	String price=request.getParameter("price");
	String intro=request.getParameter("intro");
	String balance=request.getParameter("balance");
	String pics=request.getParameter("pics");
	String unit=request.getParameter("unit");
	String qperied=request.getParameter("qperied");
	String weight=request.getParameter("weight");
	String descr=request.getParameter("descr");
	
	//response.getWriter().print(new Gson().toJson(pdao.getGTypeName()));
	//pdao.insert(gname, tno, price, intro, balance, pics, unit, qperied, weight, descr);
	if(tno==null|| tno.trim().isEmpty()) {
		response.getWriter().append("请填写商品类型编号 ！");
	}else if(gname==null|| gname.trim().isEmpty()) {
		response.getWriter().append("请填写商品名！");
	}else if(price==null|| price.trim().isEmpty()) {
		response.getWriter().append("请填写商品价格！");
	}else if(balance==null|| balance.trim().isEmpty()) {
		response.getWriter().append("请填写库存！");
	}else if(unit==null|| unit.trim().isEmpty()) {
		response.getWriter().append("请填写商品单位！");
	}else if(qperied==null|| qperied.trim().isEmpty()) {
		response.getWriter().append("请填写保质期！");
	}else if(weight==null|| weight.trim().isEmpty()) {
		response.getWriter().append("请填写商品净重！");
	}else if(intro==null|| intro.trim().isEmpty()) {
		response.getWriter().append("请填写商品简介！");
	}else if(descr==null|| descr.trim().isEmpty()) {
		response.getWriter().append("请填写商品描述！");
	}else {
		pdao.insert(gname, tno, price, intro, balance, pics, unit, qperied, weight, descr,0);
		response.getWriter().append("添加成功！");
		
		
	}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
