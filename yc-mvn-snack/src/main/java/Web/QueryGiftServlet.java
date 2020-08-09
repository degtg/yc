package Web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.ProductDao;

/**
 * Servlet implementation class QueryGiftServlet
 */
@WebServlet("/QueryGift.do")
public class QueryGiftServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
	ProductDao pdao=new ProductDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String ,Object>> listgift=pdao.queryGift();
		response.getWriter().print(new Gson().toJson(listgift));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
