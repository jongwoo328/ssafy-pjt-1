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
	@GetMapping("/rec/{userno}")
	public ResponseEntity<List<Msg>> recMsgList(@PathVariable int userno) throws Exception {
		
		List<Msg> list = msg.selectRecMsg(userno);
		User reciver = user.getUserByUserno(userno);
		
		for(Msg m : list) {
			User sender = user.getUserByUserno(m.getWriterno());
			m.setWritername(sender.getName());
			m.setRecivername(reciver.getName());
		}
		
		
		return new ResponseEntity<List<Msg>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "보낸 쪽지 리스트 반환", response = List.class)
	@GetMapping("/send/{userno}")
	public ResponseEntity<List<Msg>> sendMsgList(@PathVariable int userno) throws Exception {
		List<Msg> list = msg.selectSendMsg(userno);
		User sender = user.getUserByUserno(userno);
		
		for(Msg m : list) {
			
			User reciver = user.getUserByUserno(m.getReciverno());
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
	
	
	@ApiOperation(value = "받은 쪽지 세부 정보 전달", response = Msg.class)
	@GetMapping("/detail/msgNo={msgno}&msgtype={msgtype}")
	public ResponseEntity<Msg> detailRecMsg(@PathVariable int msgno, @PathVariable String msgtype) throws Exception {
		Msg detailMsg = msg.detailMsg(msgno);
		if(msgtype.equals("rec")) {
			if(!detailMsg.isReadcheck()) {
				msg.readMsg(detailMsg);
				detailMsg.setReadcheck(true);
			}		
		} 
		
		detailMsg.setWritername(user.getUserByUserno(detailMsg.getWriterno()).getName());
		detailMsg.setRecivername(user.getUserByUserno(detailMsg.getReciverno()).getName());
		return new ResponseEntity<Msg>(detailMsg,HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "쪽지 전달", response = String.class)
	@PostMapping
	public ResponseEntity<String> sendMsg(@RequestBody Msg message){
		User sender = user.getUserByName(message.getWritername());
		User reciver = user.getUserByName(message.getRecivername());
		message.setReciverno(reciver.getUserno());
		message.setWriterno(sender.getUserno());
		if(msg.senedMsg(message)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			
		} 
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		
	}
	
	@ApiOperation(value="받은 쪽지 삭제", response = String.class)
	@DeleteMapping("/rec")
	public ResponseEntity<String> deleteRecMsg(@RequestBody List<Integer> msgnoList){
		
		
		if(msg.deleteRecMsg(msgnoList)) {
			return new ResponseEntity<String> (SUCCESS, HttpStatus.OK);
		}
		
		
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "보낸 쪽지 삭제", response = String.class)
	@DeleteMapping("/send")
	public ResponseEntity<String> deleteSendMsg(@RequestBody List<Integer> msgnoList){
		if(msg.deleteSendMsg(msgnoList)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
}
