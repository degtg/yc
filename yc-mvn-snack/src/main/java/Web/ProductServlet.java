package Web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.util.DBHelper;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/静态页面(7.10sql)/product.do")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//查询全部商品
		protected void queryAllGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String sql="select * from goodsinfo limit 0,20";
			List<?>list=new DBHelper().query(sql);
			HashMap<String,Object>page=new HashMap<>();
			page.put("list", list);
			print(response,page);
		}
	//查询热卖商品
	protected void queryHot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql="select * from goodsinfo where status=1 limit 0,20";
		List<?>list=new DBHelper().query(sql);
		HashMap<String,Object>page=new HashMap<>();
		page.put("list", list);
		print(response,page);
	}
	//查询新品商品
		protected void queryNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String sql="select * from goodsinfo where status=0 limit 0,20";
			List<?>list=new DBHelper().query(sql);
			HashMap<String,Object>page=new HashMap<>();
			page.put("list", list);
			print(response,page);
		}
	//查询某件商品
	protected void queryByGno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gno=request.getParameter("gno");
		System.out.println(gno);
		String sql="select * from goodsinfo where gno =?";
		List<?>list=new DBHelper().query(sql, gno);
		if(list!=null && list.size()>0) {
			print(response,list.get(0));
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
