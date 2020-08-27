package favorite.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;

import favorite.dao.FavoriteMapper;
import favorite.dao.TagMapper;
import favorite.uitl.MyBatisHelper;

/**
 * Servlet implementation class QueryTagServlet
 */
@WebServlet("/QueryFavs.do")
public class QueryFavsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html ;charset=uft-8");
		SqlSession session=MyBatisHelper.openSession();
		FavoriteMapper tm=session.getMapper(FavoriteMapper.class);
		
		String sTid=request.getParameter("tid");
		Integer iTid;
		if(sTid==null || sTid.trim().isEmpty()||"null".equals(sTid)) {
			iTid=null;
		}else {
			iTid=Integer.valueOf(sTid);
		}
		
		Gson gson=new Gson();
		String json=gson.toJson(tm.selectByTid(iTid));
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
