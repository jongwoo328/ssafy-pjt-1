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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.ConnectorService;
import com.web.curation.model.Pay;
import com.web.curation.service.PayService;
import com.web.curation.service.ServiceService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"},maxAge = 6000)
@RestController
@RequestMapping("/pay")
public class PayController {
	private static final Logger logger = LoggerFactory.getLogger(PayController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PayService service;
	
	@Autowired
	private ServiceService serv;
	
	@ApiOperation(value = "새로운 결제 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Pay pay){
		logger.debug("insert - 호출");
		System.out.println("결제 정보 저장");
		System.out.println(pay);
		if(service.insert(pay)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저의 결제 내역 정보를 반환한다. ", response = List.class)
	@GetMapping("/{userno}")
	public Object payList(@PathVariable int userno) {
		List<Pay> payList = service.userPay(userno);
		if(payList != null) {
			for(Pay p : payList) {
				ConnectorService c = serv.detailService(p.getServno());
				p.setPrice(c.getPrice());
				p.setServname(c.getServname());
			}
			
			return new ResponseEntity<List<Pay>>(payList, HttpStatus.OK);
		} else {
			return new ResponseEntity<String> (FAIL, HttpStatus.OK);
		}
		
	}
	
	@ApiOperation(value = "결제를 취소한다." , response = String.class)
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody Pay pay){
		System.out.println("결제 취소");
		System.out.println(pay);
		if(service.cancelPay(pay)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}
