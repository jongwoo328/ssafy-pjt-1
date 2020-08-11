package com.web.curation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = {"*"},maxAge = 6000)
@Controller
public class ErrorController {
	
	@GetMapping("/error")
	public String qna() {
		return "error.html";
	}
}
