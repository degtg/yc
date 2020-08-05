package Web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * Servlet implementation class AreaServlet 
 */
@WebServlet("/area.do")
public class AreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String level=request.getParameter("level");
		try {
			if("0".equals(level)) {
				String s1=Arrays.toString((AreaConstants.PROVINCES));
				String s2=s1.substring(1,s1.length()-1);
				//response.getWriter().append(Arrays.toString(AreaConstants.PROVINCES));
				response.getWriter().append(s2);
			}else if("1".equals(level)) {
				String shengIndex=request.getParameter("shengIndex");
				int index=Integer.parseInt(shengIndex);
				String s1=Arrays.toString((AreaConstants.CITYS[index]));
				String s2=s1.substring(1,s1.length()-1);
				response.getWriter().append(s2);
			
			}else {
				try {
					String shengIndex=request.getParameter("shengIndex");
					int index1=Integer.parseInt(shengIndex);
					String shiIndex=request.getParameter("shiIndex");
					int index2=Integer.parseInt(shiIndex);
					//response.getWriter().append(Arrays.toString(AreaConstants.COUNTYS[index1][index2]));
					
					String s3=Arrays.toString((AreaConstants.COUNTYS[index1][index2]));
					String s4=s3.substring(1, s3.length()-1);
					response.getWriter().append(s4);
				} catch (ArrayIndexOutOfBoundsException e) {
					//e.printStackTrace();
				}
				
				
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
