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
 * Servlet implementation class AddressServlet
 */
@WebServlet("/AddressServlet.do")
public class AddressServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private AddressDao adao = new AddressDao();

	protected void Set(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ano = request.getParameter("ano");
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");

		if (user != null) {
			Object mno = user.get("mno");
			List<?> list1 = adao.queryAddressFlag(mno.toString());
			//List<?> list=adao.queryAddress(mno.toString()); 
			if(list1.size()==0) {
				adao.update(mno.toString(),ano.toString());
				response.getWriter().print("亲~设置为默认地址成功！"); 
			}else {
				response.getWriter().print("亲~当前已有默认地址！");
				
			}
		}
	}

	protected void Cancel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ano = request.getParameter("ano");
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");

		if (user != null) {
			Object mno = user.get("mno");
			List<Map<String, Object>> list1 = adao.queryAddressFlag(mno.toString());
			if (list1 != null) {
				List<?> list = adao.queryAddress(mno.toString());
				if (list != null) {
					adao.Cancel(mno.toString(), ano);
					response.getWriter().print("亲~已取消当前默认地址！");
				}
			} else {
				response.getWriter().print("亲~！");
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
