package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.TypeDao;

/**
 * Servlet implementation class DeleteTypeServlet
 */
@WebServlet("/DeleteType.do")
public class DeleteTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    TypeDao tdao=new TypeDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tno=request.getParameter("tno");
		try {
			tdao.delType(tno);
			response.getWriter().print("删除成功");
		}catch(Exception e) {
			response.getWriter().print("删除失败");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
