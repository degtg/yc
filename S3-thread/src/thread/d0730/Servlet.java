package thread.d0730;

import java.io.IOException;

/**
 * Servlet 继承关系
 * servlet==>GenriceServlet==>HttpServlet
 *
 */
public interface Servlet {
	public void service(HttpServletRequest request,HttpServletResponse response) 
			throws IOException;

}
