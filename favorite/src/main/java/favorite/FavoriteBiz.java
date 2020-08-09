package favorite;

import org.apache.ibatis.session.SqlSession;

import favorite.bean.favorite;
import favorite.bean.tag;
import favorite.bean.tagfavorite;
import favorite.dao.FavoriteMapper;
import favorite.dao.TagFavoriteMapper;
import favorite.dao.TagMapper;
import favorite.uitl.MyBatisHelper;

public class FavoriteBiz {

	
	public void addFavorite(favorite f) {
		
		SqlSession session=MyBatisHelper.openSession();
		FavoriteMapper fm=session.getMapper(FavoriteMapper.class);
		TagMapper tm=session.getMapper(TagMapper.class);
		TagFavoriteMapper tfm=session.getMapper(TagFavoriteMapper.class);
		
		try {
			//添加链接到favorite类
			fm.insert(f);
			System.out.println(fm.insert(f));
			//拆分类ftags
			String[] tags=f.getFtags().split("[，,、;]");
			System.out.println("==============================="+tags);
			for (String tag : tags) {
				tag tagObj=new tag();
				//直接修改分类的数量
				if(tm.updateCount(tag)==0) {
					//如果返回的结果为0，则没有该记录
					tagObj.setTname(tag);
					tm.insert(tagObj);
					System.out.println("-----------------------"+tag);
				}else {
					//如果返回的结果为1，则修改成功
					//并且查询对应的tag对象
					tagObj=tm.selectByName(tag);
				}
				
				tagfavorite tf=new tagfavorite();
				tf.setTid(tagObj.getTid());
				tf.setFid(f.getFid());
				tfm.insert(tf);
				
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
	}
}
