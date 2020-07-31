package com.web.curation.controller;

import java.io.File;
import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.curation.config.JwtUtil;
import com.web.curation.model.Profile;
import com.web.curation.service.ProfileService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/profile")
public class ProfileController {
	private static final String  SAVE_PATH = "C:\\Users\\multicampus\\Desktop\\s03p12d106-backend-jingi\\backend\\src\\main\\resources\\static\\img\\profile/";
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ProfileService service;
	
	@Autowired
	JwtUtil jwtutil;
	
	@ApiOperation(value = "프로필 정보 반환", response = Profile.class)
	@GetMapping("{userno}")
	public Object detailProfile(@PathVariable int userno) {
		Profile profile = service.detailProfile(userno);
		
		if(profile != null) {
			profile.setImgurl("img/profile/" + profile.getImgurl() );
			return new ResponseEntity<Profile>(profile, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "새로운 프로필을 등록한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeProfile(HttpServletRequest request) throws JsonMappingException, JsonProcessingException{
		
		
		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
		MultipartFile imgFiles = mrequest.getFile("profileImage");
		System.out.println(mrequest.getFileNames());
		System.out.println(imgFiles.getOriginalFilename());
		System.out.println(request.getParameter("Comment"));
		Profile profile = new Profile();
		profile.setComment(request.getParameter("Comment"));
		profile.setUserno(Integer.parseInt(request.getParameter("userno")));
		
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
		MultipartFile imgFiles = mrequest.getFile("profileImage");
		Profile pre = service.detailProfile(Integer.parseInt(request.getParameter("userno")));
		
		Profile profile = new Profile();
		profile.setComment(request.getParameter("Comment"));
		profile.setUserno(Integer.parseInt(request.getParameter("userno")));
		File file = new File(SAVE_PATH + pre.getImgurl());
		if(file.exists() == true) {
			file.delete();
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
		
		if(service.updateProfile(profile)) {
			System.out.println("프로필 업데이트 성공");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
				
	}
	
	@ApiOperation(value = "userno에 해당하는 프로필 정보를 삭제한다.")
	@DeleteMapping
	public ResponseEntity<String> deleteProfile(@RequestBody Profile profile){
		System.out.println("삭제");
		
		File file = new File(profile.getImgurl());
		if(file.exists() == true) {
			file.delete();
		}
		
		if(service.deleteProfile(jwtutil.getUserno())) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
