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

import com.snack.dao.CartDao;
import com.snack.dao.OrderitemDao;
import com.snack.dao.OrdersDao;
import com.snack.dao.PayDao;

/**
 * Servlet implementation class PayServlet
 */
@WebServlet("/静态页面(7.10sql)/Pay.do")
public class PayServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrdersDao odao=new OrdersDao(); 
    private OrderitemDao oidao=new OrderitemDao(); 
	private PayDao pdao=new PayDao();     
	//查询新增的订单
		protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
			Object mno=user.get("mno");
			Map<String,Object>ret=new HashMap<>();
			Map<String,Object>orders=odao.queryNewOrders(mno.toString());
			ret.put("orders", orders);
			
			ret.put("orderitems", pdao.query(""+orders.get("ono")));
			System.out.println(""+orders.get("ono"));
			print(response,ret);	
		}
		
		//查询更新的订单的支付状态
				protected void udpatePayStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					//String ono=request.getParameter("ono");
					Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
					Object mno=user.get("mno");
					//Map<String,Object>ret=new HashMap<>();
					Map<String,Object>orders=odao.queryNewOrders(mno.toString());
					if(orders!=null) {
						System.out.println("ssssssssssss");
						pdao.updatePay(""+orders.get("ono"));
						System.out.println("ssssssssssss");
						response.getWriter().print("成功更新状态！");
					}else {
						System.out.println("ssssssssssss");
						response.getWriter().print("更新状态！");
					}
					
					
				}
		
		
		

	

}
