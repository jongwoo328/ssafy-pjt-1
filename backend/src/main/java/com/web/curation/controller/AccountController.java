package com.web.curation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.config.JwtUtil;
import com.web.curation.model.Admin;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.ConnectorService;
import com.web.curation.model.Follow;
import com.web.curation.model.Profile;
import com.web.curation.model.User;
import com.web.curation.service.FollowService;
import com.web.curation.service.MsgService;
import com.web.curation.service.PayService;
import com.web.curation.service.ProfileService;
import com.web.curation.service.ServiceService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = { "*" })
@RestController
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
    @Autowired
    UserService userService;
    
    @Autowired
	JwtUtil jwtutil;
    
    @Autowired
    MsgService msg;
    
    @Autowired
    PayService pay;
    
    @Autowired
	ProfileService profile;
    
    @Autowired
	FollowService folService;
    
    @Autowired
    ServiceService serv;
    
    @PostMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(@RequestBody User test, HttpServletResponse response) {

    User user = new User();
    user.setEmail(test.getEmail());
    user.setPw(test.getPw());
    ResponseEntity response1 = null;
    User userinfo = userService.login(user);
    String jws = jwtutil.createToken(user);
    if (userinfo != null) {
    	userinfo.setMsgcount(msg.msgCount(userinfo.getUserno()));
    	
    	Admin checkadmin = userService.checkAdmin(userinfo.getUserno());
        
    	if(checkadmin == null) {
    		userinfo.setCheckadmin(false);
    	} else {
    		userinfo.setCheckadmin(true);
    	}
    	
    	final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = userinfo;
        response1 = new ResponseEntity<>(result, HttpStatus.OK);
    	response.setHeader("jwsToken", jws);
    	response.setHeader("Access-Control-Expose-Headers", "jwsToken");
    } else {
        response1 = new ResponseEntity<String>("로그인 정보 에러", HttpStatus.OK);
    }
    
    return response1;
}
    
    @ApiOperation(value = "모든 유저 name 정보 반환", response = List.class)
    @GetMapping("/account/total/{word}")
    public ResponseEntity<List<String>> totalUser(@PathVariable String word){
    	return new ResponseEntity<List<String>>(userService.totalUserName(word), HttpStatus.OK);
    }
    
    
    
    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")
    @Transactional
    public Object signup(@RequestBody User user) {
    	String email = user.getEmail();
    	User newUser = userService.getUserByEmail(email);
    	BasicResponse result = new BasicResponse();
    	if (newUser != null) {
    		result.status = false;
    		result.data = "email";
    		return new ResponseEntity<BasicResponse>(result, HttpStatus.BAD_REQUEST);
    	}
    	String name = user.getName();
    	newUser = userService.getUserByName(name);
    	if (newUser != null) {
    		result.status = false;
    		result.data = "name";
    		return new ResponseEntity<BasicResponse>(result, HttpStatus.BAD_REQUEST);
    	}
    	
    	logger.debug("호출");
    	if(userService.insert(user)) {
    		result.status = true;
    		result.data = "success";
    		User u = userService.getUserByEmail(email);
    		Profile p = new Profile();
    		p.setUserno(u.getUserno());
    		p.setImgurl("null.png");
    		profile.writeProfile(p);
    		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);  	
    	}
    	result.status = false;
		result.data = "fail";
    	return new ResponseEntity<BasicResponse>(result, HttpStatus.NOT_FOUND);  
    }
    
    @PostMapping("/account/pwfind")
    @ApiOperation(value = "비밀번호 찾기")
    @Transactional
    public ResponseEntity<BasicResponse> pwfind(@RequestBody User user) {
    	
    	String email = user.getEmail();
    	User newUser = userService.getUserByEmail(email);
    	BasicResponse result = new BasicResponse();
    	if (newUser == null) {
    		result.status = false;
    		result.data = "email";
    		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    	}
    	
    	char[] charSet = new char[] {'Z','K','W', '0', 'E', 'J', '1','N', 'A', '2','P',  'B', '3', 'S','C', '4'
    			, 'D','M','Q', '5','L', 'F', '6','G','V',  '7','H','O', '8','I','T', '9',     
    			   'R',   'U',  'X', 'Y'  }; 
    	int idx = 0;
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < 8; i++) { 
    		idx = (int) (charSet.length * Math.random());
    		sb.append(charSet[idx]);
    	}
    	

    	
    	user.setPw(sb.toString());
    	if (userService.samplePw(user)) {
    		result.status = true;
    		result.data = sb.toString();
    		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK); 
    	}
    	result.status = false;
		result.data = "fail";
    	return new ResponseEntity<BasicResponse>(result, HttpStatus.NOT_FOUND);  
    }
    
    @PostMapping("/account/signup/checkemail")
    @ApiOperation(value = "이메일 중복체크")
    public ResponseEntity<String> checkEmail(@RequestBody String email) {
    	User user = userService.getUserByEmail(email);
    	if (user != null) {
    		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    	}
    	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
    
    @PostMapping("/account/signup/checkname")
    @ApiOperation(value = "이름 중복체크")
    public ResponseEntity<String> checkName(@RequestBody String username) {
    	User user = userService.getUserByName(username);
    	if (user != null) {
    		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    	}
    	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
    @PostMapping("/account/userinfo")
    @ApiOperation(value = "회원정보 리턴")
    public ResponseEntity<String> UserInfo(@RequestBody String email) {
    	User user = userService.getUserByEmail(email);
    	if (user != null) {
    		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
    @PostMapping("/account/userinfo/modify")
    @ApiOperation(value = "회원정보 리턴")
    public ResponseEntity<String> UserInfoModify(@RequestBody User user) {
    	int cnt = userService.update(user);
    	if (cnt != 1) {
    		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
    
    @GetMapping("/account/payinfo/{servno}")
    @ApiOperation(value = "결제한 회원 정보 리턴")
    public ResponseEntity<List<User>> PayUserInfo(@PathVariable int servno){
    	List<Integer> usernoList = pay.servPay(servno);
    	ConnectorService conServ = serv.detailService(servno);
    	
    	
    	List<User> userList = new ArrayList<User>();
    	for(int userno : usernoList) {
    		User u = userService.getUserByUserno(userno);
    		Profile p = profile.detailProfile(userno);
    		if(p != null) {
    			u.setAddr1("img/profile/" + p.getImgurl());
    		} else {
    			u.setAddr1("img/profile/null.png");
    		}
    		u.setPw("XXXX");
    		u.setFolcount(folService.followCount(userno));
    		Follow f = new Follow();
    		f.setUserno(conServ.getUserno());
    		f.setProno(userno);
    		if(folService.selectFollow(f) != null) {
    			u.setCheckfollow(true);
    		} else {
    			u.setCheckfollow(false);
    		}
    		
    		userList.add(u);
    	}
    	
    	return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

}