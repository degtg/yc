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
import com.snack.dao.OrderitemDao;
import com.snack.dao.OrdersDao;

/**
 * Servlet implementation class QueryDisOrdServlet
 */
@WebServlet("/QueryDisOrd.do")
public class QueryDisOrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderitemDao oidao=new OrderitemDao();    
	OrdersDao odao=new OrdersDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String gno=request.getParameter("gno");
		
		List<?> olist=oidao.queryBygno(gno);
		response.getWriter().print(new Gson().toJson(olist));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
