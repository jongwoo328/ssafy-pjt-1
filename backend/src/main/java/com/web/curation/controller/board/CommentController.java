package com.web.curation.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.service.board.CommentService;

@CrossOrigin(origins = {"*"},maxAge = 6000)
@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
}
