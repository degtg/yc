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
import com.snack.dao.TypeDao;

/**
 * Servlet implementation class QueryTypeServlet
 */
@WebServlet("/QueryTypes.do")
public class QueryTypeServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TypeDao tdao=new TypeDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Map<String,Object>> typelist=tdao.queryType();
		response.getWriter().print(new Gson().toJson(typelist));
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
