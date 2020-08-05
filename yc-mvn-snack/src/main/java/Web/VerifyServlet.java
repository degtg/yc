package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerifyServlet
 */
@WebServlet("/verify.do")
public class VerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//设置响应对象的字符集
		response.setCharacterEncoding("UTF-8");
		//设置网页的字符集
		response.setContentType("text/html;charset=utf-8");
		String vcode =request.getParameter("vcode");
		String scode=(String) request.getSession().getAttribute("vcode");
		if(vcode!=null&&vcode.trim().isEmpty()==false) {
			if(vcode.equalsIgnoreCase(scode)) {
				response.getWriter().append("验证码正确");
			}else {
				response.getWriter().append("验证码错误");
			}
		}else{
			response.getWriter().append("请输入验证码");
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
