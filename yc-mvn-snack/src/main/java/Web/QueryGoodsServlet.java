package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.ProductDao;

/**
 * Servlet implementation class QueryGoodsServlet
 */
@WebServlet("/QueryGoodsServlet.do")
public class QueryGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao pdao=new ProductDao(); 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gname=request.getParameter("gname");
		String tname=request.getParameter("tname");
		Gson gson=new Gson();
		String json=gson.toJson(pdao.queryAll1(tname, gname));
		response.getWriter().print(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
