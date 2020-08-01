package thread.d0730;

import java.io.PrintWriter;

public class CookieServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		/**
		 * 向浏览器发送cookie 值 name=wusong
		 */
		Cookie cookie=new Cookie("name","luoli");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		response.getWriter().append("cookie");
	}
}
