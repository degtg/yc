package thread.d0730;

import java.io.PrintWriter;

public class ToIndexServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		//响应重定向
		response.sendRedirect("/photo/index.html");
	}
}
