package thread.d0730;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Tomcat {
	//Servlet容器
	private HashMap<String,	Servlet> servletMap;
	
	public void statrup() throws IOException {
		//服务器初始化Servlet容器==》Map集合==》URL：Servlet对象
		servletMap =new HashMap<>();
		
		servletMap.put("/photo/hello", new HelloServlet());
		//让ToindexServlet成为网站的默认页面
		servletMap.put("/", new ToIndexServlet());
		servletMap.put("/index", new ToIndexServlet());
		servletMap.put("/toindex", new ToIndexServlet());
		//注册cookie servlet
		servletMap.put("/cookie", new CookieServlet());
		servletMap.put("/login.jsp", new LoginPageServlet());
		servletMap.put("/photo/post.do", new PostServlet());

		//启动服务监听8080端口
		//循环生成socket对象
		//使用线程处理浏览器发送的请求
		ServerSocket tomcat=new ServerSocket(8080);
		System.out.println("tomcat 服务器启动  监听8080端口");
		boolean running=true;
		while(running) {
			Socket socket=tomcat.accept();
			
			new Thread() {
				public void run() {
					try {
						System.out.println("接收到请求");
						InputStream in=socket.getInputStream();//其实就是从服务器端发来的数据
						OutputStream out=socket.getOutputStream();//发送给服务器端的数据
						byte[] buffer=new byte[1024];//定义字节数组，相当于缓存
						int count;//定义用于得到实际读取到的字节数
						count=in.read(buffer);//把得到的数据读到buffer数组中去
						if(count>0) {
							
							String requestText=new String(buffer,0,count);//把字节转成String 类型，从0到count
							System.out.println(requestText);//打印请求报文
							
							//解析请求报文
							HttpServletRequest request=buildRequest(requestText);
							HttpServletResponse response=new HttpServletResponse(out);;
							//使用资源地区分动态请求和静态请求
							//使用资源地址到Servlet容器中获取Servlet对象
							String uri=request.getRequestURI();
							Servlet servlet=servletMap.get(uri);
							if(servlet !=null) {
								//使用Servlet处理动态请求
								servlet.service(request, response);
							}else {
								//处理静态请求
								processStaticRequest(request,out);
								
							}
									
						}
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
			
			
		}
		tomcat.close();
	}

	
	public void showdown() {
		
	}
	
	/**
	 * 解析请求对象
	 * @param requestText
	 * @return
	 */
	private HttpServletRequest buildRequest(String requestText) {
		return new HttpServletRequest(requestText);
		
	}
	
	public static void main(String[] args) throws IOException {
		new Tomcat().statrup();
	}
	
	/**
	 * 处理静态请求
	 * @param out 
	 * @param request 
	 * @throws IOException 
	 */
	public void processStaticRequest(HttpServletRequest request, OutputStream out) throws IOException {
		String webpath=request.getRequestURI();
		String contentType;
		
		//结果码
		int statusCode=200;
		//定义磁盘文件路径
		String path= "D:/Tomcat/webapps/" + webpath;
		File file=new File(path);
		if(!file.exists()) {
			statusCode=404;
			path="D:/Tomcat/webapps/photo/404.html";
			
			
		}
	//application/javascript
		if(webpath.endsWith(".js")) {
			contentType="application/javascript; charset=utf-8";
		}else if(webpath.endsWith(".css")) {
			contentType="text/css; charset=utf-8";
		}else {
			contentType="text/html; charset=utf-8";
		}
		//响应头行
		out.write(("HTTP/1.1 "+statusCode+" OK\n").getBytes());
		//响应头域
		out.write(("Content-Type: "+contentType+" \n").getBytes());
		//空行CRLF
		out.write("\n".getBytes());
		//实体
		//out.write("<h1> hello world</h1>".getBytes());
		//out.write("<a href='https://uland.taobao.com'>惦记</a>".getBytes());
		FileInputStream fis=new FileInputStream(path);
		byte[] buffer=new byte[1024];
		int count;
		while( (count=fis.read(buffer))>0 ) {
			out.write(buffer,0,count);
		}
		
		fis.close();
	}
	
	
}
