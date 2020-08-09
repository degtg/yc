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
import com.snack.dao.AddressDao;

/**
 * Servlet implementation class QueryAddressServlet
 */
@WebServlet("/QueryAddress.do")
public class QueryAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private AddressDao adao=new AddressDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		
		if(user!=null) {
			Object mno=user.get("mno");
			List<Map<String ,Object>> list=adao.queryAddress(mno.toString());
			response.getWriter().print(new Gson().toJson(list));
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
