package com.yc.user.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserAciton {

	@GetMapping("user")
	public String user() {
		return "user";
	}
}
