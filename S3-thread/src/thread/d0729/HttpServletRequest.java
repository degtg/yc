package thread.d0729;

import java.util.HashMap;
import java.util.Map;

public class HttpServletRequest {
	//GET /photo/index.html HTTP/1.1
	private String method;//方法名
	private String requestUri;//请求资源地址
	private String protocol ;//协议
	private Map<String,String> headerMap=new HashMap<>() ;
	
	public HttpServletRequest(String requestText) {
		//完成对报文请求的解析
		String[] lines=requestText.split("\\n");//惊醒数组分割，拆成n行
		String[] items=lines[0].split("\\s");//使用空格拆分
		method=items[0];
	    requestUri=items[1];//uri是抽象的地址，url是具体的地址
		protocol =items[2];
		
		
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
		return name;
		
	}
	
	public String getParameter(String name) {
		return name;
		
	}
	//获取cookie
	public Cookie[] getCookies() {
		return null;
		
	} 
}
