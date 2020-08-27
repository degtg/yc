package favorite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import favorite.bean.tag;

public interface TagMapper {
	@Insert("insert into tag values(null,#{tname},1)")
	@Options(useGeneratedKeys=true,keyColumn="tid",keyProperty="tid")
	int insert(tag f);
	
	@Update("update tag set tcount=tcount+1 where tname=#{tname}")
	int updateCount(String tag);

	@Select("select * from tag where tname=#{tname}")
	tag selectByName(String tag);
	
	@Select("select tname from tag")
	tag selectTagName(String tag);
	
	@Select("select * from tag ")
	List<tag> selectAll();
}
