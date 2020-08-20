package com.web.curation.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.web.curation.config.JwtUtil;
import com.web.curation.model.ConnectorService;
import com.web.curation.model.Follow;
import com.web.curation.model.Profile;
import com.web.curation.model.Review;
import com.web.curation.model.User;
import com.web.curation.service.FollowService;
import com.web.curation.service.PayService;
import com.web.curation.service.ProfileService;
import com.web.curation.service.ReviewService;
import com.web.curation.service.ServiceService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/profile")
public class ProfileController {
	private static String  SAVE_PATH = "/var/www/html/img/profile/";
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ProfileService service;
	
	@Autowired
	private UserService user;
	
	@Autowired
	private ServiceService connectorServ;
	
	@Autowired
	JwtUtil jwtutil;
	
	@Autowired
	PayService pay;
	
	@Autowired
	ReviewService rev;
	
	@Autowired
	FollowService fol;
	
	@ApiOperation(value = "프로필 정보 반환", response = Profile.class)
	@GetMapping("/username={username}&userno={userno}")
	public Object detailProfile(@PathVariable String username, @PathVariable int userno) {
		
		
		User u = user.getUserByName(username);
		
		
		if(u != null) {
			Profile profile = service.detailProfile(user.getUserByName(username).getUserno());
			
			if(profile != null) {
				profile.setImgurl("img/profile/" + profile.getImgurl() );
				profile.setServList(connectorServ.selectServiceByUserno(user.getUserByName(username).getUserno()));
				profile.setIspro(u.isIspro());
				if(profile.getServList() != null) {
					List<Review> revList = rev.totalReview();
					for(ConnectorService s : profile.getServList()) {
						s.setImgurl("img/service/" + s.getImgurl());
						double sum = 0.0;
						int sno = s.getServno();
						int count = 0;
						for(Review r : revList) {
							if(sno == r.getServno()) {
								sum += r.getPoint();
								count ++;
							}
						}
						if(count == 0) {
							sum = 0.0;
						} else {
							sum = sum / count;
						}
						s.setAvgpoint(Math.round((sum) * 10)/ 10.0);
						s.setPayCount(pay.payCount(s.getServno()));
					}
					
				}
				Follow follow = new Follow();
				follow.setUserno(userno);
				follow.setProno(u.getUserno());
				if(fol.selectFollow(follow) != null) {
					profile.setCheckfollow(true);
				} else {
					profile.setCheckfollow(false);					
				}
				return new ResponseEntity<Profile>(profile, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(FAIL , HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<String>(FAIL , HttpStatus.OK);
			
		}
		
		
		
			
		
	}
	
	
	@ApiOperation(value = "새로운 프로필을 등록한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeProfile(HttpServletRequest request) throws JsonMappingException, JsonProcessingException{
		
		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
		MultipartFile imgFiles = mrequest.getFile("profileImage");
		Profile profile = new Profile();
		profile.setComment(request.getParameter("Comment"));
		profile.setUserno(Integer.parseInt(request.getParameter("userno")));
		
		if(imgFiles != null) {
			String originalFileName = imgFiles.getOriginalFilename();
			String cur_time = new String("");
			cur_time += System.currentTimeMillis();
			String saveFile = SAVE_PATH + cur_time + originalFileName;
			try {
				imgFiles.transferTo(new File(saveFile));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			profile.setImgurl(cur_time + originalFileName);
		} else {
			profile.setImgurl("null.png");
		}
		
		
		if(service.writeProfile(profile)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
		
//		return new ResponseEntity<String>(saveFile, HttpStatus.OK);
		
	}
	
	
	
	@ApiOperation(value = "userno에 해당하는 프로필 정보를 수정한다.그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateProfile(HttpServletRequest request) throws JsonMappingException, JsonProcessingException{
		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
		
		Profile pre = service.detailProfile(Integer.parseInt(request.getParameter("userno")));
		Profile profile = new Profile();
		profile.setComment(request.getParameter("Comment"));
		profile.setUserno(Integer.parseInt(request.getParameter("userno")));
		MultipartFile imgFiles = mrequest.getFile("profileImage");
		
		if(imgFiles != null) {
			
			if(pre.getImgurl().equals("null.png")) {
				
			} else {
				File file = new File(SAVE_PATH + pre.getImgurl());
				if(file.exists() == true) {
					file.delete();
				}				
			}
			
			String originalFileName = imgFiles.getOriginalFilename();
			String cur_time = new String("");
			cur_time += System.currentTimeMillis();
			String saveFile = SAVE_PATH + cur_time + originalFileName;
			try {
				imgFiles.transferTo(new File(saveFile));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			profile.setImgurl(cur_time + originalFileName);
			
		} else {
			profile.setImgurl(pre.getImgurl());
		}
		
		
		if(service.updateProfile(profile)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
				
	}
	
	@ApiOperation(value = "userno에 해당하는 프로필 정보를 삭제한다.")
	@DeleteMapping
	public ResponseEntity<String> deleteProfile(@RequestBody Profile profile, HttpServletRequest request) throws URISyntaxException{
		
		
		if(profile.getImgurl().equals("null.png")) {
			
		} else {
			File file = new File(SAVE_PATH + profile.getImgurl());
			if(file.exists() == true) {
				file.delete();
			}			
		}
		
		
		if(service.deleteProfile(profile.getUserno())) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
