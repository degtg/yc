package Web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class LoginFilter
 * 1.资源绝对路径 /hello.s
 * 2.目录绝对路径： /page/ 匹配所有的资源 /page/1 page/a 
 * 3.文件后缀名  ：   *.jsp , *.jpg *.do *.s
 * 
 * 目录绝对路径 +文件后缀名  是非法的 /page/*.jsp
 * 
 */
@WebFilter(urlPatterns = {"*.jsp ","*.s","*.do"})
public class LoginFilter implements Filter {
	//销毁方法
	public void destroy() {
		System.out.println("=====LoginFilter=destroy==========");
	}

	/**
	 * 执行过滤
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*过滤方法 主要是对request和response进行一些处理，然后交给下一个过滤器或Servlet处理*/
		//设置请求对象的字符集==>post
		request.setCharacterEncoding("UTF-8");
				//设置响应对象的字符集
		response.setCharacterEncoding("UTF-8");
				//设置网页的字符集
		response.setContentType("text/html;charset=utf-8");
		chain.doFilter(request, response);//交给下一个过滤器或servlet处理
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//  /*初始化方法  接收一个FilterConfig类型的参数 该参数是对Filter的一些配置*/
		System.out.println("====LoginFilter=init=======");
	}

}
