package thread.d0730;

import java.io.PrintWriter;

public class PostServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		String name=request.getParameter("name");
				
		response.getWriter().append("post,name="+name);
	}
}
