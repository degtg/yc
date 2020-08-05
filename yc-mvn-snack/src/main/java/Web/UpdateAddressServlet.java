package Web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.AddressDao;

/**
 * Servlet implementation class UpdateAddressServlet
 */
@WebServlet("/UpdateAddress.do")
public class UpdateAddressServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private AddressDao adao=new AddressDao();
    
   //新增收获地址
	protected void AddAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String mno=request.getParameter("mno");
		String name=request.getParameter("name");
		String tel=request.getParameter("tel");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		String addr=request.getParameter("addr");
		
		if(name==null || name.trim().isEmpty()) {
			response.getWriter().print("亲~你还没有填写姓名哦！");
		}else if(tel==null || tel.trim().isEmpty()) {
			response.getWriter().print("亲~你还没有填写联系方式哦！");
		}else if(province==null || province.trim().isEmpty()) {
			response.getWriter().print("亲~你还没有填写地址哦！");
		}else if(addr==null || addr.trim().isEmpty()) {
			response.getWriter().print("亲~你的地址不够详细哦！");
		}else {
			@SuppressWarnings("unchecked")
			Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
			if(user!=null) {
				Object mno=user.get("mno");
				List<?> list=adao.queryAddress(mno.toString());
				if(list!=null) {
					adao.insert(mno.toString(), name, tel, province, city, area, addr);
					response.getWriter().print("亲~地址添加成功！");
				}
				
			}
			
		}
	}

	//设置默认地址
	protected void setDefualtaddr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ano=request.getParameter("ano");
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		if(user!=null) {
			Object mno=user.get("mno");
			List<?> list=adao.queryAddress(mno.toString());
			if(list!=null) {
				adao.update(mno.toString(),ano.toString());
				response.getWriter().print("亲~地址已设置为默认地址！");
			}
			
		}
	}
	//删除地址
	protected void deladdr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ano=request.getParameter("ano");
		if(adao.delByano(ano)==0) {
			adao.delByano(ano);
		}
		response.getWriter().append("{\"text\":\"亲~删除地址成功！\"}");
	}

}
