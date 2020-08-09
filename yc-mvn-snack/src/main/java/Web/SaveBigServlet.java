package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.ProductDao;

/**
 * Servlet implementation class SaveBigServlet
 */
@WebServlet("/SaveBig.do")
public class SaveBigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ProductDao pdao=new ProductDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gno=request.getParameter("gno");
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
		String status=request.getParameter("status");
		
		
		try {
			pdao.save(gno, gname, tno, price, intro, balance, pics, unit, qperied, weight, descr, status);
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
