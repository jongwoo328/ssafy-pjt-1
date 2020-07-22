package com.web.curation.controller.qna;

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
import com.web.curation.service.qna.QnaService;

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
	
	@ApiOperation(value = "모든 게시글의 정보를 반환", response = List.class)
	@GetMapping
	public ResponseEntity<List<Qna>> retrieveQna() throws Exception {
		logger.debug("retrieveQna - 호출");
		return new ResponseEntity<List<Qna>>(service.retrieveQna(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Qna.class)    
	@GetMapping("{no}")
	public ResponseEntity<Qna> detailQna(@PathVariable int qnano) {
		logger.debug("writeQna - 호출");
		return new ResponseEntity<Qna>(service.detailQna(qnano), HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQna(@RequestBody Qna qna){
		logger.debug("writeQna - 호출");
		System.out.println("qna 작성");
		System.out.println(qna);
		if(service.writeQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{qnano}")
	public ResponseEntity<String> updateQna(@RequestBody Qna qna){
		logger.debug("updateQna 호출");
		logger.debug("" + qna);
		System.out.println("qna 수정");
		System.out.println(qna);
		if(service.updateQna(qna)) {
			return new ResponseEntity<String> (SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String> (FAIL, HttpStatus.NO_CONTENT);
	}
	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{qnano}")
	public ResponseEntity<String> deleteQna(@PathVariable int qnano) {
		logger.debug("deleteQna - 호출");
		System.out.println("삭제");
		if (service.deleteQna(qnano)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
