package Web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.snack.dao.PageDao;



/**
 * Servlet implementation class QueryPageServlet
 */
@WebServlet("/QueryPages.do")
public class QueryPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private PageDao pdao=new PageDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取分页参数 第几页
		String page=request.getParameter("page");
		int ipage=1;
		if(page!=null&&page.trim().isEmpty()==false) {
			ipage=Integer.valueOf(page);
		}
		List<Map<String,Object>> hotList=pdao.queryPage(ipage,10);
		int pages=pdao.countPages(10);
		//使用map对象保存list和pages
		Map<String,Object>data=new HashMap<String,Object>();
		data.put("list",hotList);
		data.put("pages",pages);
		
		//Gson google开发的JSON工具，可以将对象转成json字符串或者方向操作
		Gson gson=new Gson();
		System.out.println(data);
		String json=gson.toJson(data);
		System.out.println(json);
		response.getWriter().append(json);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
