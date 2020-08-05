package Web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.OrderitemDao;

/**
 * Servlet implementation class QueryOrderServlet
 */
@WebServlet("/QueryOrder.do")
public class QueryOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private OrderitemDao odao=new OrderitemDao();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ono=request.getParameter("ono");
	//------------
		String cno=request.getParameter("cno");
		List<?> orderlist=odao.queryByOnoCno(ono,cno);
		response.getWriter().print(new Gson().toJson(orderlist));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
