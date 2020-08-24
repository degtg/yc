package com.yc.LxBlog.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yc.LxBlog.bean.Result;
import com.yc.LxBlog.bean.User;
import com.yc.LxBlog.biz.BizException;
import com.yc.LxBlog.biz.UserBiz;

@Controller //默认控制器方法是执行页面跳转
public class UserAction {

	@Resource
	private UserBiz ubiz;
	/**
	 * 注册：表单提交==》页面跳转
	 * Errors报错所有的验证错误信息，默认会推送页面
	 * @param user
	 */
	@PostMapping("reg.do")
	public String register(@Valid User user,Errors errors,Model m) {
		if(errors.hasErrors()) {
			m.addAttribute("errors", asMap(errors));
			m.addAttribute("user", user);
			return "reg";
		}
		try {
			ubiz.register(user);
		} catch (BizException e) {
			e.printStackTrace();
			errors.rejectValue("account", "account", e.getMessage());
			m.addAttribute("errors", asMap(errors));
			m.addAttribute("user", user);
			return "reg";
		}
		return "redirect:/";
		
	}
	/**
	 * 将所有的字段验证写入到一个map
	 * @param errors
	 * @return
	 */
	private Map<String,String> asMap(Errors errors) {
		if(errors.hasErrors()) {
			Map<String,String> ret=new HashMap<String,String>();
			for(FieldError fe :errors.getFieldErrors()) {
				ret.put(fe.getField(), fe.getDefaultMessage());
				
			}
			return ret;
		}else {
			return null;
		}
	}

	@GetMapping("toreg")
	public String toreg() {
		return "reg";
		
	}
	
	/**
	 * 登录，ajax提交
	 * @param user
	 */
	@PostMapping("login.do")
	public Result login(User user,HttpSession session) {
		try {
			User dbuser=ubiz.login(user);
			session.setAttribute("loginedUser", dbuser);
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(e.getMessage());
		}
		
		return new Result(1,"登录成功");
		
	}
}
