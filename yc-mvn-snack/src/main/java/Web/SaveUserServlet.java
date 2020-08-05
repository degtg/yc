package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.UserDao;

/**
 * Servlet implementation class SaveUserServlet
 */
@WebServlet("/SaveUser.do")
public class SaveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserDao udao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mno=request.getParameter("mno");
		String nickName=request.getParameter("nickName");
		String realName=request.getParameter("realName");
		String pwd=request.getParameter("pwd");
		String tel=request.getParameter("tel");
		String email=request.getParameter("email");
		String photo=request.getParameter("photo");
		String regDate=request.getParameter("regDate");
		String status=request.getParameter("status");
		
		try {
			udao.save(mno, nickName, realName, pwd, tel, email, photo, regDate, status);
			response.getWriter().print("保存成功");
		}catch(Exception e) {
			response.getWriter().print("保存失败");

			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
