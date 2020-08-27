package favorite.dao;

import org.apache.ibatis.session.SqlSession;

import org.junit.Test;

import favorite.FavoriteBiz;
import favorite.bean.favorite;
import favorite.bean.tag;
import favorite.uitl.MyBatisHelper;

public class BastTest {

	@Test
	public void test1() {
		SqlSession session=MyBatisHelper.openSession();
		FavoriteMapper fm=session.getMapper(FavoriteMapper.class);
		TagMapper tm=session.getMapper(TagMapper.class);
		TagFavoriteMapper tfm=session.getMapper(TagFavoriteMapper.class);
		
		favorite f=new favorite();
		f.setFlabel("淘宝");
		f.setFurl("taobao.com");
		f.setFdesc("想要的都在这里");
		f.setFtags("购物,生活");
		
		  tag t=new tag(); 
		  t.setTname("购物");
		  t.setTcount(1); 
		  
		  fm.insert(f);
		  tm.insert(t);
		 
		  session.commit();
		  session.close();

	}
	
	@Test
	public void test2() {
		FavoriteBiz fb=new FavoriteBiz();
		favorite f=new favorite();
		f.setFlabel("淘宝");
		f.setFurl("taobao.com");
		f.setFdesc("想要的都在这里");
		f.setFtags("购物,生活");
		fb.addFavorite(f);
	}
	
	@Test
	public void test3() {
		SqlSession session=MyBatisHelper.openSession();
		FavoriteMapper fm=session.getMapper(FavoriteMapper.class);
		fm.selectByTid(null);
		fm.selectByTid(1);
		fm.selectByTid(0);
		
	}
	
	
	@Test
	public void test4() {
		
		FavoriteBiz fb=new FavoriteBiz();
		favorite f=new favorite();
		f.setFlabel("百度");
		f.setFurl("baidu.com");
		f.setFdesc("有事请找我");
		f.setFtags("");
		fb.addFavorite(f);
	}
	

}
