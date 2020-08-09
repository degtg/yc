package Web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.snack.dao.UserDao;


@WebServlet("/Foundpwd.do")
public class Foundpwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao udao=new UserDao();  
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realName=request.getParameter("realName");
		String email=request.getParameter("email");
		String yzm=request.getParameter("yzm");
		HttpSession session=request.getSession();
		//向页面输出一张验证码图片
		String vcode=VerifyCodeUtils.outputImage(response);
		session.setAttribute("vcode", vcode);
		String y=(String) request.getSession().getAttribute("yzm");
		Map<String,Object>users=udao.SelectByMember(realName, email);
		if(users!=null) {
			
			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
