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
import com.snack.dao.PersonDao;


@WebServlet("/静态页面(7.10sql)/Myself.do")
public class MyselfServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private PersonDao pd = new PersonDao( );
	
	protected void Save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//String id = req.getParameter("id");
		String realName = req.getParameter("realName");
		String nickName = req.getParameter("nickName");
		String sex = req.getParameter("sex");
		String area = req.getParameter("area");
		Map<String,Object> user=(Map<String, Object>) req.getSession().getAttribute("loginedUser");
		
		
		try {
			if(user!=null) {
				Object mno=user.get("mno");
				List<Map<String,Object>>list=pd.query(mno.toString());
				if(list.size()==0) {
					pd.insert(realName, nickName, sex, area, mno.toString());
					resp.getWriter( ).print("保存成功啦！"); 
				}else {
					pd.save(realName, nickName,sex,area,mno.toString());
					resp.getWriter( ).print("保存成功"); 
				}
				
			}
			
		}catch(Exception e) {
			resp.getWriter( ).print("保存失败");
		}
	}
	protected void QueryMyself(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//Gson gson = new GsonBuilder( ).setDateFormat("yyyy-MM-dd").create( );
		//String json = gson.toJson(bdao.getBook(id));
		Map<String,Object> user=(Map<String, Object>) req.getSession().getAttribute("loginedUser");
		if(user!=null) {
			Object mno=user.get("mno");
			Gson gson = new Gson( );
			String json = gson.toJson(pd.query( mno.toString()));
			resp.getWriter( ).print(json);
		}
		
	}
}
