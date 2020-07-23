package com.web.curation.controller.fselect;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.controller.qna.QnaController;
import com.web.curation.model.DongCode;
import com.web.curation.model.SidoCode;
import com.web.curation.service.fselect.FselectService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("fselect")
public class FselectController {
	private static final Logger logger = LoggerFactory.getLogger(FselectController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private FselectService service;
	
	@ApiOperation(value = "모든 시/도 정보 전달", response = List.class)
	@GetMapping
	public ResponseEntity<List<SidoCode>> sidoInfo() throws Exception {
		System.out.println("시도 코드 정보 전달");
		return new ResponseEntity<List<SidoCode>>(service.selectSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 시/도의 구/군 정보 전달", response = List.class)
	@GetMapping("{sido}")
	public ResponseEntity<List<SidoCode>> gugunInfo(@PathVariable String sido) throws Exception {
		System.out.println("구군 코드 정보 전달");
		System.out.println(sido);
		return new ResponseEntity<List<SidoCode>>(service.selectGugun(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 구/군의 동 정보 전달", response = List.class)
	@GetMapping("/sido/{gugun}")
	public ResponseEntity<List<DongCode>> dongInfo(@PathVariable String gugun) throws Exception {
		return new ResponseEntity<List<DongCode>> (service.selectDong(gugun), HttpStatus.OK);
	}
}
