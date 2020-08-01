package thread.d0730;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpServletRequest {
	//GET /photo/index.html HTTP/1.1
	private String method;//方法名
	private String requestUri;//请求资源地址
	private String protocol ;//协议
	private Map<String,String> headerMap=new HashMap<>() ;
	private Map<String,String> paramsMap=new HashMap<>() ;
	public HttpServletRequest(String requestText) {
		//完成对报文请求的解析
		String[] lines=requestText.split("\\n");//惊醒数组分割，拆成n行
		String[] items=lines[0].split("\\s");//使用空格拆分
		method=items[0];
	    requestUri=items[1];//uri是抽象的地址，url是具体的地址
		protocol =items[2];
		
		int index=items[1].indexOf("?");
		if(index !=-1) {
			//解析参数
			requestUri=items[1].substring(0,index);
			String paramString=items[1].substring(index+1);
			String[] params=paramString.split("&");
			for (int i = 0; i < params.length; i++) {
				String[] nv=params[i].split("=");
				if(nv.length==1) {
					paramsMap.put(nv[0], "");
					
				}else if(nv.length>1) {
					paramsMap.put(nv[0], nv[1]);
				}
			}
		}
		for (int i = 1; i < lines.length; i++) {
			lines[i]=lines[i].trim();
			if(lines[i].isEmpty()) {
				break;
			}
			items=lines[i].split(":");
			headerMap.put(items[0], items[1].trim());//Map集合保存头域值，头域是键值对的形式。
			
		}
	}
	
	
	//获取请求资源路径
	public String getRequestURI() {
		return requestUri;
	}
	
	//获取请求的方法名
	public String getMethod() {
		return method;
		
	}
	//获取协议版本号
	public String getProtocol() {
		return protocol;
		
	}
	//获取头域， 键值对
	public String getHeader(String name) {
		return headerMap.get(name);
		
	}
	
	public String getParameter(String name) {
		return paramsMap.get(name);
		
	}
	//获取cookie
	/**
	 * 获取请求cookie数据
	 */
	public Cookie[] getCookies() {
		String cookieString =headerMap.get("Cookie");
		if(cookieString==null) {
			return null;
		}else {
			List<Cookie> cookieList=new ArrayList<>();
			String[] sCookies=cookieString.split(";\\s*");
			for (int i = 0; i < sCookies.length; i++) {
				String[] nv=sCookies[i].split("=");
				cookieList.add(new Cookie(nv[0],nv[1]));
			}
			return cookieList.toArray(new Cookie[0]);
		}
		
	}
}
