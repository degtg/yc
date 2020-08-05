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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.snack.bean.AdminInfo;
import com.snack.bean.MemberInfo;
import com.snack.dao.UserDao;

/**
 * Servlet implementation class QueryAdminServlet
 */
@WebServlet("/QueryUser.do")
public class QueryUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDao udao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        MemberInfo dp = new MemberInfo();
		// 装载方法
		try {
			BeanUtils.populate(dp, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		List<?> list = udao.query1(dp,page, rows);
		int total = udao.count1(dp);
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
