package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.AdminDao;


@WebServlet("/SaveAdmin.do")
public class SaveAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AdminDao adao=new AdminDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid=request.getParameter("aid");
		String aname=request.getParameter("aname");
		String pwd=request.getParameter("pwd");
		String tel=request.getParameter("tel");
		String status=request.getParameter("status");
		String photo=request.getParameter("photo");
		String date=request.getParameter("date");
		
		try {
			adao.save(aid, aname, pwd, tel, status, photo, date);
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
