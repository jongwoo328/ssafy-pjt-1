package com.web.curation.controller.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.service.msg.MsgService;

@CrossOrigin(origins = { "*" })
@RestController
public class MsgController {
	
	@Autowired
	MsgService msgService;
	
}
