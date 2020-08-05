package Web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snack.dao.CartDao;



/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/静态页面(7.10sql)/cart.do")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CartDao cdao=new CartDao();   
   //cart.do?op=add&gon=???
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gno=request.getParameter("gno");
		String num=request.getParameter("num");
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		System.out.println("sdda");
		try {
			if(user!=null) {
				Object mno=user.get("mno");
				if(mno!=null) {
					List<Map<String,Object>> list=cdao.query(mno.toString(), gno);
					if(list.size()==0) {
						cdao.insert(mno, gno,num.toString());
						System.out.println(num.toString());
						response.getWriter().print("购物车添加成功！");
					}else {
						cdao.update(mno, gno, num.toString());
						response.getWriter().print("购物车更新成功！");
					}
				}
				
				/*
				 * if(cdao.update(mno, gno, num.toString())==0) { //结果为0则说明用户没有添加商品 cdao.insert(
				 * mno, gno); cdao.insert(mno, gno);
				 * 
				 * response.getWriter().print("购物车添加成功！"); }else { cdao.update(mno, gno, num); }
				 */
			}else {
				response.getWriter().append("亲~你还未登录！不能加购哦~");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//index页面点击小购物车图标完成一次添加购物车
	protected void addcart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gno=request.getParameter("gno");
		String num="1";
		@SuppressWarnings("unchecked")
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		System.out.println("sdda");
		try {
			if(user!=null) {
				Object mno=user.get("mno");
				if(mno!=null) {
					List<Map<String,Object>> list=cdao.query(mno.toString(), gno);
					if(list.size()==0) {
						cdao.insert(mno, gno,num.toString());
						System.out.println(num.toString());
						response.getWriter().print("购物车添加成功！");
					}else {
						cdao.update(mno, gno, num.toString());
						response.getWriter().print("购物车添加成功！");
					}
				}
				
				/*
				 * if(cdao.update(mno, gno, num.toString())==0) { //结果为0则说明用户没有添加商品 cdao.insert(
				 * mno, gno); cdao.insert(mno, gno);
				 * 
				 * response.getWriter().print("购物车添加成功！"); }else { cdao.update(mno, gno, num); }
				 */
			}else {
				response.getWriter().append("亲~你还未登录！不能加购哦~请先登录");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String mno="2";//用户mon从会话中获取loginedUser.getId(),
		try {
			Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
			Object mno=user.get("mno");
			List<?>list=cdao.queryByMno(mno);
			print(response,list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void queryNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String mno="2";//用户mon从会话中获取loginedUser.getId(),
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		Object mno=user.get("mno");
		if(mno!=null) {
			List<?>list=cdao.queryNum(mno);
			print(response,list);
		}
		
	}
	
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cno=request.getParameter("cno");
		if(cdao.delByCno(cno)==0) {
			cdao.delByCno(cno);
		}
		response.getWriter().append("{\"text\":\"删除成功！\"}");
	}
	//在购物车页面显示通过每件的gno来查询商品的数量
	public void UpdateNumByGno(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String gno=request.getParameter("gno");
		String num=request.getParameter("num");
		try {
			if(user!=null) {
				Object mno=user.get("mno");
				List<Map<String,Object>> list=cdao.query(mno.toString(), gno);
				if(list.size()!=0) {
					cdao.update1(num, mno.toString(), gno);
					System.out.println(num);

					//response.getWriter().print("更新成功！");
				}else {
					response.getWriter().print("加购失败！");
				}
				
			}
		}catch(Exception e) {
			
		}
		
	}
	//对购物车商品数量进行加减时进行更新购物车
	public void UpdateNumByGno1(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String gno=request.getParameter("gno");
		String num=request.getParameter("num");
		try {
			if(user!=null) {
				Object mno=user.get("mno");
				List<Map<String,Object>> list=cdao.query(mno.toString(), gno);
				System.out.println("sssss");
				if(list.size()!=0) {
					System.out.println("sssss");
					cdao.update2(num, mno.toString(), gno);
					System.out.println(num);

					//response.getWriter().print("更新成功！");
				}else {
					response.getWriter().print("加购失败！");
				}
				
			}
		}catch(Exception e) {
			
		}
		
	}
	//查询购物车商品总价格和总金额
	protected void queryTotal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		Object mno=user.get("mno");
		String cno=request.getParameter("cno");
		List<?>list=cdao.query1(cno, mno.toString());
		print(response,list);
	}
	
	//清空购物车
	protected void delbymno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		Object mno=user.get("mno");
		cdao.delByMno(mno.toString());
		response.getWriter().append("{\"text\":\"购物车清空成功！\"}");
	}

}
