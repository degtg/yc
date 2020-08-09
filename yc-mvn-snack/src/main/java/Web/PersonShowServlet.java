package Web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.DiscussDao;

/**
 * Servlet implementation class PersonShowServlet
 */
@WebServlet("/PersonShow.do")
public class PersonShowServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private DiscussDao  disdao=new DiscussDao();
	protected void Show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String,Object>> list=disdao.queryAll();
		print(response,list);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
