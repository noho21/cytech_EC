package jp.co.sss.cytech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.cytech.service.UserService;

@Controller
public class SessionController {
	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String login() {
		return "session/login";
	}
}
