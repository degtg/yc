package Web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.ProductDao;

/**
 * Servlet implementation class QueryCheapServlet
 */
@WebServlet("/QueryCheap.do")
public class QueryCheapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ProductDao pdao=new ProductDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String ,Object>> listcheap=pdao.queryCheaper();
		response.getWriter().print(new Gson().toJson(listcheap));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
