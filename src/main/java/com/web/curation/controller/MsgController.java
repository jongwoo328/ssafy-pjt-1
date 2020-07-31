package com.web.curation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.service.MsgService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/msg")
public class MsgController {
	
	@Autowired
	MsgService msgService;
	
}
