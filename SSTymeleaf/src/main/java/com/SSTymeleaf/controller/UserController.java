package com.SSTymeleaf.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.SSTymeleaf.DTO.UserVo;
import com.SSTymeleaf.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	/**
	 * localhost:8080 시 login 으로 redirect
	 * 
	 * @return
	 */

	@GetMapping
	public String root() {
		System.out.println("root");
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String signUpForm() {
		System.out.println("login");
		return "login";
	}

	@GetMapping("/access_denied")
	public String accessDenied() {
		return "access_denied";
	}

	// @PostMapping("/signup")
	@GetMapping("signup")
	public String singUp(UserVo userVo) {
		System.out.println("signup");
		System.out.println(userVo);
		userService.joinUser(userVo);
		return "redirect:/login";
	}

	@GetMapping("/user_access")
	public String userAccess(Model model, Authentication authentication) {
		System.out.println("2");
		UserVo userVo = (UserVo) authentication.getPrincipal();
		model.addAttribute("info", userVo.getUserId() + "의 " + userVo.getUserName() + "님");
		return "user_access";
	}

}
