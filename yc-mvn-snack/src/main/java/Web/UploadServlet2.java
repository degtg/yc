package Web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.snack.dao.UserDao;

/**
 * Servlet implementation class UploadServlet
 */
@MultipartConfig()
@WebServlet("/UploadImg.s")
public class UploadServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDao udao=new UserDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//设置网页的字符集
		response.setContentType("text/html;charset=utf-8");
		
		//D:\ALL\filestore\apache-tomcat-8.5.54\webapps\snack\静态页面\image
		Part part=request.getPart("file");
		part.getName();//获取上传字段名
		String filename=part.getSubmittedFileName();//获取上传的文件名
		part.getSize();
		
		//定义工程内部的上传文件夹
		String webpath="静态页面/image";
		//获取应用上下文对象
		ServletContext sc=getServletContext();
		//返回web路径对应的磁盘路径
		/* String diskpath=sc.getRealPath(webpath); */
		//文件必须放在工程的里面
		part.write("/"+filename);
		//返回该文件web路径
		String photo="image/"+filename;
		//response.getWriter().append(webpath+"/"+filename);
		Map<String,Object> user=(Map<String, Object>) request.getSession().getAttribute("loginedUser");
		if(user!=null) {
			Object mno=user.get("mno");
			if(mno!=null) {
				udao.changehead(mno.toString(), photo);
			}
		}
	
		/*
		 * @SuppressWarnings("unchecked") Map<String,Object> user=(Map<String, Object>)
		 * request.getSession().getAttribute("loginedUser"); String
		 * mno=""+user.get("mno"); udao.changehead(mno, photo);
		 */

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
