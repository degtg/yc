package Web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.ProductDao;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class RecentServlet
 */
@WebServlet("/RecentServlet.do")
public class RecentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao pdao = new ProductDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Jedis jd = new Jedis();
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		if(user!=null) { 
			/* jd.del("detail"); */
			String mno= ""+user.get("mno");
			Set<String>set=jd.zrangeByScore("detail", mno, mno);
			List<Map<String, Object>> list = new ArrayList<>(); 
			System.out.println("set:"+set);
			if(set.size()!=0) {
				for (String s : set) {
					System.err.println(s+"========");
				 list.add(pdao.queryid1(s));
				}
			}
			
			
			jd.close();
			response.getWriter().print(new Gson().toJson(list));
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
