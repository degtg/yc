package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.ProductDao;

/**
 * Servlet implementation class DeleteBigServlet
 */
@WebServlet("/DeleteBig.do")
public class DeleteBigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    ProductDao pdao=new ProductDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gno=request.getParameter("gno");
		try {
			pdao.delete(gno);
			response.getWriter().print("删除成功");
		}catch(Exception e) {
			response.getWriter().print("删除失败");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
