package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.AdminDao;

/**
 * Servlet implementation class AddAdminServlet
 */
@WebServlet("/AddAdmin.do")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AdminDao adao=new AdminDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aname=request.getParameter("aname");
		String pwd=request.getParameter("pwd");
		String tel=request.getParameter("tel");
		String photo=request.getParameter("photo");
		if(aname==null || aname.trim().isEmpty()) {
			response.getWriter().print("请输入姓名");
		}else if(pwd==null || pwd.trim().isEmpty()) {
			response.getWriter().print("请输入密码");
		}else if(tel==null || tel.trim().isEmpty()) {
			response.getWriter().print("请输入电话号码");
		}else {
			adao.addAdmin(aname, pwd, tel, photo);
			response.getWriter().print("添加成功");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
