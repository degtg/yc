package springmvc.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.damai.bean.DmProduct;
import com.yc.damai.dao.DmProductMapper;

@RestController("/pAction")
public class ProductAction {
	
	@Resource
	private DmProductMapper pm;
	
	/**
	 * Model ==>数据模型==》Map集合可以替代Model
	 * 返回Model必须是页面跳转方式才可以
	 * @param dp
	 * @param m
	 * @return
	 */
	@GetMapping(path="product.do",params="op=query")
	public Map<String,Object> query(DmProduct dp,Map<String,Object> m){
		m.put("list", pm.selectForHot());
		return m;
		
	}
}
