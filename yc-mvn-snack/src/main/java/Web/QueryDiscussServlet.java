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
import com.google.gson.GsonBuilder;
import com.snack.dao.DiscussDao;

/**
 * Servlet implementation class QueryDiscussServlet
 */
@WebServlet("/QueryDiscuss.do")
public class QueryDiscussServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DiscussDao ddao=new DiscussDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gno=request.getParameter("gno");
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		Object mno=user.get("mno");
		List<Map<String,Object>> alllist=ddao.query(mno.toString(),gno);
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		response.getWriter().print(gson.toJson(alllist));

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
