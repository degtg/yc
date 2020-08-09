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

@WebServlet("/PwdServlet.do")
public class PwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao udao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String pwd=request.getParameter("pwd");
	   String npwd=request.getParameter("npwd");
	   String cpwd=request.getParameter("cpwd");
	   Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
	   
	   if(pwd==null|| pwd.trim().isEmpty()) {
			response.getWriter().append("请填写原始密码！");
		}else if(npwd==null|| npwd.trim().isEmpty()) {
			response.getWriter().append("请填写新密码！");
		}else if(npwd==null|| npwd.trim().isEmpty()) {
			response.getWriter().append("请再次填写新密码！");
		}else if(npwd.equals(cpwd)==false) {
			response.getWriter().append("两次新密码不一致！");
		}else if(pwd.equals(npwd)==true) {
			response.getWriter().append("新密码不能与旧密码相同");
		}else {
			try {
				if(user!=null) {
					Object mno=user.get("mno");
					if(mno!=null) {
						udao.modifypwd(npwd, mno.toString());
						response.getWriter().append("密码修改成功！亲~请重写登入");
					}else {
						response.getWriter().append("密码修改失败！");
					}
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
		
		}
	   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
