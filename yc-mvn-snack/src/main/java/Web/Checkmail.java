package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Checkmail
 */
@WebServlet("/Checkemail.do")
public class Checkmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//设置网页的字符集
		response.setContentType("text/html;charset=utf-8");
		  String yzm=request.getParameter("yzm");
		   int vcode=Integer.parseInt(yzm);
		   
		  // Integer svcode=(Integer) request.getSession().getAttribute("vcode");
		   Integer code=(Integer) request.getSession().getAttribute("yzm");
		   System.out.println(code);
		   int svcode1=code; 
		   
		     if(yzm!=null &&yzm.trim().isEmpty()==false ) {
		    	 if(vcode==svcode1) {
		    		 response.getWriter().append("验证码正确");
		    	 }else {
		    		 response.getWriter().append("验证码不正确");
		    	 }
		     }else {
		    	 response.getWriter().append("请输入验证码");
		     }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
