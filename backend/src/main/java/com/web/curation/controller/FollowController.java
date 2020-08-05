package com.web.curation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.Follow;
import com.web.curation.model.User;
import com.web.curation.service.FollowService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"},maxAge = 6000)
@RestController
@RequestMapping("/follow")
public class FollowController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	FollowService folService;
	
	@Autowired
	UserService user;
	
	@ApiOperation(value = "팔로우한 유저 정보 반환" , response = List.class)
	@GetMapping("/following/{userno}")
	public ResponseEntity<List<User>> followList (@PathVariable int userno){
		List<Follow> f = folService.selectFollowingList(userno);
		List<User> followingList = new ArrayList<User>();
		for(Follow fol : f) {
			User u = user.getUserByUserno(fol.getProno());
			u.setPw("");
			followingList.add(u);
		}
		
		return new ResponseEntity<List<User>>(followingList, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "나를 팔로우한 유저 정보 반환" , response = List.class)
	@GetMapping("/follower/{userno}")
	public ResponseEntity<List<User>> followerList (@PathVariable int userno){
		List<Follow> f = folService.selectFollowerList(userno);
		List<User> followerList = new ArrayList<User>();
		for(Follow fol : f) {
			User u = user.getUserByUserno(fol.getUserno());
			u.setPw("");
			followerList.add(u);
		}
		
		return new ResponseEntity<List<User>>(followerList, HttpStatus.OK);
		
	}
	
	@ApiOperation(value= "팔로우 하기", response = String.class)
	@PostMapping
	public ResponseEntity<String> following (@RequestBody Follow follow){
		if(folService.insertFollow(follow)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "언팔 하기", response = String.class)
	@DeleteMapping
	public ResponseEntity<String> unfollow(@RequestBody Follow follow){
		if(folService.deleteFollow(follow)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} 
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
}
