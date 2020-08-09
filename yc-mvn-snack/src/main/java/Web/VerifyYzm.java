package Web;

import java.io.IOException;

import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.snack.dao.UserDao;


/**
 * Servlet implementation class VerifyYzm
 */
@WebServlet("/verifyYzm.do")
public class VerifyYzm extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserDao udao=new UserDao();     
	protected void SendYZM(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//设置网页的字符集
		response.setContentType("text/html;charset=utf-8");
		String realName=request.getParameter("realName");
		String email=request.getParameter("email");
		
		Random random=new Random();
		int yzm=1000+random.nextInt(9999);
		//将随机生成的验证码保存到会话中
		HttpSession session=request.getSession();
		session.setAttribute("yzm", yzm);
		
		HtmlEmail esend=new HtmlEmail();//创建对象
		esend.setHostName("smtp.qq.com");//在QQ邮箱设置账户 打开smtp服务
		esend.setCharset("utf-8");
		String a="mhoiruzrnexrddhd";//客户端授权码
		try {
			System.out.println("111");
			if(email!=null&&email.trim().isEmpty()==false) {
				System.out.println("222222");
				esend.addTo(email);//收件地址
				System.out.println("222222");
				esend.setFrom(email,realName);//收件地址 和用户名
				System.out.println("222222");
				esend.setAuthentication(email,a );// 邮件地址  客户端授权码 （打开smtp服务即可得到授权码）
				esend.setSubject("验证码获取");//邮件名 可任意填写
				esend.setMsg("尊敬的用户您好，你本次注册的验证码为："+yzm+"请在5分钟内完成注册");
				esend.send();
				response.getWriter().append("验证码已发送");
			}
			
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
		
	}
	
	protected void checkInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String realName=request.getParameter("realName");
		String email=request.getParameter("email");
		String yzm1=request.getParameter("yzm");
		String cpwd=request.getParameter("cpwd");
		int YZM=(int) request.getSession().getAttribute("yzm");
		if(yzm1!=null&&yzm1.trim().isEmpty()==false) {
			if(yzm1.equals(YZM)) {
				response.getWriter().append("验证码正确！");
				udao.updateMemberPwd(cpwd, realName, email);
			}else {
				response.getWriter().append("验证码不正确！");
			}
			
		}
		

	}

	

}
