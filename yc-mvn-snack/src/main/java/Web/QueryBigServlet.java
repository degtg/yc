package Web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.GsonBuilder;
import com.snack.bean.AdminInfo;
import com.snack.bean.GoodsInfo;
import com.snack.dao.ProductDao;

/**
 * Servlet implementation class QueryBigServlet
 */
@WebServlet("/QueryBig.do")
public class QueryBigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ProductDao pdao=new ProductDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        GoodsInfo dp = new GoodsInfo();
		// 装载方法
		try {
			BeanUtils.populate(dp, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		List<?> list = pdao.query1(dp,page, rows);
		int total = pdao.count1(dp);
        HashMap<String,Object> data = new HashMap<>();
		data.put("rows", list);
		data.put("total", total);
		response.getWriter().print(new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(data));
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
