package Web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.bean.MemberInfo;
import com.snack.dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UserDao udao=new UserDao();
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if(op.equals("Login")) {
			Login(request,response);
			
		}else if(op.equals("checkLogin")) {
			checkLogin(request,response);
			
		}
	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> u= (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		try {
			response.getWriter().print(u);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	public void Login(HttpServletRequest request, HttpServletResponse response) {
		String nickName=request.getParameter("nickName");
		String pwd=request.getParameter("pwd");
		Map<String,Object> user=udao.selectByLogin(nickName, pwd);
		
		try {
			if(user.get("status").equals("冻结")) {
				response.getWriter().print("您的账户已被冻结");
				
			}else {
				
				if(user!=null) {
					request.getSession().setAttribute("loginedUser", user);
					response.getWriter().print("登录成功");
				}
			}
		} catch (Exception e) {
			try {
				response.getWriter().print("用户名或密码错误");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
