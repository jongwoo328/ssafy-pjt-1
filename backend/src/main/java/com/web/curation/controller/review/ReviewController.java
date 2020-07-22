package com.web.curation.controller.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.service.pay.PayService;
import com.web.curation.service.review.ReviewService;

@CrossOrigin(origins = {"*"},maxAge = 6000)
@RestController
@RequestMapping("/review")
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ReviewService revService;
	
	@Autowired
	private PayService payService;
	
	
}
