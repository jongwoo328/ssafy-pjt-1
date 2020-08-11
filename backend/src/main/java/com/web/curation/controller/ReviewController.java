package com.web.curation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.Review;
import com.web.curation.model.User;
import com.web.curation.service.ProfileService;
import com.web.curation.service.ReviewService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"},maxAge = 6000)
@RestController
@RequestMapping("/review")
public class ReviewController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ReviewService revService;
	
	@Autowired
	private UserService user;
	
	@Autowired
	private ProfileService profile;
	
	@ApiOperation(value = "전체 리뷰 목록을 가져온다.", response = String.class)
	@GetMapping("/{servno}")
	public Object retrieveReview(@PathVariable int servno) {
		List<Review> list = revService.retrieveReview(servno);
		if (list == null) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		
		for(Review rev : list) {
			User u = user.getUserByUserno(rev.getUserno());
			rev.setWriter(u.getName());
			rev.setImgurl("img/profile/"+ profile.detailProfile(rev.getUserno()).getImgurl());
		}
		
		
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 리뷰 내용을 가져온다.", response = String.class)
	@GetMapping("/detail/{revno}")
	public Object detailReview(@PathVariable int revno) {
		Review review = revService.detailReview(revno);
		
		if (review == null) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		
		User u = user.getUserByUserno(review.getUserno());
		review.setWriter(u.getName());
		review.setImgurl("img/profile/"+ profile.detailProfile(review.getUserno()).getImgurl());
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
	
	@ApiOperation(value = "리뷰를 작성한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeReview(@RequestBody Review review) {
		if (revService.writeReview(review)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "리뷰를 수정한다.", response = String.class)
	@PutMapping("{revno}")
	public ResponseEntity<String> updateReview(@RequestBody Review review) {
		if (revService.updateReview(review)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "글번호에 해당하는 리뷰를 삭제한다.", response = String.class)
	@DeleteMapping("{revno}")
	public ResponseEntity<String> deleteReview(@PathVariable int revno) {
		if (revService.deleteReview(revno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	
	
	
}
