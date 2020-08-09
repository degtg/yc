package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.ProductDao;

/**
 * Servlet implementation class AddTypeServlet
 */
@WebServlet("/AddType.do")
public class AddTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ProductDao pdao=new ProductDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tname=request.getParameter("tname");
	
		
		if(tname==null || tname.trim().isEmpty()) {
			response.getWriter().print("请输入商品类型");
		}else {
			pdao.insertType( tname);
			response.getWriter().print("添加成功");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
