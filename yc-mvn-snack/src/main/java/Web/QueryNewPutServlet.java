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
 * Servlet implementation class QueryNewPutServlet
 */
@WebServlet("/QueryNewPut.do")
public class QueryNewPutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductDao pdao=new ProductDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Map<String,Object>> newlist=pdao.queryNewPut();
		response.getWriter().append(new Gson().toJson(newlist));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
