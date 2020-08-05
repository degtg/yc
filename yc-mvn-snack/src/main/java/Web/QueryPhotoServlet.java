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
import com.snack.dao.DiscussDao;
import com.snack.dao.OrderitemDao;

/**
 * Servlet implementation class QueryPhotoServlet
 */
@WebServlet("/QueryPhoto.do")
public class QueryPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderitemDao oidao=new OrderitemDao();      
	DiscussDao dao=new DiscussDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		if(user!=null) {
			Object mno=user.get("mno");
			if(mno!=null) {
				List<Map<String ,Object>> plist=dao.queryPhoto(mno.toString());
				response.getWriter().print(new Gson().toJson(plist));
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
