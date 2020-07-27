package com.web.curation.controller.profile;

import java.io.File;
import java.io.IOException;

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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.curation.controller.qna.QnaController;
import com.web.curation.model.Profile;
import com.web.curation.service.profile.ProfileService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"#"})
@RestController
@RequestMapping("/profile")
public class ProfileController {
	private static final String  SAVE_PATH = "C:/Users/multicampus/Desktop/git/s03p12d106/backend/src/main/resources/static/img/";
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ProfileService service;
	
	@ApiOperation(value = "프로필 정보 반환", response = Profile.class)
	@GetMapping("{userno}")
	public Object detailProfile(@PathVariable int userno) {
		Profile profile = service.detailProfile(userno);
		
		if(profile != null) {
			return new ResponseEntity<Profile>(profile, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "새로운 프로필을 등록한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeProfile(/* @RequestBody Profile profile*/  @RequestParam(value="mf", required = false)MultipartFile mf, String JsonString) throws JsonMappingException, JsonProcessingException{
		
		
		Profile profile  = new ObjectMapper().readValue(JsonString, Profile.class);
		String originalFileName = mf.getOriginalFilename();
		String saveFile = SAVE_PATH + System.currentTimeMillis() + originalFileName;
		
		try {
			mf.transferTo(new File(saveFile));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		profile.setImgurl(saveFile);
		
		System.out.println(saveFile);
		System.out.println(profile.getComment());
		System.out.println(profile.getImgurl());
		System.out.println(profile.getPno());
		System.out.println(profile.getUserno());
		
		if(service.writeProfile(profile)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
		
//		return new ResponseEntity<String>(saveFile, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "userno에 해당하는 프로필 정보를 수정한다.그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{userno}")
	public ResponseEntity<String> updateProfile(@RequestParam(value="file1", required = false)MultipartFile mf, String JsonString) throws JsonMappingException, JsonProcessingException{
		ModelAndView mv = new ModelAndView("uploadView");
		Profile profile  = new ObjectMapper().readValue(JsonString, Profile.class);
		Profile preProfile = service.detailProfile(profile.getUserno());
		
		File file = new File(preProfile.getImgurl());
		if(file.exists() == true) {
			file.delete();
		}
		
		String originalFileName = mf.getOriginalFilename();
		String saveFile = SAVE_PATH + System.currentTimeMillis() + originalFileName;
		
		try {
			mf.transferTo(new File(saveFile));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		profile.setImgurl("saveFile");
		
		if(service.updateProfile(profile)) {
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
		
		if(service.deleteProfile(profile.getUserno())) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
