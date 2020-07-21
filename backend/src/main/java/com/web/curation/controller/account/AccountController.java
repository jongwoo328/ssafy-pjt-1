package com.web.curation.controller.account;

import java.security.Key;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.User;
import com.web.curation.model.user.SignupRequest;
import com.web.curation.service.user.JwtUtil;
import com.web.curation.service.user.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
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

    @Autowired
    UserService userService;

	@Autowired
	JwtUtil jwtutil;
	
    
    @PostMapping("/account/login")
    @ApiOperation(value = "로그인")
    public void login(@RequestBody SignupRequest test, HttpServletResponse response) {

    System.out.println(test.getEmail());
    System.out.println(test.getPassword());
    User user = new User();
    user.setEmail(test.getEmail());
    user.setPassword(test.getPassword());
//    ResponseEntity response = null;
//    User userinfo = userService.login(user);
    jwtutil = new JwtUtil();
    String jws = jwtutil.createToken(user);
    if (true) {
//        final BasicResponse result = new BasicResponse();
//        result.status = true;
//        result.data = "success";
//        result.object = userinfo;
//        response = new ResponseEntity<>(result, HttpStatus.OK);
    	response.setHeader("jwsToken", jws);
    	response.setHeader("Access-Control-Expose-Headers", "jwsToken");
    } else {
//        response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
    
    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")
    @Transactional
    public Object signup(@Valid @RequestBody SignupRequest request) {
    	
    	String email = request.getEmail();
    	System.out.println(email);
    	User user = userService.getUserByEmail(email);
    	final BasicResponse result = new BasicResponse();
    	if (user != null) {
    		result.status = false;
    		result.data = "email";
    		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    	}
    	String uid = request.getNickname();
    	user = userService.getUserByUid(uid);
    	if (user != null) {
    		result.status = false;
    		result.data = "nickname";
    		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    	}
    	
    	String password = request.getPassword();
    	System.out.println(uid);
    	System.out.println(password);
//    	user = new User(uid, password, email);
    	System.out.println(user);
    	userService.insert(user);
    	result.status = true;
    	result.data = "success";
    	
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
//    @PostMapping("/account/login")
//    @ApiOperation(value = "로그인")
//    public Object login(@RequestParam(required = true) final String email,
//            @RequestParam(required = true) final String password) {
//
//    	System.out.println(email);
//    	System.out.println(password);
//        Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);
//
//        ResponseEntity response = null;
//
//        if (userOpt.isPresent()) {
//            final BasicResponse result = new BasicResponse();
//            result.status = true;
//            result.data = "success";
//            response = new ResponseEntity<>(result, HttpStatus.OK);
//        } else {
//            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        return response;
//    }
//
//    @PostMapping("/account/signup")
//    @ApiOperation(value = "가입하기")
//    @Transactional
//    public Object signup(@Valid @RequestBody SignupRequest request) {
//    	
//    	String email = request.getEmail();
//    	System.out.println(email);
//    	User user = userDao.getUserByEmail(email);
//    	final BasicResponse result = new BasicResponse();
//    	if (user != null) {
//    		result.status = false;
//    		result.data = "email";
//    		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//    	}
//    	String uid = request.getNickname();
//    	user = userDao.getUserByUid(uid);
//    	if (user != null) {
//    		result.status = false;
//    		result.data = "nickname";
//    		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//    	}
//    	
//    	String password = request.getPassword();
//    	System.out.println(uid);
//    	System.out.println(password);
//    	user = new User(uid, password, email);
//    	System.out.println(user);
//    	userDao.save(user);
//        result.status = true;
//        result.data = "success";
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}