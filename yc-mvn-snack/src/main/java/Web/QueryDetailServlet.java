package Web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.ProductDao;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class QueryDetailServlet
 */
@WebServlet("/QueryDetail.do")
public class QueryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ProductDao pdao=new ProductDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Jedis jd=new Jedis();
		String gno=request.getParameter("gno");
		
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		System.out.println("===================================="+user);
		String mno=""+user.get("mno");
		System.out.println("======mno"+mno);
		System.out.println("======gno:"+gno);
		
		List<Map<String ,Object>> dlist=pdao.queryDetail(gno);
		System.out.println("--------------dlist:"+dlist);
		response.getWriter().print(new Gson().toJson(dlist));
		
		double mno1= (Double.valueOf(""+user.get("mno"))) ;
		System.out.println("---------mno1"+mno1);
		jd.zadd("detail", mno1,gno);
		jd.close(); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
