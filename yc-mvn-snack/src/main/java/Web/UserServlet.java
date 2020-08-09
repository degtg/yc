package Web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.UserDao;

/**
 * Servlet implementation class GetLoginedUserServlet
 */
@WebServlet("/静态页面(7.10sql)/UserServlet.do")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserDao udao=new UserDao();
	protected void GetLoginedUserServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		Gson gson=new Gson();
		String json=gson.toJson(user);
		response.getWriter().print(json);
	}
	protected void DeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mno=request.getParameter("mno");
		try {
			udao.delete(mno);
			response.getWriter().print("删除成功");
		}catch(Exception e) {
			response.getWriter().print("删除失败");
		}
	}
	
	protected void QueryUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickName=request.getParameter("nickName");
		String tel=request.getParameter("tel");
		

		Gson gson=new Gson();
		String json=gson.toJson(udao.query(nickName,tel));
		response.getWriter().print(json);
	}
	
	protected void UpdateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String status=request.getParameter("status");
		String mno=request.getParameter("mno");
		udao.updateStatus(mno, status);
	}

	
	

}
