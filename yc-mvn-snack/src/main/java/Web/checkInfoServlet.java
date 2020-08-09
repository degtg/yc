package Web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.UserDao;

/**
 * Servlet implementation class checkInfoServlet
 */
@WebServlet("/checkInfo.do")
public class checkInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao udao=new UserDao();   
	@SuppressWarnings("unlikely-arg-type")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realName=request.getParameter("realName");
		String email=request.getParameter("email");
		String yzm1=request.getParameter("yzm");
		String cpwd=request.getParameter("cpwd");
		Object YZM= request.getSession().getAttribute("yzm");
		Map<String, Object> list= udao.SelectByMember(realName.toString(), email.toString());
		try {
			if(list.size()==0 || list==null) {
				response.getWriter().append("该用户或者邮箱不存在！");
			}else {
				if(yzm1!=null&&yzm1.trim().isEmpty()==false) {
					if(yzm1.equals(YZM.toString())) {
						//response.getWriter().append("验证码正确！");
						response.getWriter().append("亲你已经修改密码了，可以回首页登录哦！");
						udao.updateMemberPwd(cpwd, realName, email);
					}else {
						response.getWriter().append("验证码不正确！");
					}
					
				}
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
