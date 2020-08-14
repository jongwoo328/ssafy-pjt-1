package com.web.curation.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.web.curation.model.Review;
import com.web.curation.service.PayService;
import com.web.curation.service.ReviewService;
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
	
	@Autowired
	private ReviewService rev;
	
	@ApiOperation(value = "새로운 결제 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Pay pay){
		logger.debug("insert - 호출");
		
		if(service.searchPayed(pay) != null) {
			return new ResponseEntity<String> ("payed", HttpStatus.OK);
		}
		
		if(service.insert(pay)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저의 결제 내역 정보를 반환한다. ", response = List.class)
	@GetMapping("/{userno}")
	public Object payList(@PathVariable int userno) {
		List<Pay> payList = service.userPay(userno);
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		Date date = calendar.getTime();
		String time1 = format1.format(date);
		if(payList != null) {
			for(Pay p : payList) {
			
				p.setImgurl("img/service/" + serv.detailService(p.getServno()).getImgurl());
				p.setPayCount(service.payCount(p.getServno()));
				ConnectorService c = serv.detailService(p.getServno());
				p.setPrice(c.getPrice());
				p.setServname(c.getServname());
				if(p.getPdate().compareTo(time1) > 0) {
					p.setCancelcheck(true);
				} else {
					p.setCancelcheck(false);
				}
				
				p.setPdate(p.getPdate().substring(2,10));
				
				List<Review> revList = rev.totalReview();
				int count = 0;
				double sum = 0.0;
				for(Review r : revList) {
					if(r.getServno() == p.getServno()) {
						sum += r.getPoint();
						count++;
					}
				}
				if(count == 0) {
					p.setAvgpoint(0.0);
				} else {

					p.setAvgpoint(Math.round((sum/count) * 10) / 10.0);
				}
				
			}
			
			return new ResponseEntity<List<Pay>>(payList, HttpStatus.OK);
		} else {
			return new ResponseEntity<String> (FAIL, HttpStatus.OK);
		}
		
	}
	
	@ApiOperation(value = "결제를 취소한다." , response = String.class)
	@DeleteMapping("/{payno}")
	public ResponseEntity<String> delete(@PathVariable int payno){
		Pay pay = new Pay();
		pay.setPayno(payno);
		if(service.cancelPay(pay)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "프로가 서비스 결제를 취소한다.", response = String.class)
	@DeleteMapping("/pro/servno={servno}&userno={userno}")
	public ResponseEntity<String> deletePay(@PathVariable int servno, @PathVariable int userno){
		Pay pay = new Pay();
		pay.setServno(servno);
		pay.setUserno(userno);
		
		if(service.procancelPay(pay)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}
