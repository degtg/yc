package Web;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;



@MultipartConfig()
@WebServlet("/UploadBigServlet.do")
public class UploadBigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part part = request.getPart("file");
		// String webpath = "/productimg";

		// 获取工程根目录 tomcat服务/webapps/工程名
		String webpath = "/静态页面(7.10sql)/../图片素材/";
		
		String diskpath = request.getServletContext().getRealPath(webpath);
		
		diskpath=diskpath+part.getSubmittedFileName();
		webpath += "/"+ part.getSubmittedFileName();
		webpath = webpath.substring(1);
		System.out.println(diskpath);
		System.out.println(webpath);
		part.write(diskpath);

		//保存数据库
		Gson gson = new Gson();
		//Result res = new Result(1, "文件上传成功!", webpath);
		Result res = new Result( 1, "文件上传成功!", "../图片素材/" + part.getSubmittedFileName());
		String json = gson.toJson(res);
		response.getWriter().append(json);

	}

}
