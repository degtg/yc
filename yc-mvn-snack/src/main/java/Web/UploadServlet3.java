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
@WebServlet("/UploadImgs.s")
public class UploadServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDao udao=new UserDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		Part part=request.getPart("file");
		part.getName();//获取上传字段名
		String filename=part.getSubmittedFileName();//获取上传的文件名
		part.getSize();
		//D:\Screen\apache-tomcat-8.5.54\wtpwebapps\com-yc-snack\静态页面(7.10sql)\images
		String webpath="/静态页面(7.10sql)/images";//上传到这个文件夹里面
		ServletContext sc=getServletContext();
		//返回web路径下的磁盘路径
		String photo=sc.getRealPath(webpath);
		part.write(photo+"/"+filename);
		response.getWriter().append(webpath+"/"+filename);

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
