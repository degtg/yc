package Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VcodeServlet
 */
@WebServlet("/vcode.do")
public class VcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//设置响应对象的字符集
		response.setCharacterEncoding("UTF-8");
		//设置网页的字符集
		response.setContentType("text/html;charset=utf-8");
		//获取session 必须在输出图片之前
		HttpSession session=request.getSession();
		//向页面输出一张验证码图片
		String vcode=VerifyCodeUtils.outputImage(response);
		session.setAttribute("vcode", vcode);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
