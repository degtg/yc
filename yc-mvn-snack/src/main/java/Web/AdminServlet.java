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
import com.snack.dao.AdminDao;

/**
 * Servlet implementation class AddAdminServlet
 */
@WebServlet("/Admin.do")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    private AdminDao adao=new AdminDao();
    protected void AddAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aname=request.getParameter("aname");
		String pwd=request.getParameter("pwd");
		String tel=request.getParameter("tel");
		String photo=request.getParameter("photo");
		if(aname==null || aname.trim().isEmpty()) {
			response.getWriter().print("请输入姓名");
		}else if(pwd==null || pwd.trim().isEmpty()) {
			response.getWriter().print("请输入密码");
		}else if(tel==null || tel.trim().isEmpty()) {
			response.getWriter().print("请输入电话号码");
		}else {
			adao.addAdmin(aname, pwd, tel, photo);
			response.getWriter().print("添加成功");
		}
		
		
	}
	protected void DeleteAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid=request.getParameter("aid");
		try {
			adao.delete(aid);
			response.getWriter().print("删除成功");
		}catch(Exception e) {
			response.getWriter().print("删除失败");
		}
	}
	protected void QueryAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
	 	String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        AdminInfo dp = new AdminInfo();
		// 装载方法
		BeanUtils.populate(dp, request.getParameterMap());

		List<?> list = adao.query1(dp,page, rows);
		int total = adao.count1(dp);
        HashMap<String,Object> data = new HashMap<>();
		data.put("rows", list);
		data.put("total", total);
		response.getWriter().print(new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(data));
		

}
	protected void UpdateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid=request.getParameter("aid");
		String status=request.getParameter("status");
		
		adao.updateStatus(aid, status);
	}
	

}
