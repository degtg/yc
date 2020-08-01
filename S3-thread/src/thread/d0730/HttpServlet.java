package thread.d0730;

import java.io.IOException;
import java.lang.reflect.Parameter;

/*
 * 负责根据请求的方法method 调用对应doXXX方法
 * service() 负责判断和转发
 * doxxx() 负责处理流对应的method
 */
public class HttpServlet implements Servlet{

	public void service(HttpServletRequest request,HttpServletResponse response) 
			throws IOException {
		if("GET".equals(request.getMethod()) ) {
			doGet(request,response);
		}else if("POST".equals(request.getMethod()) ) {
			doPost(request,response);
		}else if("PUT".equals(request.getMethod()) ) {
			doPut(request,response);
		}else if("DELETE".equals(request.getMethod()) ) {
			doDelete(request,response);
		}else {
			//补充其它方法
		}
		
		
		response.setStatus(200, "OK");
		response.flushBuffer();
	}
	

	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
		
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response) {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	}
	

	
	
	
	
}
