package Web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.bean.OrderItemInfo;
import com.snack.dao.DiscussDao;
import com.snack.dao.OrderitemDao;
import com.snack.dao.OrdersDao;

/**
 * Servlet implementation class InsertDiscServlet
 */
@WebServlet("/InsertDisc.do")
public class InsertDiscServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private OrderitemDao oidao=new OrderitemDao(); 
	private OrdersDao odao=new OrdersDao(); 
	DiscussDao dao=new DiscussDao();
	protected void goComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content=request.getParameter("content");
		String picture1=request.getParameter("picture1");
		String picture2=request.getParameter("picture2");
		String picture3=request.getParameter("picture3");
		String gno=request.getParameter("gno");

		
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		if(user!=null) {
			Object mno=user.get("mno");
			String nickName=""+user.get("nickName");
			String photo=""+user.get("photo");
			
		if(mno!=null) {
				//try {
					System.out.println("aaaaaaa");
				//	List<OrderItemInfo>list=dao.queryGno1(mno.toString(),gno);
					//if(list.size()==0) {
						System.out.println("aaaaaaa");
						if(content==null||content.trim().isEmpty()) {
							response.getWriter().append("亲~你还没有写评语哦");	
						
					 }else {
						 dao.insertDiscuss(mno.toString(), nickName, content, photo, gno, picture1, picture2, picture3);
						 response.getWriter().append("亲~评论成功！！");
					 }
					
					
				//} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
			}else {
				response.getWriter().append("亲~你还未注册，请先注册！！");
			}
		
	}

	
	protected void isComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gno=request.getParameter("gno");
		response.getWriter().append(new Gson().toJson(dao.updateStatus4(gno)));
	}

}
