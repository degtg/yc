package favorite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import favorite.bean.favorite;

public interface FavoriteMapper {

	@Insert("insert into favorite values(null,#{flabel},#{furl},#{fdesc},#{ftags})")
	@Options(useGeneratedKeys=true,keyColumn="fid",keyProperty="fid")
	int insert(favorite f);
	
	//报错没有这个属性的时候，可以添加@Param("tid")注解
	List<favorite> selectByTid(@Param("tid")Integer tid);//tid可能==null,要用包装其类型
	
}
