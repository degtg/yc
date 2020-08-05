package Web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		Map<String,Object> a=new HashMap<>();
		System.out.println(a.toString());
		a.put("mno", 1);
		Map<String,Object> b=new HashMap<>();
		b.put("mno", 1);
		//泛型
		List<Map<String,Object>> list=new ArrayList<>();
		list.add(a);
		list.add(b);
		System.out.println(list.toString());
	}

}
