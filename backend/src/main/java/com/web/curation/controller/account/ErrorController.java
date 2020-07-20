package com.web.curation.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	@GetMapping("index")
	public String qna() {
		System.out.println("sdfsdfsd");
		return "index";
	}
}
