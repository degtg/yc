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
import com.snack.bean.MemberInfo;
import com.snack.dao.UserDao;

/**
 * Servlet implementation class HeadServlet
 */
@WebServlet("/HeadServlet.do")
public class HeadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserDao udao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		if(user!=null) {
			Object mno=user.get("mno");
			if(mno!=null) {
				List<Map<String ,Object>> list=udao.queryHead(mno.toString());
				response.getWriter().print(new Gson().toJson(list));
			}
		}
		
		
		
		
		/*
		 * Map<String,Object> user=(Map<String, Object>)
		 * request.getSession().getAttribute("loginedUser"); String
		 * nickName=""+user.get("nickName"); String mno=""+user.get("mno");
		 */
		/*List<MemberInfo> list=udao.queryHead("1");*/
		/*
		 * String photo=null; for (MemberInfo memberInfo : list) {
		 * photo=memberInfo.getPhoto(); }
		 */
		/* udao.updateHead(photo, nickName); */
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
