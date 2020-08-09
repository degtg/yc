package favorite.dao;


import org.apache.ibatis.annotations.Insert;

import favorite.bean.tagfavorite;

public interface TagFavoriteMapper {

	@Insert("insert into tagfavorite values(#{tid},#{fid})")
	int insert(tagfavorite f);
}
