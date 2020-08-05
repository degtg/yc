package Web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.snack.bean.OrderInfo;
import com.snack.dao.CartDao;
import com.snack.dao.OrderitemDao;
import com.snack.dao.OrdersDao;
import com.snack.dao.PayDao;

/**
 * Servlet implementation class CartServlet
 */

@WebServlet("/静态页面(7.10sql)/orders.do")
public class OrdersServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private OrdersDao odao=new OrdersDao(); 
    private OrderitemDao oidao=new OrderitemDao(); 
    private CartDao cdao=new CartDao();  
    private PayDao pdao=new PayDao();   
   //cart.do?op=add&gon=???
    //添加订单
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cnos[]=request.getParameterValues("cnos[]");
			@SuppressWarnings("unchecked")
			Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
			Object mno=user.get("mno");
			odao.insert(mno.toString());
			oidao.insert(mno.toString());
			for(String c : cnos ) {
				cdao.delByCno(c);
			}
			response.getWriter().print(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	//查询新增的订单 通过订单号ono和cno来查询显示在订单详细表中
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		Object mno=user.get("mno");
		String cnos[]=request.getParameterValues("cnos[]");
		Map<String,Object>ret=new HashMap<>();
		Map<String,Object>orders=odao.queryNewOrders(mno.toString());
		ArrayList<List<?>>list=new ArrayList<List<?>>();
		ret.put("orders", orders);
		for(String c : cnos ) {
			list.add(oidao.queryByOnoCno(""+orders.get("ono"),c));
			
		}
		ret.put("orderitem", list);
		print(response,ret);
		
		
	}
		
	//查询所有支付的订单下沿
			protected void queryPayorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
				Object mno=user.get("mno");
				Map<String,Object>ret=new HashMap<>();
				Map<String,Object>orders=odao.queryPayOrders(mno.toString());
				ret.put("orders", orders);
				ret.put("orderitem", oidao.queryByOno(""+orders.get("ono")));
				print(response,ret);
				
				
			}
	//查询新增的订单通过订单号来查询 个人商品中心中已支付和未支付的商品订单
	//查询未支付的订单下沿
		protected void queryorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
			Object mno=user.get("mno");
			Map<String,Object>ret=new HashMap<>();
			Map<String,Object>orders=odao.queryNewOrders(mno.toString());
			ret.put("orders", orders);
			ret.put("orderitem", oidao.queryByOno(""+orders.get("ono")));
			print(response,ret);
			
			
		}

	//查询未支付的订单上沿
			protected void queryNoPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
				if(user!=null) {
					Object mno=user.get("mno");
					List<Map<String,Object>> list=pdao.query1(mno.toString());
					print(response,list);
				}
					
			}
	//查询已支付的订单上沿
			protected void queryPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
				if(user!=null) {
					Object mno=user.get("mno");
					List<Map<String,Object>> list=pdao.queryPay(mno.toString());
					print(response,list);
				}
					
			}
			//待收货地址的下沿
			protected void queryshouhuoorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
				Object mno=user.get("mno");
				Map<String,Object>ret=new HashMap<>();
				Map<String,Object>orders=odao.queryshouhuo(mno.toString());
				ret.put("orders", orders);
				ret.put("orderitem", oidao.queryByOno(""+orders.get("ono")));
				print(response,ret);
			}
			//待收货地址的上沿
			protected void querywaitgoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
				if(user!=null) {
					Object mno=user.get("mno");
					List<Map<String,Object>> list=pdao.querywait(mno.toString());
					print(response,list);
				}
					
			}
			
			
			
			//待评论商品的下沿
			protected void queryComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
				Object mno=user.get("mno");
				Map<String,Object>ret=new HashMap<>();
				Map<String,Object>orders=odao.queryWaitCommentOrders(mno.toString());
				ret.put("orders", orders);
				ret.put("orderitem", oidao.queryByOno(""+orders.get("ono")));
				print(response,ret);
			}
			//待收货地址的上沿
			protected void queryCommentOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
				if(user!=null) {
					Object mno=user.get("mno");
					List<Map<String,Object>> list=pdao.querywaitComment(mno.toString());
					print(response,list);
				}
					
			}
			
			//查询更新的订单的收货状态
			protected void udpateGetgoodsStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//String ono=request.getParameter("ono");
				Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
				Object mno=user.get("mno");
				//Map<String,Object>ret=new HashMap<>();
				Map<String,Object>orders=odao.queryshouhuo(mno.toString());
				if(orders!=null) {
					System.out.println("ssssssssssss");
					odao.updateStatus3(""+orders.get("ono"));
					System.out.println("ssssssssssss");
					response.getWriter().print("确认收货成功！");
				}else {
					System.out.println("ssssssssssss");
					response.getWriter().print("确认收货失败！");
				}
				
				
			}
			//查询更新的评论状态
			protected void udpate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//String ono=request.getParameter("ono");
				Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
				Object mno=user.get("mno");
				//Map<String,Object>ret=new HashMap<>();
				Map<String,Object>orders=odao.queryshouhuo(mno.toString());
				if(orders!=null) {
					System.out.println("ssssssssssss");
					odao.update(""+orders.get("ono"));
					System.out.println("ssssssssssss");
					response.getWriter().print("更新状态成功！");
				}else {
					System.out.println("ssssssssssss");
					response.getWriter().print("更新状态失败！");
				}
				
				
			}
			//查询已支付状态为1的订单
			protected void Pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				List<Map<String ,Object>> plist=odao.queryPayOrder();
				response.getWriter().print(new Gson().toJson(plist));
			}
			
			//删除订单
			protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String ono=request.getParameter("ono");
				try {
					odao.delete(ono);
					response.getWriter().append("删除成功");
				}catch(Exception e) {
					response.getWriter().append("删除失败");
				}
			}
			
			//保存订单
			protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String ono=request.getParameter("ono");
				String odate=request.getParameter("odate");
				String ano=request.getParameter("ano");
				String sdate=request.getParameter("sdate");
				String rdate=request.getParameter("rdate");
				String status=request.getParameter("status");
				String price=request.getParameter("price");
				String invoice=request.getParameter("invoice");

				try {
					odao.save(ono, odate, ano, sdate, rdate, status, price, invoice);
					response.getWriter().print("保存成功");
				}catch(Exception e) {
					response.getWriter().print("保存失败");

					
				}
			}
			
			//查询订单
			protected void QueryOrd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
					String page=request.getParameter("page");
			        String rows=request.getParameter("rows");
			        OrderInfo dp = new OrderInfo();
					// 装载方法
					BeanUtils.populate(dp, request.getParameterMap());

					List<?> list = odao.query1(dp,page, rows);
					int total = odao.count1(dp);
			        HashMap<String,Object> data = new HashMap<>();
					data.put("rows", list);
					data.put("total", total);
					response.getWriter().print(new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(data));
					
			}

			
			//查询商品状态
			protected void QueryStatusItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.getWriter().print(new Gson().toJson(odao.getStatusItems()));

			}
			//后台更新发货状态
			protected void UpdateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String ono=request.getParameter("ono");
				try {
					odao.updateStatus(ono);
					response.getWriter().print("发货成功");
				}catch(Exception e) {
					response.getWriter().print("发货失败");

					
				}
			}
			//下架商品
			protected void down(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String gno=request.getParameter("gno");
				try {
					odao.updateStatusdown(gno);
					response.getWriter().print("商品已下架");
				}catch(Exception e) {
					response.getWriter().print("商品下架失败");

					
				}
			}

			//下架商品类型
			protected void downtype(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String tno=request.getParameter("tno");
				try {
					odao.updateStatusdown(tno);
					response.getWriter().print("商品已下架");
				}catch(Exception e) {
					response.getWriter().print("商品下架失败");

					
				}
			}
			
	

}
