package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.OrdersDao;

/**
 * Servlet implementation class SaveOrdersServlet
 */
@WebServlet("/SaveOrders.do")
public class SaveOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    OrdersDao odao=new OrdersDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ono=request.getParameter("ono");
		String odate=request.getParameter("odate");
		String ano=request.getParameter("ano");
		String sdate=request.getParameter("sdate");
		String rdate=request.getParameter("rdate");
		String status=request.getParameter("status");
		String price=request.getParameter("price");
		String invoice=request.getParameter("invoice");

		try {
			odao.save(ono, odate, ano, sdate, rdate, status, price, invoice);
			response.getWriter().print("保存成功");
		}catch(Exception e) {
			response.getWriter().print("保存失败");

			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
