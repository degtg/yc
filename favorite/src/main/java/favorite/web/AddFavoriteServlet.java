package favorite.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;

import favorite.FavoriteBiz;
import favorite.bean.favorite;
import favorite.dao.FavoriteMapper;
import favorite.uitl.MyBatisHelper;

public class AddFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flabel = request.getParameter("flabel");
		String furl = request.getParameter("furl");
		String fdesc = request.getParameter("fdesc");
		String ftags = request.getParameter("ftags");

		System.out.println("==========="+flabel);
		System.out.println("==========="+furl);
		System.out.println("==========="+fdesc);
		System.out.println("==========="+ftags);
		
		FavoriteBiz fb = new FavoriteBiz();
		favorite f = new favorite();
		f.setFlabel(flabel);
		f.setFurl(furl);
		f.setFdesc(fdesc);
		f.setFtags(ftags);
		try {
			/*
			 * Gson gson=new Gson(); String json=gson.toJson(fb.addFavorite(f));
			 * response.getWriter().append(json);
			 */
			BeanUtils.populate(f, request.getParameterMap());
			fb.addFavorite(f);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("添加成功");
		} catch (Exception e) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("添加失败");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
