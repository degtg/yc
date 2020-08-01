package thread.d0729;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpServletResponse {
	
	
	
	//通过socket获取的输出流
	private OutputStream out;
	private int status;
	private String msg;
	
	public HttpServletResponse(OutputStream out) {
		this.out=out;
	}
	
	//字符串输出流，将输出的内容保存到一个字符串中 用于保存数据
	private CharArrayWriter caw=new CharArrayWriter();
	//处理流 给用户输入的
	private PrintWriter pw=new PrintWriter(caw);
	
	//获取响应输出流 临时保存servlet输出的流
	public PrintWriter getWriter() {
		return pw;
		
	}
	//设置结果码和结果码消息
	public void setStatus(int status,String msg) {
		this.status=status;
		this.msg=msg;
	}
	
	//将响应报文推送给浏览器
	public void flushBuffer() throws IOException {
		
		//响应头行
		out.write(("HTTP/1.1 " + status + " "+ msg + "\n").getBytes());
		//响应头域
		out.write(("Content-Type: text/html; charset=utf-8\n").getBytes());
		//空行CRLF
		out.write("\n".getBytes());
		//实体
		out.write(caw.toString().getBytes());
	}
	
	
	
}


