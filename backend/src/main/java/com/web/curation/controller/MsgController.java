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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.Msg;
import com.web.curation.model.User;
import com.web.curation.service.MsgService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/msg")
public class MsgController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	MsgService msg;
	
	@Autowired
	UserService user;
	
	@ApiOperation(value = "받은 쪽지 리스트 반환", response = List.class)
	@GetMapping("/{userno}")
	public ResponseEntity<List<Msg>> msgList(@PathVariable int userno) throws Exception {
		List<Msg> list = msg.selectMsg(userno);
		User reciver = user.getUserByUserno(userno);
		
		for(Msg m : list) {
			User sender = user.getUserByUserno(m.getWriterno());
			m.setWritername(sender.getName());
			m.setRecivername(reciver.getName());
		}
		
		
		return new ResponseEntity<List<Msg>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "읽지 않은 쪽지 개수 반환", response = Integer.class)
	@GetMapping("/count/{userno}")
	public ResponseEntity<Integer> msgCount(@PathVariable int userno) throws Exception {
		
		return new ResponseEntity<Integer>(msg.msgCount(userno), HttpStatus.OK); 
	}
	
	
	@ApiOperation(value = "쪽지 세부 정보 전달", response = Msg.class)
	@GetMapping("/detail/{msgno}")
	public ResponseEntity<Msg> detailMsg(@PathVariable int msgno) throws Exception {
		System.out.println(msgno + " msgno 세부정보 전달");
		Msg detailMsg = msg.detailMsg(msgno);
		if(!detailMsg.isReadcheck()) {
			msg.readMsg(detailMsg);
			detailMsg.setReadcheck(true);
		}
		
		return new ResponseEntity<Msg>(detailMsg,HttpStatus.OK);
	}
	
	@ApiOperation(value = "쪽지 전달", response = String.class)
	@PostMapping
	public ResponseEntity<String> sendMsg(@RequestBody Msg message){
		System.out.println("메시지 전달");
		System.out.println(message);
		User sender = user.getUserByName(message.getWritername());
		User reciver = user.getUserByName(message.getRecivername());
		System.out.println(sender.getUserno());
		System.out.println(reciver.getUserno());
		message.setReciverno(reciver.getUserno());
		message.setWriterno(sender.getUserno());
		if(msg.senedMsg(message)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			
		} 
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "쪽지 삭제", response = String.class)
	@DeleteMapping("/{msgno}")
	public ResponseEntity<String> deleteMsg(@PathVariable int msgno){
		System.out.println(msgno + " 번 쪽지 삭제 합니다.");
		
		if(msg.deleteMsg(msgno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}
