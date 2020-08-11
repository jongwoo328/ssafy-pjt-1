package com.web.curation.controller;

import java.util.List;

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

import com.web.curation.model.Qna;
import com.web.curation.model.User;
import com.web.curation.service.QnaService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"},maxAge = 6000)
@RestController
@RequestMapping("/qna")
public class QnaController {
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnaService service;
	
	@Autowired
	private UserService user;
	
	@ApiOperation(value = "모든 게시글의 정보를 반환", response = List.class)
	@GetMapping
	public ResponseEntity<List<Qna>> retrieveQna() throws Exception {
		logger.debug("retrieveQna - 호출");
		List<Qna> list = service.retrieveQna();
		
		for(Qna q : list) {
			User u = user.getUserByUserno(q.getUserno());
			q.setQwriter(u.getName());
		}
		
		
		return new ResponseEntity<List<Qna>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저의 qna 정보를 반환", response = List.class)
	@GetMapping("{userno}")
	public ResponseEntity<List<Qna>> userQna(@PathVariable int userno) throws Exception {
		
		return new ResponseEntity<List<Qna>>(service.selectQnaByUserno(userno), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Qna.class)    
	@GetMapping("/detail/{qnano}")
	public ResponseEntity<Qna> detailQna(@PathVariable int qnano) {
		Qna q = service.detailQna(qnano);
		User u = user.getUserByUserno(q.getUserno());
		q.setQwriter(u.getName());
		return new ResponseEntity<Qna>(q, HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQna(@RequestBody Qna qna){
		logger.debug("writeQna - 호출");
		User u = user.getUserByUserno(qna.getUserno());
		qna.setQwriter(u.getName());
		
		
		
		if(service.writeQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{qnano}")
	public ResponseEntity<String> updateQna(@RequestBody Qna qna){
		logger.debug("updateQna 호출");
		logger.debug("" + qna);
		if(service.updateQna(qna)) {
			return new ResponseEntity<String> (SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String> (FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("answer/{qnano}")
	public ResponseEntity<String> resistAnswerQna(@RequestBody Qna qna){

		if(service.resistAnswerQna(qna)) {
			return new ResponseEntity<String> (SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String> (FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("answer/update/{qnano}")
	public ResponseEntity<String> updateAnswerQna(@RequestBody Qna qna){

		if(service.updateAnswerQna(qna)) {
			return new ResponseEntity<String> (SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String> (FAIL, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{qnano}")
	public ResponseEntity<String> deleteQna(@PathVariable int qnano) {
		logger.debug("deleteQna - 호출");
		if (service.deleteQna(qnano)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}
