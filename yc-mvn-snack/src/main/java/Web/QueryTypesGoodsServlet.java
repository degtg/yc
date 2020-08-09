package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.ProductDao;
import com.snack.dao.TypeDao;

/**
 * Servlet implementation class QueryTypesGoodsServlet
 */
@WebServlet("/QueryTypesGoods.do")
public class QueryTypesGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private TypeDao tdao=new TypeDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String tno=request.getParameter("tno");
		String tname=request.getParameter("tname");
		response.getWriter().print(new Gson().toJson(tdao.selectAll()));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
