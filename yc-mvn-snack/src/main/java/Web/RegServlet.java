package Web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.UserDao;


/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet.do")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao udao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String nickName=request.getParameter("nickname");
		//String pwd=request.getParameter("pwd");
		String cpwd=request.getParameter("cpwd");
		String email=request.getParameter("email");
		String vcode =request.getParameter("vcode");
		String scode=(String) request.getSession().getAttribute("vcode");
		String tel=request.getParameter("tel");
		if(!vcode.equals(scode)) {
			response.getWriter().append("验证码不正确！");
		}else if(tel.length()!=11) {
			response.getWriter().append("电话输入不正确！");
		}else {
			List<Map<String, Object>> user=udao.queryBynickName(nickName);
			if(user.size()==0){
					udao.insert(nickName, cpwd, tel, email, "正常");
					response.getWriter().append("用户注册成功！");
			}else {
				System.out.println("qqqqqqqqqqqqqqqqqqqqq");
				response.getWriter().append("该用户名已经被注册过了！");
				
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
