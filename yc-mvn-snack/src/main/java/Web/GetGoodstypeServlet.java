package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.TypeDao;

/**
 * Servlet implementation class GetGoodstypeServlet
 */
@WebServlet("/GetGoodstype.do")
public class GetGoodstypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	TypeDao tdao=new TypeDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print(new Gson().toJson(tdao.getGoodstypeItems()));

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
