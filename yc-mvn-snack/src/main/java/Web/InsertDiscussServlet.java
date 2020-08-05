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
import com.snack.dao.DiscussDao;

/**
 * Servlet implementation class InsertDiscussServlet
 */
@WebServlet("/InsertDiscuss.do")
public class InsertDiscussServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	DiscussDao dao=new DiscussDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gno=request.getParameter("gno");
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		try {
			if(user!=null) {
				Object mno=user.get("mno");
				Object nickName=user.get("nickName");
				Object photo=user.get("photo");
				List<?>list=dao.query(mno.toString(),gno);
				if(list.size()==0) {
					if(nickName!=null&&photo!=null) {
						String content=request.getParameter("content");
						dao.insert(mno.toString(),nickName.toString(), content, photo.toString(),gno);
						response.getWriter().append("亲~评论成功！");
						
					}else {
						response.getWriter().print("亲~评论失败！");
					}
				}else {
					response.getWriter().append("亲~你已经回复过啦！");
				}
				
			}else {
				response.getWriter().append("亲~你还未登入，请登入后在进行回复!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
